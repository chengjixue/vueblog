package edu.xcj.shiro;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sun.net.httpserver.Authenticator;
import edu.xcj.common.lang.Result;
import edu.xcj.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.net.httpserver.AuthFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 19:28
 */
@Component
public class JwtFilter extends AuthenticatingFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)) {
            return null;
        }
        return new JwtToken(jwt);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if (StringUtils.isEmpty(jwt)) {
            return true;
        } else {
//        校验jwt
            Claims claimByToken = jwtUtils.getClaimByToken(jwt);
            if (claimByToken == null || jwtUtils.isTokenExpired(claimByToken.getExpiration())) {
                throw new ExpiredCredentialsException("token失效,请重新登录");
            }
            return executeLogin(servletRequest, servletResponse);
        }
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        Throwable throwable = e.getCause() == null ? e : e.getCause();
        Result result=Result.fail(throwable.getMessage());
        String json= JSONUtil.toJsonStr(result);
        try {
            httpServletResponse.getWriter().write(json);
        } catch (IOException ex) {
        }
        return false;
    }
}
