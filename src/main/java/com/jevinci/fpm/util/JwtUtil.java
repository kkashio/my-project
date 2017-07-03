package com.jevinci.fpm.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jevinci.fpm.security.config.JwtConfig;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Je.vinci.Inc. on 2017. 4. 14..
 */

@Component
@Slf4j
public class JwtUtil {
    public static String createAccessToken(UserDetailsImpl user) {
        log.info(LocalDateTime.now().plusDays(JwtConfig.EXPIRES_LIMIT).toString());
        return createAccessToken(user, LocalDateTime.now().plusDays(JwtConfig.EXPIRES_LIMIT));
    }

    private static String createAccessToken(UserDetailsImpl user, LocalDateTime dateTime) {
        log.info("Jwt Token create access token [ "+ dateTime + " ] : " + user.getUsername());

        try {
            return JWT.create()
                    .withIssuer(JwtConfig.ISSUER)
                    .withClaim("email", user.getUsername())
                    .withArrayClaim("roles", user.getAuthorities().stream().map(e -> e.toString()).toArray(String[]::new))
                    .withExpiresAt(DateUtil.convertLocalDateTimeToDate(dateTime))
                    .sign(JwtConfig.getAlgorithm());
        } catch (JWTCreationException createEx) {
            return null;
        }
    }

    public static String createRefreshToken(UserDetailsImpl user){
        return createRefreshToken(user, LocalDateTime.now().plusDays(JwtConfig.REFRESH_EXPIRES_LIMIT));
    }

    private static String createRefreshToken(UserDetailsImpl user, LocalDateTime dateTime){
        log.info("create refresh token [ " + dateTime + " ] : " + user.getUsername());
        try {
            return JWT.create()
                    .withIssuer(JwtConfig.ISSUER)
                    .withClaim("email", user.getUsername())
                    .withArrayClaim("roles", new String[]{"ROLE_REFRESH_TOKEN"})
                    .withExpiresAt(DateUtil.convertLocalDateTimeToDate(dateTime))
                    .sign(JwtConfig.getAlgorithm());
        } catch (JWTCreationException createEx){
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
