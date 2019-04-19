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
import org.springframework.web.multipart.MultipartFile;

@PathController("/manage/material")
public class MaterialController {

    private final MaterialService materialService;

    @Autowired
    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @RequiresPermissions("material:insert")
    @ResponseBody
    @PostMapping("/info")
    public ResApi<String> saveInfo(@RequestParam("file") MultipartFile multipartFile, String fName) {
        return materialService.saveInfo(multipartFile, fName);
    }

    @RequiresPermissions("material:delete")
    @ResponseBody
    @DeleteMapping("/info/{id}")
    public ResApi<String> deleteInfo(@PathVariable("id") Integer id, String filePath) {
        return materialService.deleteInfo(id, filePath);
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
    public ResApi<String> updateInfo(@PathVariable("id") Integer id, @RequestParam("file") MultipartFile file, Material material) {
        return materialService.updateInfo(id, file, material);
    }

    @RequiresPermissions("material:select")
    @ResponseBody
    @GetMapping("/getByName")
    public ResApi<Object> getOneByName(String fName) {
        return materialService.getOneByName(fName);
    }

}
