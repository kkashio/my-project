package com.jevinci.fpm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }
}
