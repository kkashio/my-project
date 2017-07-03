package com.jevinci.fpm.security.model.token;

import com.auth0.jwt.JWT;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
public interface TokenFactory<T> {
     String createAccessToken(T t);
     String createRefreshToken(T t);
     Boolean verifyToken(String token);
     JWT tokenToJwt(String token);
}
