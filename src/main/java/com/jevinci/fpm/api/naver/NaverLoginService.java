package com.jevinci.fpm.api.naver;

import com.jevinci.fpm.dto.SocialLoginResponseDTO;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */
public interface NaverLoginService {
    SocialLoginResponseDTO getProfile(String accessToken);
}
