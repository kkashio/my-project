package com.jevinci.fpm.security.service;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.repository.UserRepository;
import com.jevinci.fpm.security.impl.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */

@Slf4j
@Service
public class SocialUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetailsImpl loadUserByUsername(String socialId) throws UsernameNotFoundException {
        User user = userRepository.findBySocialId(socialId);

        if(user == null){
            throw new UsernameNotFoundException("user is not joined");
        }

        return null;
    }
}