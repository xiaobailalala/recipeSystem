package com.smxy.recipe.service;

import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.utils.ResApi;

import javax.servlet.http.HttpServletRequest;

/**
 * Demo MerchantUserService
 *
 * @author Yangyihui
 * @date 2018/11/12 0012 21:30
 */
public interface MerchantUserService {
    /**
     * 功能描述: 商家用户登陆
     * @param merchantUser 商家用户信息类
     * @return ResApi值
     * @auther: yangyihui
     * @date: 2018/11/12 0012 21:33
     */
    ResApi<String> userLogin(MerchantUser merchantUser, HttpServletRequest request);
    /**
     * 功能描述:商家用户注册
     * @param merchantUser 商家用户信息类
     * @return ResApi值
     * @auther: yangyihui
     * @date: 2018/11/12 0012 21:35
     */
    ResApi<String> userRegister(MerchantUser merchantUser,HttpServletRequest request);
}
