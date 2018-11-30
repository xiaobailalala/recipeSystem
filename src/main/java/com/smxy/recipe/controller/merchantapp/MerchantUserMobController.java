package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo MerchantUserMobController
 *
 * @author Yangyihui
 * @date 2018/11/29 0029 19:25
 */
@PathRestController("/merchantMob/merchantUserMob")
public class MerchantUserMobController {

    @PostMapping("/login")
    public ResApi<Object> userLogin(MerchantUser merchantUser){
        if (merchantUser.getFAccount().equals(merchantUser.getFPassword())){
            return ResApi.getSuccess(merchantUser);
        }else {
            return new ResApi<>(408, "用户名密码不一致", null);
        }
    }
}
