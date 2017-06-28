package com.jevinci.fpm.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */

@Configuration
public class HttpSessionConfig {

    @Bean
    public SessionRepository sessionRepository(){ return new MapSessionRepository();}
}
