package com.jevinci.fpm.security.service;

import com.auth0.jwt.JWT;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.security.model.token.TokenFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by seongmin Park on 2017. 6. 26..
 */

@Service
public class JwtUserDetailsService implements UserDetailsService{

    @Autowired
    TokenFactory tokenFactory;

    @Override
    public UserDetailsImpl loadUserByUsername(String token) throws UsernameNotFoundException {
        if(!tokenFactory.verifyToken(token)){
            throw new BadCredentialsException("Not used JWT");
        }
        JWT jwt = tokenFactory.tokenToJwt(token);

        return convertJwtToUserDetailsImpl(jwt);
    }

    public UserDetailsImpl convertJwtToUserDetailsImpl(JWT jwt) throws UsernameNotFoundException {
        List<String> roles = jwt.getClaim("roles").asList(String.class);
        List<GrantedAuthority> authorities = roles.stream()
                .map(authority -> new SimpleGrantedAuthority(authority))
                .collect(Collectors.toList());
        String email = jwt.getClaim("email").toString();

        if(email == null){
            throw new UsernameNotFoundException("Not used JWT");
        }

        return new UserDetailsImpl(email ,authorities);
    }
}
