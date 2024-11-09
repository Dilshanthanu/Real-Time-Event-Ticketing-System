package com.example.Real_Time.Event.Ticketing.System.Security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class userPrincipalAuthenticationToken extends AbstractAuthenticationToken {

    private final userPrincipal principal;


    public userPrincipalAuthenticationToken(userPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public userPrincipal getPrincipal() {
        return principal;
    }
}
