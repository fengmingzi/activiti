package com.main.activity.common.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Author: fengguang xu
 * @Description: jwttoken
 * @Date: 2019/4/26 10:03
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
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
