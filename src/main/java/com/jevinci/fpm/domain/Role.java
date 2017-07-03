package com.jevinci.fpm.domain;

/**
 * Created by seongmin Park on 2017. 6. 29..
 */
public enum Role {
    ADMIN, PREMIUM_USER, USER, MANAGER;
    public String authority() { return "ROLE_" + this.name(); }
}
