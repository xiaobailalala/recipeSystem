/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/8 12:29
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.Material;
import com.smxy.recipe.service.MaterialService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PathController("/manage/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @RequiresPermissions("material:insert")
    @ResponseBody
    @PostMapping("/info")
    public ResApi<Object> saveInfo(String fName) {
        return materialService.saveInfo(fName);
    }

    @RequiresPermissions("material:delete")
    @ResponseBody
    @DeleteMapping("/info/{id}")
    public ResApi<Object> deleteInfo(@PathVariable("id") Integer id) {
        return materialService.deleteInfo(id);
    }

    @RequiresPermissions("material:select")
    @GetMapping("/info")
    public String list(Model model) {
        model.addAttribute("list", materialService.getAllInfo());
        return "admin/material/list";
    }

    @RequiresPermissions("material:select")
    @GetMapping("/add")
    public String add() {
        return "admin/material/add";
    }

    @RequiresPermissions("material:select")
    @GetMapping("/editor/{id}")
    public String editor(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("item", materialService.getInfoById(id));
        return "admin/material/editor";
    }

    @RequiresPermissions("material:update")
    @ResponseBody
    @PutMapping("/info/{id}")
    public ResApi<Object> updateInfo(@PathVariable("id") Integer id, Material material) {
        return materialService.updateInfo(id, material);
    }

    @RequiresPermissions("material:select")
    @ResponseBody
    @GetMapping("/getByName")
    public ResApi<Object> getOneByName(String fName) {
        return materialService.getOneByName(fName);
    }

}
