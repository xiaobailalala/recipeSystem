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

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @RequiresPermissions("recipe:select")
    @GetMapping("/add")
    public String add(){
        return "admin/recipe/add";
    }

}
