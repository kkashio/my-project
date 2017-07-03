package com.jevinci.fpm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {
    @Id @Column(name="USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SOCIAL_ID")
    private String socialId;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "NICKNAME")
    private String nickname;

    @Column(name = "GENDER")
    private String gender;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
    private List<UserRole> roles;

    @Column(name = "REG_DATE", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Date regDate;

    @Column(name = "IMAGE_PATH")
    private String imagePath;

    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    @Column(name = "IS_USED", nullable = false)
    private Boolean isUsed = true;

    public User(Long id, String socialId, String email, List<UserRole> roles){
        this.id = id;
        this.socialId = socialId;
        this.email = email;
        this.roles = roles;
    }
}
