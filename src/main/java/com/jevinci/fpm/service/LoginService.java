package com.jevinci.fpm.service;

import com.jevinci.fpm.security.auth.rest.LoginResponse;
import com.jevinci.fpm.security.auth.rest.LoginRequest;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */
public interface LoginService {
    LoginResponse socialLogin(LoginRequest loginRequest);
    LoginResponse naverLogin(String accessToken);
    LoginResponse facebookLogin();
    LoginResponse googoleLogin();
}
