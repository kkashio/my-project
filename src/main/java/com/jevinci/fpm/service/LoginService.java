package com.jevinci.fpm.service;

import com.jevinci.fpm.api.naver.NaverApiException;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.dto.SocialLoginDTO;
import com.jevinci.fpm.dto.SocialLoginResponseDTO;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */
public interface LoginService {
    SocialLoginResponseDTO socialLogin(SocialLoginDTO socialLoginDTO);
    SocialLoginResponseDTO naverLogin(String accessToken);
    SocialLoginResponseDTO facebookLogin();
    SocialLoginResponseDTO googoleLogin();
}
