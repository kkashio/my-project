package com.jevinci.fpm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.jwt.JwtConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Je.vinci.Inc. on 2017. 4. 14..
 */

@Component
@Slf4j
public class JwtUtil {
    public static String create(User user) {
        System.out.println(LocalDateTime.now().plusDays(JwtConfig.EXPIRES_LIMIT).toString());
        return create(user, LocalDateTime.now().plusDays(JwtConfig.EXPIRES_LIMIT));
    }

    private static String create(User user, LocalDateTime dateTime) {
        log.info("Jwt Token create [ "+ dateTime + " ] : " + user.getId());
        try {
            return JWT.create()
                    .withIssuer(JwtConfig.ISSUER)
                    .withClaim("id", user.getId())
                    .withArrayClaim("roles", user.getRoles())
                    .withExpiresAt(DateUtil.convertLocalDateTimeToDate(dateTime))
                    .sign(JwtConfig.getAlgorithm());
        } catch (JWTCreationException createEx) {
            return null;
        }
    }

    public static Boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(JwtConfig.getAlgorithm()).build();
            verifier.verify(token);

            return Boolean.TRUE;
        } catch (JWTVerificationException verifyEx) {
            return Boolean.FALSE;
        }
    }

    public static JWT tokenToJwt(String token) {
        try {
            return JWT.decode(token);
        } catch (JWTDecodeException decodeEx) {
            return null;
        }
    }
}
