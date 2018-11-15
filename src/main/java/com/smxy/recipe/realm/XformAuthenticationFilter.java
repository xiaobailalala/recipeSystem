/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/15 9:04
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.realm;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XformAuthenticationFilter extends FormAuthenticationFilter {

    private static final String ADMIN_LOGINTYPE = LoginType.ADMIN.toString();

    private static final String MERCHANT_LOGINTYPE = LoginType.MERCHANT.toString();

    private static final String COMMON_LOGINTYPE = LoginType.COMMON.toString();


//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        HttpServletRequest httpRequest = WebUtils.toHttp(request);
//        HttpServletResponse httpResponse = WebUtils.toHttp(response);
//        if (isLoginRequest(request, response)) {
//            if (isLoginSubmission(request, response)) {
//                return executeLogin(request, response);
//            } else {
//// allow them to see the login page ;)
//                return true;
//            }
//        } else {
//// 判断session里是否有用户信息
//            String requestWith = "X-Requested-With";
//            if (httpRequest.getHeader(requestWith) != null
//                    && "XMLHttpRequest".equalsIgnoreCase(httpRequest.getHeader("X-Requested-With"))) {
//// 如果是ajax请求响应头会有，x-requested-with
//                httpResponse.sendError(HttpStatus.UNAUTHORIZED.value());
//
////redirectToLogin(request, response);
//            } else {
//                redirectToLogin(request, response);
//            }
//            return false;
//        }
//    }

    @Override
    protected UserToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        String loginType = request.getParameter("loginType");
        if (ADMIN_LOGINTYPE.equals(loginType)) {
            return new UserToken(username, password, ADMIN_LOGINTYPE);
        } else if(MERCHANT_LOGINTYPE.equals(loginType)) {
            return new UserToken(username, password, MERCHANT_LOGINTYPE);
        } else {
            return new UserToken(username, password, COMMON_LOGINTYPE);
        }
    }
}
