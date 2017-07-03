package com.jevinci.fpm.service;

import com.jevinci.fpm.api.naver.NaverApiException;
import com.jevinci.fpm.api.naver.NaverLoginService;
import com.jevinci.fpm.security.auth.rest.LoginResponse;
import com.jevinci.fpm.security.auth.rest.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */

@Service
public class LoginServiceImpl implements LoginService{

    @Autowired
    NaverLoginService naverLoginService;

    @Override
    public LoginResponse socialLogin(LoginRequest loginRequest) {
        String provider = loginRequest.getProvider();
        String accessToken = loginRequest.getAccessToken();

        switch (provider) {
            case "naver" : return naverLogin(accessToken);
            case "facebook" : return facebookLogin();
            case "googleLogin" : return googoleLogin();
        }
        return null;
    }

    @Override
    public LoginResponse naverLogin(String accessToken) {
        LoginResponse loginResponse = naverLoginService.getProfile(accessToken);
        if (loginResponse == null){
            throw new NaverApiException("NAVER API ERROR");
        }
        return loginResponse;
    }

    @Override
    public LoginResponse facebookLogin() {
        return null;
    }

    @Override
    public LoginResponse googoleLogin() {
        return null;
    }
}
