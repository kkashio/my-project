package com.jevinci.fpm.security.service;

import com.auth0.jwt.JWT;
import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.repository.UserRepository;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import com.jevinci.fpm.security.jwt.JwtFactory;
import com.jevinci.fpm.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by seongmin Park on 2017. 6. 26..
 */

@Service
public class JwtUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user;
        if(!userId.equals("GUEST")) {
            user = userRepository.findOne(userId);

            if(user == null){
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", userId));
            }
        } else {
            user = new User("GUEST", new String[]{"GUEST"});
        }

        return new UserDetailsImpl(user, AuthorityUtils.createAuthorityList(user.getRoles()));
    }
}
