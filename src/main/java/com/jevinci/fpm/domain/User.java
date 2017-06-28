package com.jevinci.fpm.domain;

import com.auth0.jwt.JWT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    private String id;
    private String socialId;
    private String email;
    private String provider;
    private String nickname;
    private String gender;
    private String[] roles;
    private Date regDate;
    private String imagePath;
    private Date lastLoginDate;
    private Boolean isUsed = true;

    public User(String id, String[] roles){
        this.id = id;
        this.roles = roles;
    }

    public User(JWT jwt){
        this.id = jwt.getClaim("id").toString();
        this.roles = jwt.getClaim("roles").asArray(String.class);
    }

    public void setNaverProfile(LinkedHashMap responseMap){
        email = responseMap.get("email").toString();
        imagePath = responseMap.get("profile_image").toString();
        gender = responseMap.get("gender").toString();
        socialId = responseMap.get("id").toString();
        provider = "naver";
    }
}
