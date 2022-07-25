package com.example.vitallsolutions.model.user.service;

import com.example.vitallsolutions.helpers.UserInfoProvider;
import com.example.vitallsolutions.model.ModelAndViewInitializer;
import com.example.vitallsolutions.model.bankaccount.BankAccount;
import com.example.vitallsolutions.model.bankaccount.crud.create.CreateBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.crud.delete.DeleteBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.crud.update.UpdateBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.service.BankAccountRepository;
import com.example.vitallsolutions.model.notification.Notification;
import com.example.vitallsolutions.model.notification.NotificationTypes;
import com.example.vitallsolutions.model.user.User;
import com.example.vitallsolutions.model.user.crud.update.UpdateUserFormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    @PostMapping("/edit")
    public ModelAndView editUser(@Valid @ModelAttribute("editFormData") UpdateUserFormData formData, BindingResult bindingResult, Model model) {
        ModelAndView usersView = this.getUsersPage();

        if (bindingResult.hasErrors()) {
            usersView.addObject("notify", new Notification(NotificationTypes.ERROR, "Could not update user"));

            return usersView;
        }

        if(userRepository.findById(formData.getId()).isPresent()) {
            Optional<User> user = userRepository.findById(formData.toParameters().getId());

            if(user.isPresent()) {
                User u = user.get();

                u.setFee(formData.toParameters().getFee());
                u.setStatus(formData.toParameters().getStatus());

                userRepository.saveAndFlush(u);
                usersView = this.getUsersPage();
            }

            usersView.addObject("notify", new Notification(NotificationTypes.SUCCES, "User successfully updated"));

        } else {
            usersView.addObject("notify", new Notification(NotificationTypes.INFO, "No user with this ID exists"));

            return usersView;
        }

        return usersView;
    }

    private ModelAndView getUsersPage() {
        if(userInfoProvider.isAdmin()) {
            ModelAndView modelAndView = new ModelAndViewInitializer().initialize();
            modelAndView.setViewName("/users/view");
            modelAndView.addObject("editFormData", new UpdateUserFormData());
            modelAndView.addObject("selectedPage", "Users");
            modelAndView.addObject("items", userRepository.findAll());

            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndViewInitializer().initialize();
            modelAndView.setViewName("/bankaccounts/view");
            modelAndView.addObject("items", bankAccountRepository.findByUserId(userInfoProvider.getUserId()));
            modelAndView.addObject("formData", new CreateBankAccountFormData());
            modelAndView.addObject("deleteFormData", new DeleteBankAccountFormData());
            modelAndView.addObject("editFormData", new UpdateBankAccountFormData());
            modelAndView.addObject("selectedPage", "Bank Accounts");

            return modelAndView;
        }
    }

    @RequestMapping(value="/view")
    public ModelAndView getPage() {
        return this.getUsersPage();
    }
}
