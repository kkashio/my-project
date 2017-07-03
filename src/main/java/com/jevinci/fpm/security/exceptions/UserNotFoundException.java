package com.jevinci.fpm.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by seongmin Park on 2017. 6. 30..
 */
public class UserNotFoundException extends AuthenticationException {
    public UserNotFoundException(String msg){
        super(msg);
    }

}
