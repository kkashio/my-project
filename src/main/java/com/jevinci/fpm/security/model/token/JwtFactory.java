package com.jevinci.fpm.security.model.token;

import com.auth0.jwt.JWT;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.util.JwtUtil;
import org.springframework.stereotype.Component;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
@Component
public class JwtFactory implements TokenFactory<UserDetailsImpl> {

    @Override
    public String createAccessToken(UserDetailsImpl user) {
        return JwtUtil.createAccessToken(user);
    }

    @Override
    public Boolean verifyToken(String token) {
        return JwtUtil.verify(token);
    }

    @Override
    public JWT tokenToJwt(String token) {
        return JwtUtil.tokenToJwt(token);
    }

    @Override
    public String createRefreshToken(UserDetailsImpl user) {
        return JwtUtil.createRefreshToken(user);
    }
}
