package com.jevinci.fpm.security.jwt;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.util.JwtUtil;
import org.springframework.stereotype.Component;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
@Component
public class JwtFactory implements TokenFactory<User> {

    @Override
    public String createToken(User user) {
        return JwtUtil.create(user);
    }

    @Override
    public Boolean verifyToken(String token) {
        return JwtUtil.verify(token);
    }
}
