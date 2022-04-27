package edu.xcj.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 19:35
 */
public class JwtToken implements AuthenticationToken {
    private String token;
    public JwtToken(String jwt) {
        this.token = jwt;
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
