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
 * Build File @date: 2018/12/13 21:19
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.vueClient;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.CommonUser;
import com.smxy.recipe.entity.MerchantChat;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@PathRestController("/vue/commonUser")
public class CommonUserVueController {

    private final CommonUserService commonUserService;
    private final MerchantUserService merchantUserService;

    @Autowired
    public CommonUserVueController(CommonUserService commonUserService, MerchantUserService merchantUserService) {
        this.commonUserService = commonUserService;
        this.merchantUserService = merchantUserService;
    }

    @PostMapping("/userLogin")
    public ResApi<String> userLogin(CommonUser commonUser) {
        return commonUserService.userLogin(commonUser);
    }

    @PostMapping("/getUserInfoByToken")
    public ResApi<Object> getUserInfoByToken(String token, HttpServletRequest request, HttpServletResponse response) {
        return commonUserService.getUserInfoByToken(token, request, response);
    }

    @PostMapping("/userReg")
    public ResApi<String> userReg(CommonUser commonUser) {
        return commonUserService.commonUserReg(commonUser);
    }

    @GetMapping("/getUserInfoDetailByToken")
    public ResApi<Object> getUserInfoDetailByToken(String token, HttpServletRequest request, HttpServletResponse response) {
        return commonUserService.getUserInfoDetailByToken(token, request, response);
    }

    @PostMapping("/changeChatRead")
    public ResApi<String> changeChatRead(MerchantChat merchantChat) {
        return merchantUserService.changeChatRead(merchantChat);
    }

    @PostMapping("/toChatSwitch")
    public ResApi<String> toChatSwitch(@RequestParam(value = "file", required = false) MultipartFile file,
                                           MerchantChat merchantChat) {
        return merchantUserService.toMerchantSwitch(file, merchantChat);
    }

    @PostMapping("/getChatMessage")
    public ResApi<Object> getChatMessage(MerchantChat merchantChat){
        return merchantUserService.getMerchantChatMessage(merchantChat);
    }

}
