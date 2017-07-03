package com.jevinci.fpm.security.auth.rest;

import com.jevinci.fpm.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by seongmin Park on 2017. 6. 28..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String resultCode;
    private String message;
    private User user;
}
