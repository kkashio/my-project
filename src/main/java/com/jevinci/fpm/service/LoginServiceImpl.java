package com.jevinci.fpm.service;

import com.jevinci.fpm.api.naver.NaverLoginService;
import com.jevinci.fpm.dto.SocialLoginResponseDTO;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.dto.SocialLoginDTO;
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
    public SocialLoginResponseDTO socialLogin(SocialLoginDTO socialLoginDTO) {
        String provider = socialLoginDTO.getProvider();
        String accessToken = socialLoginDTO.getAccessToken();

        switch (provider) {
            case "naver" : return naverLogin(accessToken);
            case "facebook" : return facebookLogin();
            case "googleLogin" : return googoleLogin();
        }
        return null;
    }

    @Override
    public SocialLoginResponseDTO naverLogin(String accessToken) {
        return naverLoginService.getProfile(accessToken);
    }

    @Override
    public SocialLoginResponseDTO facebookLogin() {
        return null;
    }

    @Override
    public SocialLoginResponseDTO googoleLogin() {
        return null;
    }
}
