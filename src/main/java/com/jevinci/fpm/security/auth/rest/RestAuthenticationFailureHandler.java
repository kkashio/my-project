package com.jevinci.fpm.security.auth.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jevinci.fpm.api.naver.NaverApiException;
import com.jevinci.fpm.common.ErrorCode;
import com.jevinci.fpm.common.ErrorResponse;
import com.jevinci.fpm.security.exceptions.AuthMethodNotSupportedException;
import com.jevinci.fpm.security.exceptions.JwtExpiredTokenException;
import com.jevinci.fpm.security.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by seongmin Park on 2017. 6. 30..
 */

@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (e instanceof BadCredentialsException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponse.of("Invalid username or password", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (e instanceof JwtExpiredTokenException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponse.of("Token has expired", ErrorCode.JWT_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED));
        } else if (e instanceof AuthMethodNotSupportedException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
        } else if (e instanceof NaverApiException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.NAVER_API, HttpStatus.UNAUTHORIZED));
        } else if (e instanceof UserNotFoundException) {
            objectMapper.writeValue(response.getWriter(), ErrorResponse.of(e.getMessage(), ErrorCode.NOT_FOUND, HttpStatus.UNAUTHORIZED));
        }

        objectMapper.writeValue(response.getWriter(), ErrorResponse.of("Authentication failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED));
    }
}
