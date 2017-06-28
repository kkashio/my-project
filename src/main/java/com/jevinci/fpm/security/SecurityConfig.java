package com.jevinci.fpm.security;

import com.jevinci.fpm.security.filter.JwtAuthenticationFilter;
import com.jevinci.fpm.security.provider.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

/**
 * Created by seongmin Park on 2017. 6. 22..
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    JwtAuthenticationProvider provider;

    public static final String GUEST_END_POINT = "/guest/**";
    public static final String AUTH_END_POINT = "/auth/**";

    protected void configure(HttpSecurity http) throws Exception {
        http
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
                .authenticationProvider(provider);
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception{
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(skipPathRequestMatcher());
        filter.setAuthenticationManager(authenticationManager());

        return filter;
    }

    private SkipPathRequestMatcher skipPathRequestMatcher(){
        return new SkipPathRequestMatcher(Arrays.asList(AUTH_END_POINT));
    }
}
