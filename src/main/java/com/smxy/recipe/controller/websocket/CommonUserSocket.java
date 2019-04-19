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
 * Build File @date: 2018/10/12 22:11
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.websocket;

import com.smxy.recipe.entity.CommonChat;
import com.smxy.recipe.entity.MerchantChat;
import com.smxy.recipe.service.socket.CommonUserSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class CommonUserSocket {

    private final CommonUserSocketService commonUserSocketService;

    @Autowired
    public CommonUserSocket(CommonUserSocketService commonUserSocketService) {
        this.commonUserSocketService = commonUserSocketService;
    }

    @MessageMapping("/user/changeChatState")
    public void changeChatState(CommonChat commonChat) {
        commonUserSocketService.changeChatState(commonChat);
    }

    @Scheduled(fixedRate = 1000)
    public void sendFireNumber() {
        commonUserSocketService.sendFireNumber();
    }

    @Scheduled(fixedRate = 1000)
    public void sendSmogNumber() {
        commonUserSocketService.sendSmogNumber();
    }

    @Scheduled(fixedRate = 1000)
    public void sendInfraredNumber() {
        commonUserSocketService.sendInfraredNumber();
    }

    @Scheduled(fixedRate = 1000)
    public void sendDistanceNumber() {
        commonUserSocketService.sendDistanceNumber();
    }

}
