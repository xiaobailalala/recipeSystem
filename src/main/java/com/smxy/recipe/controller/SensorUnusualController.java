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
 * Build File @date: 2018/10/15 17:58
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.SensorUnusual;
import com.smxy.recipe.service.SensorUnusualService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@PathController("/manage/sensorUnusual")
public class SensorUnusualController {

    @SuppressWarnings("all")
    @Autowired
    private SensorUnusualService sensorUnusualService;

    @PostMapping("/info")
    @ResponseBody
    public ResApi<Object> saveInfo(SensorUnusual sensorUnusual) {
        return sensorUnusualService.saveInfo(sensorUnusual);
    }

    @GetMapping("/showDate")
    @ResponseBody
    public ResApi<Object> showDate(SensorUnusual sensorUnusual) {
        return sensorUnusualService.showDate(sensorUnusual);
    }

    @PostMapping("/getDataByUidAndTypeAndDate")
    @ResponseBody
    public ResApi<Object> getDataByUidAndTypeAndDate(SensorUnusual sensorUnusual) {
        return sensorUnusualService.getDataByUidAndTypeAndDate(sensorUnusual);
    }

}
