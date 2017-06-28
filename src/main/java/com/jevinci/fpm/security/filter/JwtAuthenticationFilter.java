package com.jevinci.fpm.security.filter;

import com.jevinci.fpm.domain.User;
import com.jevinci.fpm.security.jwt.JwtAuthenticationToken;
import com.jevinci.fpm.security.jwt.JwtConfig;
import com.jevinci.fpm.security.jwt.JwtFactory;
import com.jevinci.fpm.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.thymeleaf.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

/**
 * Created by seongmin Park on 2017. 6. 26..
 */

@Slf4j
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter{

    @Autowired
    JwtFactory jwtFactory;

    @Autowired
    JwtUtil jwtUtil;

    public JwtAuthenticationFilter(RequestMatcher requestMatcher){
        super(requestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        String token = request.getHeader(JwtConfig.HEADER_NAME);

        if(!jwtFactory.verifyToken(token)){
            throw new BadCredentialsException("Not used JWT");
        }

        User user = new User(jwtUtil.tokenToJwt(token));
        log.info("checking authentication für user " + user.getId());

        if(user == null)
            throw new AccessDeniedException("No token");

        if(user != null && SecurityContextHolder.getContext().getAuthentication() == null){
            return getAuthenticationManager().authenticate(new JwtAuthenticationToken(user));
        }

        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authResult);
        SecurityContextHolder.setContext(context);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        getFailureHandler().onAuthenticationFailure(request, response, failed);
    }
}
