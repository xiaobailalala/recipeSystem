/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/15 16:25
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.AdminPermission;
import com.smxy.recipe.service.AdminPermissionService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PathController("/manage/adminPermission")
public class AdminPermissionController {

    @SuppressWarnings("all")
    @Autowired
    private AdminPermissionService adminPermissionService;

    @RequiresPermissions("adminPermission:select")
    @GetMapping("/perm")
    public String list(Model model){
        model.addAttribute("list",adminPermissionService.permissionList());
        return "admin/adminPermission/list";
    }

    @RequiresPermissions("adminPermission:select")
    @GetMapping("/add")
    public String add(){
        return "admin/adminPermission/add";
    }

    @RequiresPermissions("adminPermission:select")
    @ResponseBody
    @PostMapping("/isName")
    public ResApi<String> isName(AdminPermission adminPermission){
        return adminPermissionService.isName(adminPermission);
    }

    @RequiresPermissions("adminPermission:insert")
    @ResponseBody
    @PostMapping("/perm")
    public ResApi<String> saveInfo(AdminPermission adminPermission){
        return adminPermissionService.saveInfo(adminPermission);
    }

    @RequiresPermissions("adminPermission:delete")
    @ResponseBody
    @DeleteMapping("/perm/{id}")
    public ResApi<String> deleteInfo(@PathVariable("id") Integer id){
        return adminPermissionService.deleteInfo(id);
    }

    @RequiresPermissions("adminPermission:select")
    @GetMapping("/perm/toeditor/{id}")
    public String toEditor(@PathVariable("id") Integer id, Model model){
        model.addAttribute("perm",adminPermissionService.getOneByFid(id));
        return "admin/adminPermission/editor";
    }

    @RequiresPermissions("adminPermission:update")
    @ResponseBody
    @PutMapping("/perm/{id}")
    public ResApi<String> updateInfo(@PathVariable("id") Integer id, AdminPermission adminPermission){
        return adminPermissionService.updateInfo(id,adminPermission);
    }

}
