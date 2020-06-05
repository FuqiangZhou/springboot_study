package com.zhou.springbootshiro.config.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author 周富强
 * @version 1.0.0
 * @date 2019/12/5 11:36 上午
 */
public class OAuth2Token implements AuthenticationToken {

    private String token;

    public OAuth2Token(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
