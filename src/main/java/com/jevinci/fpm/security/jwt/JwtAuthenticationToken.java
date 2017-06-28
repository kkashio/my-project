package com.jevinci.fpm.security.jwt;

import com.jevinci.fpm.domain.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private User user;

    public JwtAuthenticationToken(User user){
        super(null);
        this.user = user;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(User user, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.eraseCredentials();
        this.user = user;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return user;
    }

//    @Override
//    public void eraseCredentials() {
//        super.eraseCredentials();
//        this.user = null;
//    }
}