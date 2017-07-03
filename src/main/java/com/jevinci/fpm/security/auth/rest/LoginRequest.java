package com.jevinci.fpm.security.auth.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by seongmin Park on 2017. 6. 29..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    private String accessToken;
    private String provider;
}
