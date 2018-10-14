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
package com.smxy.recipe.controller.websocketOper;

import com.smxy.recipe.service.socket.RecipeSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class RecipeSocket {

    @Autowired
    RecipeSocketService recipeSocketService;

    @Scheduled(fixedRate = 1500)
    public void getSensorFireData(){
        recipeSocketService.getSensorFireData();
    }

    @Scheduled(fixedRate = 1500)
    public void getSensorSmogData(){
        recipeSocketService.getSensorSmogData();
    }
    
}
