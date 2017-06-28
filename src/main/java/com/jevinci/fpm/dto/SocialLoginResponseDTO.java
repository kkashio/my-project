package com.jevinci.fpm.dto;

import com.jevinci.fpm.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Created by seongmin Park on 2017. 6. 28..
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialLoginResponseDTO {
    private String resultCode;
    private String message;
    private User user;
}
