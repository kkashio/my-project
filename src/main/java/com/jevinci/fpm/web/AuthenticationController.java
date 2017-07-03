package com.jevinci.fpm.web;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.domain.UserRole;
import com.jevinci.fpm.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seongmin Park on 2017. 6. 28..
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService service;

    @GetMapping("/getToken")
    public String getToken(){
        User user = new User();
        List<UserRole> roles = new ArrayList<>();
        roles.add(new UserRole());
        user.setId(174111475L);
        user.setRoles(roles);
        return service.getToken(user);
    }
}
