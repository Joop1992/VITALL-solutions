package com.example.vitallsolutions;

import com.example.vitallsolutions.helpers.UserInfoProvider;
import com.example.vitallsolutions.interceptors.ClaimsWrapper;
import com.example.vitallsolutions.model.ModelAndViewInitializer;
import com.example.vitallsolutions.model.bankaccount.crud.create.CreateBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.crud.delete.DeleteBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.crud.update.UpdateBankAccountFormData;
import com.example.vitallsolutions.model.bankaccount.service.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    @Autowired
    BankAccountRepository bankAccountRepository;

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    @RequestMapping("/")
    @ResponseBody
    public ModelAndView home(@AuthenticationPrincipal OidcUser oidcUser) {
        ModelAndView modelAndView = new ModelAndViewInitializer().initialize();

        System.out.println(oidcUser.getFullName());
        modelAndView.setViewName("/bankaccounts/view");
        modelAndView.addObject("bankAccounts", bankAccountRepository.findByUserId(userInfoProvider.getUserId()));
        modelAndView.addObject("formData", new CreateBankAccountFormData());
        modelAndView.addObject("deleteFormData", new DeleteBankAccountFormData());
        modelAndView.addObject("editFormData", new UpdateBankAccountFormData());
        modelAndView.addObject("selectedPage", "Bank Accounts");

        return modelAndView;
    }

    @RequestMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndViewInitializer().initialize();
        modelAndView.setViewName("/bankaccounts/view");
        modelAndView.addObject("bankAccounts", bankAccountRepository.findByUserId(userInfoProvider.getUserId()));
        modelAndView.addObject("formData", new CreateBankAccountFormData());
        modelAndView.addObject("deleteFormData", new DeleteBankAccountFormData());
        modelAndView.addObject("editFormData", new UpdateBankAccountFormData());
        modelAndView.addObject("selectedPage", "Bank Accounts");

        return modelAndView;
    }

    @RequestMapping("/attributes")
    @ResponseBody
    public String attributes(@AuthenticationPrincipal OidcUser oidcUser) {
        return oidcUser.getAttributes().toString();
    }

    @RequestMapping("/authorities")
    @ResponseBody
    public String authorities(@AuthenticationPrincipal OidcUser oidcUser) {
        return oidcUser.getAuthorities().toString();
    }


    
}