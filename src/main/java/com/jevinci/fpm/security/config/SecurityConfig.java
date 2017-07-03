package com.jevinci.fpm.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jevinci.fpm.security.auth.jwt.JwtAuthenticationSuccessHandler;
import com.jevinci.fpm.security.auth.jwt.SkipPathRequestMatcher;
import com.jevinci.fpm.security.auth.jwt.JwtAuthenticationFilter;
import com.jevinci.fpm.security.auth.jwt.JwtAuthenticationProvider;
import com.jevinci.fpm.security.auth.rest.RestAuthenticationFailureHandler;
import com.jevinci.fpm.security.auth.rest.RestAuthenticationSuccessHandler;
import com.jevinci.fpm.security.auth.rest.RestAuthenticationProvider;
import com.jevinci.fpm.security.auth.rest.RestLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.Arrays;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    RestAuthenticationProvider restAuthenticationProvider;
    @Autowired
    RestAuthenticationSuccessHandler restAuthenticationSuccessHandler;
    @Autowired
    RestAuthenticationFailureHandler restAuthenticationFailureHandler;
    @Autowired
    ObjectMapper objectMapper;

    public static final String AUTH_END_POINT = "/auth/**";
    public static final String LOGIN_END_POINT = "/login";

    private RestLoginFilter restLoginFilter() throws Exception {
        RestLoginFilter filter = new RestLoginFilter(loginRequestMatcher(), objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);
        filter.setAuthenticationSuccessHandler(restAuthenticationSuccessHandler);

        return filter;
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationFailureHandler(restAuthenticationFailureHandler);

        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(restLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                    .antMatchers("/auth/**").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/guest/**").hasAuthority("GUEST")
                    .antMatchers("/foot/**").hasRole("GUEST")
                    .antMatchers("/user/**").hasRole("USER")
                    .antMatchers("/party/**").hasRole("USER")
                    .and()
                .logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(jwtAuthenticationProvider)
                .authenticationProvider(restAuthenticationProvider)
        ;
    }

    private SkipPathRequestMatcher skipPathRequestMatcher(){
        return new SkipPathRequestMatcher(Arrays.asList(AUTH_END_POINT, LOGIN_END_POINT));
    }

    private RequestMatcher loginRequestMatcher(){
        return new AntPathRequestMatcher(LOGIN_END_POINT, HttpMethod.POST.name());
    }


}
