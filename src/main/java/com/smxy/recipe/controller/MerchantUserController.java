package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Demo MerchantUserController
 *
 * @author Yangyihui
 * @date 2018/11/11 0011 14:50
 */

@PathController("/merchant/merchantUser")
public class MerchantUserController {

    @SuppressWarnings("all")
    @Autowired
    private MerchantUserService merchantUserService;

    @GetMapping("/login")
    public String goLogin() {
        return "merchant/login";
    }

    @GetMapping("/register")
    public String goRegister() {
        return "merchant/pages/register";
    }

    @RequiresRoles("merchant")
    @GetMapping("/index")
    public String goIndex() {
        return "merchant/index";
    }

    @ResponseBody
    @PostMapping("/login")
    public ResApi userLogin(MerchantUser merchantUser, HttpServletRequest request) {
        return merchantUserService.userLogin(merchantUser, request);
    }

    @ResponseBody
    @PostMapping("/register")
    public ResApi userRegister(MerchantUser merchantUser, HttpServletRequest request) {
        return merchantUserService.userRegister(merchantUser, request);
    }
}
