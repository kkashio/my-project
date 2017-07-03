package com.jevinci.fpm.security.auth.jwt;

import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.security.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by seongmin Park on 2017. 6. 26..
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    JwtUserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getCredentials();
        UserDetailsImpl user = userDetailsService.loadUserByUsername(token);
        return new JwtAuthenticationToken(user, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
