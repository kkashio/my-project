package com.jevinci.fpm.security.provider;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.security.jwt.JwtAuthenticationToken;
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
        String userId = ((User) authentication.getPrincipal()).getId();
        UserDetailsImpl user = userDetailsService.loadUserByUsername(userId);
        return new JwtAuthenticationToken(user.getUser(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
