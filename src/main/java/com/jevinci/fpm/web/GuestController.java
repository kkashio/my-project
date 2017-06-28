package com.jevinci.fpm.web;

import com.jevinci.fpm.api.naver.NaverApiException;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.dto.SocialLoginDTO;
import com.jevinci.fpm.dto.SocialLoginResponseDTO;
import com.jevinci.fpm.security.jwt.JwtAuthenticationToken;
import com.jevinci.fpm.security.jwt.JwtFactory;
import com.jevinci.fpm.security.provider.SocialAuthenticationProvider;
import com.jevinci.fpm.service.GuestService;
import com.jevinci.fpm.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
@Slf4j
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Autowired
    GuestService service;

    @Autowired
    LoginService loginService;

    @Autowired
    SocialAuthenticationProvider socialAuthenticationProvider;

    @Autowired
    JwtFactory jwtFactory;

    @PostMapping("/login")
    public String login(@RequestBody SocialLoginDTO socialLoginDTO) throws LoginException {
        SocialLoginResponseDTO socialLoginResponseDTO;
        JwtAuthenticationToken token;
        Authentication authentication = null;

        socialLoginResponseDTO = loginService.socialLogin(socialLoginDTO);

        if(!socialLoginResponseDTO.getResultCode().equals("00")){
            throw new LoginException(socialLoginResponseDTO.getMessage());
        } else {
            token = new JwtAuthenticationToken(socialLoginResponseDTO.getUser(), AuthorityUtils.createAuthorityList(socialLoginResponseDTO.getUser().getRoles()));
            authentication = socialAuthenticationProvider.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.info("Social Login : " + authentication.toString());

            return jwtFactory.createToken((User) authentication.getPrincipal());
        }
    }
}
