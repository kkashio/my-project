package com.jevinci.fpm.security.provider;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.security.jwt.JwtAuthenticationToken;
import com.jevinci.fpm.security.service.SocialUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */

@Slf4j
@Component
public class SocialAuthenticationProvider implements AuthenticationProvider{

    @Autowired
    SocialUserDetailsService socialUserDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        User user = (User) authentication.getCredentials();
        UserDetailsImpl userDetails = socialUserDetailsService.loadUserByUsername(user.getSocialId());
        log.info("social request -> token : "+ user);

        return new JwtAuthenticationToken(userDetails.getUser(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
