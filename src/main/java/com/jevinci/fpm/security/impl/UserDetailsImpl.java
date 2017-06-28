package com.jevinci.fpm.security.impl;

import com.jevinci.fpm.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by seongmin Park on 2017. 6. 26..
 */
public class UserDetailsImpl implements UserDetails{

    private final User user;
    private List<GrantedAuthority> authorities;

    public UserDetailsImpl(User user){
        this.user = user;
    }

    public UserDetailsImpl(User user, List<GrantedAuthority> authorities){
        this.user = user;
        this.authorities = authorities;
    }

    public User getUser() { return user; }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return user.getSocialId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
