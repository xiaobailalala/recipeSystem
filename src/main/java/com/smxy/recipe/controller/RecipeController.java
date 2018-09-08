/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/31 22:25
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequiresPermissions("recipe:select")
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("data",recipeService.getAddData());
        return "admin/recipe/add";
    }

    @RequiresPermissions("recipe:select")
    @GetMapping("/add/refresh")
    @ResponseBody
    public ResApi<Object> refresh(){
        return recipeService.getAddData();
    }

}
