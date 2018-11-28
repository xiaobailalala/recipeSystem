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
 * Build File @date: 2018/11/25 20:39
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.CommonAttention;
import com.smxy.recipe.service.CommonAttentionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@PathRestController("/mob/attention")
public class CommonAttentionMobController {

    @SuppressWarnings("all")
    @Autowired
    private CommonAttentionService commonAttentionService;

    @PostMapping("/addAttention")
    public ResApi<String> addAttention(CommonAttention commonAttention){
        return commonAttentionService.addAttention(commonAttention);
    }

    @PostMapping("/deleteAttention")
    public ResApi<String> deleteAttention(CommonAttention commonAttention) {
        return commonAttentionService.deleteAttention(commonAttention);
    }

    @GetMapping("/attentionInfo")
    public ResApi<Object> attentionInfo(Integer uid) {
        return commonAttentionService.attentionInfo(uid);
    }

}
