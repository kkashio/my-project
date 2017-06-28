package com.jevinci.fpm.security.jwt;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
public interface TokenFactory<T> {
     String createToken(T t);
     Boolean verifyToken(String token);
}
