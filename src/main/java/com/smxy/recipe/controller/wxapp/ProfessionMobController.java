/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/22 19:41
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.service.ProfessionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@PathRestController("/mob/profession")
public class ProfessionMobController {

    @Autowired
    private ProfessionService professionService;

    @GetMapping("/getAllInfo")
    public ResApi<List<Profession>> getAllInfo(){
        return professionService.getAllInfo();
    }
}
