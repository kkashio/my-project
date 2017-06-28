package com.jevinci.fpm.service;

import com.jevinci.fpm.domain.User;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
public interface GuestService {
    String getToken(User guest);
}
