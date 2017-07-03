package com.jevinci.fpm.security.auth.rest;

import com.jevinci.fpm.api.naver.NaverApiException;
import com.jevinci.fpm.api.naver.NaverLoginService;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.auth.jwt.JwtAuthenticationToken;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.security.service.RestUserDetailsService;
import com.jevinci.fpm.security.service.SocialUserDetailsService;
import com.jevinci.fpm.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Created by seongmin Park on 2017. 6. 29..
 */

@Slf4j
@Component
public class RestAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    RestUserDetailsService restUserDetailsService;

    @Autowired
    LoginService loginService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginRequest loginRequest = (LoginRequest) authentication.getCredentials();
        LoginResponse loginResponse = loginService.socialLogin(loginRequest);
        UserDetailsImpl userDetails = restUserDetailsService.loadUserByUsername(loginResponse.getUser().getSocialId());
        log.info("social request -> token : "+ userDetails.getUsername());

        return new RestAuthenticationToken(userDetails, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RestAuthenticationToken.class.isAssignableFrom(authentication);
    }
}