/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/18 13:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.resolver;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof UnauthorizedException){
            ModelAndView mv=new ModelAndView("error/unauthorized");
            return mv;
        }
        e.printStackTrace();
        ModelAndView mv=new ModelAndView("error/unauthorized");
        mv.addObject("exception",e.toString().replaceAll("\n","<br/>"));
        return mv;
    }
}
