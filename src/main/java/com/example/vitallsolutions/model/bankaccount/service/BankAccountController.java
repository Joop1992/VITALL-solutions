package com.example.vitallsolutions.model.bankaccount.service;

import com.example.vitallsolutions.helpers.UserInfoProvider;
import com.example.vitallsolutions.model.ModelAndViewInitializer;
import com.example.vitallsolutions.model.bankaccount.BankAccount;
import com.example.vitallsolutions.model.bankaccount.crud.create.CreateBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.crud.delete.DeleteBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.crud.update.UpdateBankAccountFormData;
import com.example.vitallsolutions.model.notification.Notification;
import com.example.vitallsolutions.model.notification.NotificationTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/bankaccounts")
public class BankAccountController {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    @PostMapping("/create")
    public ModelAndView createBankAccount(@Valid @ModelAttribute("formData") CreateBankAccountFormData formData, BindingResult bindingResult, Model model) {
        ModelAndView bankAccountView = this.getBankAccountPage();

        if (bindingResult.hasErrors()) {
            bankAccountView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not add bank account"));

            return this.getBankAccountPage();
        }

        if(bankAccountRepository.findByUserId(userInfoProvider.getUserId()).stream().anyMatch(ba -> ba.getIBAN().equalsIgnoreCase(StringUtils.trimAllWhitespace(formData.getIBAN())))) {
            bankAccountView.addObject("notify", new Notification(NotificationTypes.INFO, "Bank account with same IBAN already exists"));

            return bankAccountView;
        }

        bankAccountRepository.save(new BankAccount(formData.toParameters().getName(), StringUtils.trimAllWhitespace(formData.toParameters().getIBAN()), userInfoProvider.getUserId()));

        bankAccountView = this.getBankAccountPage();
        bankAccountView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Bank account successfully created"));

        return bankAccountView;
    }

    @PostMapping("/delete")
    public ModelAndView deleteBankAccount(@Valid @ModelAttribute("deleteFormData") DeleteBankAccountFormData formData, BindingResult bindingResult, Model model) {
        ModelAndView bankAccountView = this.getBankAccountPage();

        if (bindingResult.hasErrors()) {
            bankAccountView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not delete bank account"));

            return this.getBankAccountPage();
        }

    if(!bankAccountRepository.findByIdAndUserId(formData.getId(), userInfoProvider.getUserId()).isPresent()) {
            bankAccountView.addObject("notify", new Notification(NotificationTypes.INFO, "No bank account with this ID exists"));

            return bankAccountView;
        }

        bankAccountRepository.deleteById(formData.getId());
        bankAccountRepository.flush();

        bankAccountView = this.getBankAccountPage();

        bankAccountView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Bank account successfully deleted"));

        return bankAccountView;
    }

    @PostMapping("/edit")
    public ModelAndView editBankAccount(@Valid @ModelAttribute("editFormData") UpdateBankAccountFormData formData, BindingResult bindingResult, Model model) {
        ModelAndView bankAccountView = this.getBankAccountPage();

        if (bindingResult.hasErrors()) {
            bankAccountView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not update bank account"));

            return bankAccountView;
        }

        if(bankAccountRepository.findByIdAndUserId(formData.getId(), userInfoProvider.getUserId()).isPresent()) {
            Optional<BankAccount> ba = bankAccountRepository.findById(formData.toParameters().getId());

            if(ba.isPresent()) {
                BankAccount bankAccount = ba.get();

                bankAccount.setName(formData.toParameters().getName());

                bankAccountRepository.saveAndFlush(bankAccount);
                bankAccountView = this.getBankAccountPage();
            }

            bankAccountView.addObject("notify", new Notification(NotificationTypes.SUCCES, "Bank account successfully updated"));

        } else {
            bankAccountView.addObject("notify", new Notification(NotificationTypes.INFO, "No bank account with this ID exists"));

            return bankAccountView;
        }

        return bankAccountView;
    }

    private ModelAndView getBankAccountPage() {
        ModelAndView modelAndView = new ModelAndViewInitializer().initialize();

        modelAndView.setViewName("/bankaccounts/view");
        modelAndView.addObject("bankAccounts", bankAccountRepository.findByUserId((userInfoProvider.getUserId())));
        modelAndView.addObject("formData", new CreateBankAccountFormData());
        modelAndView.addObject("deleteFormData", new DeleteBankAccountFormData());
        modelAndView.addObject("editFormData", new UpdateBankAccountFormData());
        modelAndView.addObject("selectedPage", "Bank Accounts");

        return modelAndView;
    }

    @RequestMapping(value="/view")
    public ModelAndView getPage() {
        return this.getBankAccountPage();
    }

}

