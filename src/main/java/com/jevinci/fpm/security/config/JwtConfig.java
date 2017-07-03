package com.jevinci.fpm.security.config;

import com.auth0.jwt.algorithms.Algorithm;

import java.io.UnsupportedEncodingException;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
public class JwtConfig {
    public static String HEADER_NAME = "jwt-header";

    public static String ISSUER = "cp";

    public static String TOKEN_KEY = "jevinci1209";

    public static Long EXPIRES_LIMIT = 1 * 1L;

    public static Long REFRESH_EXPIRES_LIMIT = 7 * 1L;

    public static Algorithm getAlgorithm() {
        try {
            return Algorithm.HMAC256(TOKEN_KEY);
        } catch (IllegalArgumentException | UnsupportedEncodingException e) {
            return Algorithm.none();
        }
    }
}
