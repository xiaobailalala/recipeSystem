package com.smxy.recipe.controller;


import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/merchant")
public class MerchantUserController {

    @Autowired
    MerchantUserService merchant_userService;


//    @RequestMapping(value = "/merchantLogin")
//    public ResApi<Object> merchantLogin(MerchantUser merchant_user){
//
//    }

}
