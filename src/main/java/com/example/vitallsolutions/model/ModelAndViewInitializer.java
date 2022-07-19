package com.example.vitallsolutions.model;

import com.example.vitallsolutions.helpers.UserInfoProvider;
import com.example.vitallsolutions.interceptors.ClaimsWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

public class ModelAndViewInitializer {

    private UserInfoProvider userInfoProvider = new UserInfoProvider();

    public ModelAndView initialize() {
        ModelAndView mav = new ModelAndView();

        mav.addObject("initials", this.getUserInitials());
        mav.addObject("groups", this.userInfoProvider.getUserGroups());

        return mav;
    }

    private String getUserInitials() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ClaimsWrapper claims = new ClaimsWrapper(authentication);

        String lastWord = claims.getName().substring(claims.getName().lastIndexOf(" ")+1);
        char firstLetter = claims.getName().toUpperCase().trim().charAt(0);
        char lastLetter = lastWord.toUpperCase().trim().charAt(0);

        String initials = firstLetter + "" + lastLetter;

        return initials;
    }

}
