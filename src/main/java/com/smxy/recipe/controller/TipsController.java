/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/1 22:25
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.service.TipsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tips")
public class TipsController {

    @Autowired
    TipsService tipsService;

    @RequiresPermissions("tips:select")
    @GetMapping("/info")
    public String list(Model model){
        model.addAttribute("list",tipsService.getAllInfo());
        return "admin/tips/list";
    }

    @RequiresPermissions("tips:select")
    @GetMapping("/add")
    public String add(){
        return "admin/tips/add";
    }

}
