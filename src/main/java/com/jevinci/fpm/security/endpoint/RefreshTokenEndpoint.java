package com.jevinci.fpm.security.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by seongmin Park on 2017. 6. 29..
 * 토큰 갱신
 */

@RestController
public class RefreshTokenEndpoint {

    @GetMapping("/auth/token")
    public String refreshToken(){
        return null;
    }
}
