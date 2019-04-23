package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.CommonLinkman;
import com.smxy.recipe.entity.MerchantChat;
import com.smxy.recipe.entity.MerchantUserLinkman;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Demo MerchantMobController
 *
 * @author Yangyihui
 * @date 2019/4/14 0014 15:52
 */
@PathRestController("/merchantMob")
public class MerchantMobController {

    private final MerchantUserService merchantUserService;

    @Autowired
    public MerchantMobController(MerchantUserService merchantUserService) {
        this.merchantUserService = merchantUserService;
    }

    @GetMapping("/getIndexData/{userId}")
    public ResApi<String> getIndexData(@PathVariable("userId") Integer userId) {
        return merchantUserService.getIndexData(userId);
    }

    @PostMapping("/toChatSwitch")
    public ResApi<Object> toMerchantSwitch(@RequestParam(value = "file", required = false)MultipartFile file,
                                           MerchantChat merchantChat) {
        return merchantUserService.toMerchantSwitch(file, merchantChat);
    }

    @PostMapping("/getChatMessage")
    public ResApi<Object> getMerchantChatMessage(MerchantChat merchantChat){
        return merchantUserService.getMerchantChatMessage(merchantChat);
    }

    @PostMapping("/changeChatRead")
    public ResApi<String> changeChatRead(MerchantChat merchantChat) {
        return merchantUserService.changeChatRead(merchantChat);
    }

    @PostMapping("/linkmanList")
    public ResApi<Object> linkmanList(@RequestBody MerchantUserLinkman merchantUserLinkman) {
        return merchantUserService.linkmanList(merchantUserLinkman);
    }

}
