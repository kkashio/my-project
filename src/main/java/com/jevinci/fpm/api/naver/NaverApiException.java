package com.jevinci.fpm.api.naver;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by seongmin Park on 2017. 6. 28..
 */
public class NaverApiException extends AuthenticationException {
    public NaverApiException(String msg){
        super(msg);
    }
}
