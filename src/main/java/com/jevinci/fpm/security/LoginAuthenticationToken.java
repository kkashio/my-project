package com.jevinci.fpm.security;

import com.jevinci.fpm.dto.SocialLoginDTO;
import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * Created by seongmin Park on 2017. 6. 27..
 */
public class LoginAuthenticationToken extends AbstractAuthenticationToken {

    private SocialLoginDTO socialLoginDTO;

    public LoginAuthenticationToken(SocialLoginDTO socialLoginDTO){
        super(null);
        this.socialLoginDTO = socialLoginDTO;
        this.setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return socialLoginDTO;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.socialLoginDTO = null;
    }
}
