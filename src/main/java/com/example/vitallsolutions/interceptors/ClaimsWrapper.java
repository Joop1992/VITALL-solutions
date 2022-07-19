package com.example.vitallsolutions.interceptors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;

import java.util.List;
import java.util.Map;

public class ClaimsWrapper {
    private final Map<String, Object> rawClaims;

    public ClaimsWrapper(Authentication authentication) {
        rawClaims = (((DefaultOidcUser) ((OAuth2AuthenticationToken) authentication).getPrincipal()).getIdToken()).getClaims();
    }

    public String getSub() {
        return (String) rawClaims.get("sub");
    }

    public String getName() {
        return (String) rawClaims.get("name");
    }

    public String getEmail() {
        return (String) rawClaims.get("email");
    }

    public List<String> getGroups() { return (List<String>) rawClaims.get("groups"); }
}
