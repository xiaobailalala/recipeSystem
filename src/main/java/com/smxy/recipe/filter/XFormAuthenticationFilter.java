/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/15 9:04
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XFormAuthenticationFilter extends FormAuthenticationFilter {
    private static final String DEFAULT_LOGIN_URL="/adm/adLogin";

    @Override
    public void setUsernameParam(String usernameParam) {
        super.setUsernameParam(usernameParam);
    }

    @Override
    public void setPasswordParam(String passwordParam) {
        super.setPasswordParam(passwordParam);
    }

    public XFormAuthenticationFilter() {
        setLoginUrl(DEFAULT_LOGIN_URL);
    }
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                return executeLogin(request, response);
            } else {
// allow them to see the login page ;)
                return true;
            }
        } else {
// 判断session里是否有用户信息
            if (httpRequest.getHeader("X-Requested-With") != null
                    && httpRequest.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
// 如果是ajax请求响应头会有，x-requested-with
                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());

//redirectToLogin(request, response);
            } else {
                redirectToLogin(request, response);
            }
            return false;
        }
    }
}
