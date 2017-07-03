package com.jevinci.fpm.security.auth.rest;

import com.jevinci.fpm.security.impl.UserDetailsImpl;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Created by seongmin Park on 2017. 6. 29..
 */
public class RestAuthenticationToken extends AbstractAuthenticationToken {

    private LoginRequest loginRequest;
    private UserDetailsImpl user;

    public RestAuthenticationToken(LoginRequest loginRequest){
        super(null);
        this.loginRequest = loginRequest;
        this.setAuthenticated(false);
    }

    public RestAuthenticationToken(UserDetailsImpl user, Collection<? extends GrantedAuthority> authorities){
        super(authorities);
        this.eraseCredentials();
        this.user = user;
        this.setAuthenticated(true);

    }

    @Override
    public Object getCredentials() {
        return loginRequest;
    }

    @Override
    public Object getPrincipal() {
        return this.user;
    }
}
