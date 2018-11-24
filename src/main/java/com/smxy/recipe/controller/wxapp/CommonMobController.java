/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/22 19:58
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.api.CodeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@PathRestController("/mob/common")
public class CommonMobController {

    @SuppressWarnings("all")
    @Autowired
    RecipeService recipeService;

    @PostMapping("/getCode")
    public Map<String, Object> getCode(String num) {
        Random random = new Random();
        String code = "";
        int codeCount = 6;
        for (int i = 0; i < codeCount; i++) {
            code += random.nextInt(10);
        }
        CodeApi.getRequest(CodeApi.REG_VERIFY, num, code);
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", code);
        return map;
    }

    @GetMapping("/index")
    public ResApi<Object> clientIndex(){
        return recipeService.clientIndexData();
    }

    @GetMapping("/randomRecipe")
    public ResApi<Object> randomRecipe(){
        return recipeService.randomRecipe();
    }



}
