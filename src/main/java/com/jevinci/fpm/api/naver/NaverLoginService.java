package com.jevinci.fpm.api.naver;

import com.jevinci.fpm.security.auth.rest.LoginResponse;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */
public interface NaverLoginService {
    LoginResponse getProfile(String accessToken);
}
