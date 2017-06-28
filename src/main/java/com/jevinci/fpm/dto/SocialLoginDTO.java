package com.jevinci.fpm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialLoginDTO {
    private String accessToken;
    private String provider;
}
