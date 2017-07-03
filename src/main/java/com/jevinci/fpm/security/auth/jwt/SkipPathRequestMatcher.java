package com.jevinci.fpm.security.auth.jwt;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by seongmin Park on 2017. 6. 26..
 */
public class SkipPathRequestMatcher implements RequestMatcher{

    private OrRequestMatcher skipRequestMatcher;

    public SkipPathRequestMatcher(List<String> skipPathList) {
        if(!skipPathList.isEmpty()) {
            List<RequestMatcher> requestMatcherList = skipPathList.stream()
                    .map(skipPath -> new AntPathRequestMatcher(skipPath))
                    .collect(Collectors.toList());
            skipRequestMatcher = new OrRequestMatcher(requestMatcherList);
        }
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if(skipRequestMatcher.matches(request)) {
            return false;
        } else {
            return true;
        }
    }
}
