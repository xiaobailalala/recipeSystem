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

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.Tips;
import com.smxy.recipe.service.TipsService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PathController("/manage/tips")
public class TipsController {

    private final TipsService tipsService;

    @Autowired
    public TipsController(TipsService tipsService) {
        this.tipsService = tipsService;
    }

    @RequiresPermissions("tips:select")
    @GetMapping("/info")
    public String list(Model model) {
        model.addAttribute("list", tipsService.getAllInfo());
        return "admin/tips/list";
    }

    @RequiresPermissions("tips:select")
    @GetMapping("/add")
    public String add() {
        return "admin/tips/add";
    }

    @RequiresPermissions("tips:insert")
    @ResponseBody
    @PostMapping("/saveInfo")
    public ResApi<Object> saveInfo(Tips tips) {
        return tipsService.saveInfo(tips);
    }

    @RequiresPermissions("tips:delete")
    @ResponseBody
    @DeleteMapping("/info/{id}")
    public ResApi<Object> deleteInfo(@PathVariable("id") Integer id) {
        return tipsService.deleteInfo(id);
    }

    @RequiresPermissions("tips:select")
    @GetMapping("/editor/{id}")
    public String editor(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("item", tipsService.getInfoById(id));
        return "admin/tips/editor";
    }

    @RequiresPermissions("tips:update")
    @ResponseBody
    @PutMapping("/info/{id}")
    public ResApi<Object> updateInfo(@PathVariable("id") Integer id, Tips tips) {
        return tipsService.updateInfo(id, tips);
    }

    @RequiresPermissions("tips:select")
    @ResponseBody
    @GetMapping("/info/searchInfo")
    public ResApi<Object> searchInfo(String fName) {
        return tipsService.searchInfo(fName);
    }


}
