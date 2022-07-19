package com.example.vitallsolutions.helpers;

import com.example.vitallsolutions.interceptors.ClaimsWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class UserInfoProvider {

    public String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ClaimsWrapper claims = new ClaimsWrapper(authentication);

        return claims.getSub();
    }

    public List<String> getUserGroups() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ClaimsWrapper claims = new ClaimsWrapper(authentication);

        return claims.getGroups();
    }

    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        ClaimsWrapper claims = new ClaimsWrapper(authentication);

        return claims.getGroups().contains("Admin");
    }
}
