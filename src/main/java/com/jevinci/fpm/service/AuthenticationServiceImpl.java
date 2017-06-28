package com.jevinci.fpm.service;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.jwt.JwtFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by seongmin Park on 2017. 6. 28..
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    JwtFactory jwtFactory;

    @Override
    public String getToken(User guest) {
        return jwtFactory.createToken(guest);
    }
}
