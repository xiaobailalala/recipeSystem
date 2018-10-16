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
 * Build File @date: 2018/10/10 21:46
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.websocket;

import com.smxy.recipe.entity.tools.InMessage;
import com.smxy.recipe.service.socket.CommonUserSocketService;
import com.smxy.recipe.service.socket.RecipeSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class RecipeSocket {

    @Autowired
    private RecipeSocketService recipeSocketService;
    @Autowired
    private CommonUserSocketService commonUserSocketService;

    @Scheduled(fixedRate = 1500)
    public void getSensorFireData(){
        recipeSocketService.getSensorFireData();
    }

    @Scheduled(fixedRate = 1500)
    public void getSensorSmogData(){
        recipeSocketService.getSensorSmogData();
    }

    @MessageMapping("/fireListenStart")
    public void fireListenStart(InMessage inMessage){
        commonUserSocketService.fireNumberPush(inMessage);
    }

    @MessageMapping("/smogListenStart")
    public void smogListenStart(InMessage inMessage){
        commonUserSocketService.smogNumberPush(inMessage);
    }

    @MessageMapping("/fireListenStop")
    public void fireListenStop(Integer uid){
        commonUserSocketService.fireNumberPop(uid);
    }

    @MessageMapping("/smogListenStop")
    public void smogListenStop(Integer uid){
        commonUserSocketService.smogNumberPop(uid);
    }

    @MessageMapping("/allListenStop")
    public void allListenStop(Integer uid){
        commonUserSocketService.fireNumberPop(uid);
        commonUserSocketService.smogNumberPop(uid);
    }
    
}
