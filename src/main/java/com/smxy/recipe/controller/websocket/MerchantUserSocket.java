package com.smxy.recipe.controller.websocket;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.entity.MerchantChat;
import com.smxy.recipe.service.socket.MerchantUserSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

/**
 * Demo MerchantUserSocket
 *
 * @author Yangyihui, Zhaopeixian
 * @date 2018/12/17 0017 12:52
 */
@Controller
public class MerchantUserSocket {

    private final MerchantUserSocketService merchantUserSocketService;

    @Autowired
    public MerchantUserSocket(MerchantUserSocketService merchantUserSocketService) {
        this.merchantUserSocketService = merchantUserSocketService;
    }

    @MessageMapping("/merchant/changeChatState")
    public void changeChatState(MerchantChat merchantChat) {
        merchantUserSocketService.changeChatState(merchantChat);
    }

}
