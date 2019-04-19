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
 * Build File @date: 2018/12/13 22:12
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.service.SysNotificationService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@PathRestController("/mob/sysNotification")
public class SysNotificationMobController {

    private final SysNotificationService sysNotificationService;

    @Autowired
    public SysNotificationMobController(SysNotificationService sysNotificationService) {
        this.sysNotificationService = sysNotificationService;
    }

    @PostMapping("/showMessage")
    public ResApi<Object> showMessage(Integer uid) {
        return sysNotificationService.showMessage(uid);
    }

    @PostMapping("/showMessageCount")
    public ResApi<Object> showMessageCount(Integer uid) {
        return sysNotificationService.showMessageCount(uid);
    }

    @GetMapping("/deleteMessage")
    public ResApi<String> deleteMessage(Integer uid) {
        return sysNotificationService.deleteMessage(uid);
    }

}
