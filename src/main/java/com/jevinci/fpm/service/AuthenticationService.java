package com.jevinci.fpm.service;

import com.jevinci.fpm.domain.User;

/**
 * Created by seongmin Park on 2017. 6. 28..
 */
public interface AuthenticationService {
    String getToken(User guest);
}
