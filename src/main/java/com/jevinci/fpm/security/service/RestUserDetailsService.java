package com.jevinci.fpm.security.service;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.repository.UserRepository;
import com.jevinci.fpm.security.exceptions.UserNotFoundException;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by seongmin Park on 2017. 6. 30..
 */

@Slf4j
@Service
public class RestUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String socialId) throws UsernameNotFoundException {
        User user = userRepository.findBySocialId(socialId);

        if(user == null){
            throw new UserNotFoundException("user is not joined");
        }

        return convertUserToUserDetailsImpl(user);
    }

    public UserDetailsImpl convertUserToUserDetailsImpl(User user) throws UsernameNotFoundException {
        //List<String> roles = user.getRoles().stream().map(s -> s.toString()).collect(Collectors.toList());
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRole().authority()))
                .collect(Collectors.toList());
        String email = user.getEmail();

        if(email == null){
            throw new UsernameNotFoundException("Not used JWT");
        }

        return new UserDetailsImpl(email ,authorities);
    }
}