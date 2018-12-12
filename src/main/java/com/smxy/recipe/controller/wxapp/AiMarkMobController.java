/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/10/11 20:34
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.realm.LoginType;
import com.smxy.recipe.realm.UserToken;
import com.smxy.recipe.service.AiMarkService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@PathRestController("/mob/aiMark")
public class AiMarkMobController {

    private static final String ADMIN_LOGIN_TYPE = LoginType.MERCHANT.toString();

    @SuppressWarnings("all")
    @Autowired
    private AiMarkService aiMarkService;

    @GetMapping("/getVoiceForWXReady")
    public ResApi<Object> getVoiceForWXReady(String readyMark, String fireMark, String smogMark, String distanceMark){
        return aiMarkService.getVoiceForWXReady(readyMark, fireMark, smogMark, distanceMark);
    }

    @PostMapping("/test")
    private ResApi<Object> test(MerchantUser merchantUser) {
        System.out.println(merchantUser.toString());
        Subject subject = SecurityUtils.getSubject();
        UserToken token = new UserToken(merchantUser.getFAccount(), merchantUser.getFPassword(), ADMIN_LOGIN_TYPE);
        subject.login(token);
        return ResApi.getSuccess(subject.getSession().getId());
    }

}
