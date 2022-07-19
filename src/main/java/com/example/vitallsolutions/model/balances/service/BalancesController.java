package com.example.vitallsolutions.model.balances.service;


import com.example.vitallsolutions.helpers.UserInfoProvider;
import com.example.vitallsolutions.model.ModelAndViewInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/balances")
public class BalancesController {

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    private ModelAndView getBalancesPage() {
        ModelAndView modelAndView = new ModelAndViewInitializer().initialize();

        modelAndView.setViewName("/balances/view");
        //TODO make call to BUNQ and see current balance and get all transfers to construct stichting balance and payedOut balance
        //modelAndView.addObject("bankAccounts", transferReposi.findByUserId((userInfoProvider.getUserId())));

        modelAndView.addObject("selectedPage", "Balances");

        return modelAndView;
    }

    @RequestMapping(value="/view")
    public ModelAndView getPage() {
        return this.getBalancesPage();
    }

}
