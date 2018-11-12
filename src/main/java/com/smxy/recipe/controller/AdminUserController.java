/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 22:22
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.entity.AdminUser;
import com.smxy.recipe.service.AdminRoleService;
import com.smxy.recipe.service.AdminUserService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@PathController("/manage/adminUser")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;
    @Autowired
    private AdminRoleService adminRoleService;

    @RequiresPermissions("adminUser:select")
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("users", adminUserService.userList());
        return "admin/adminUser/list";
    }

    @RequiresPermissions("adminUser:delete")
    @ResponseBody
    @DeleteMapping("/list/{id}")
    public ResApi<Object> deleteUser(@PathVariable("id") Integer id) {
        return adminUserService.deleteInfo(id);
    }

    @RequiresPermissions("adminUser:update")
    @ResponseBody
    @PutMapping("/list/{id}")
    public ResApi<Object> resetUserPwd(@PathVariable("id") Integer id, AdminUser adminUser) {
        return adminUserService.resetUserPwd(id, adminUser);
    }

    @RequiresPermissions("adminRole:select")
    @GetMapping("/user/perm/{id}")
    public String userPerm(@PathVariable("id") Integer fId, Model model) {
        model.addAttribute("data", adminUserService.toUserPerm(fId));
        return "admin/adminUser/perm";
    }

    @RequiresPermissions("adminRole:delete")
    @ResponseBody
    @PostMapping("/user/perm/delete")
    public ResApi<Object> permDelete(@RequestParam("includeArr") Integer[] includeArr, @RequestParam("rid") Integer uid) {
        return adminUserService.deletePerm(includeArr, uid);
    }

    @RequiresPermissions("adminRole:insert")
    @ResponseBody
    @PostMapping("/user/perm/add")
    public ResApi<Object> permAdd(@RequestParam("excludeArr") Integer[] excludeArr, @RequestParam("rid") Integer uid) {
        return adminUserService.addPerm(excludeArr, uid);
    }

    @RequiresPermissions("adminUser:select")
    @ResponseBody
    @PostMapping("/isAcc")
    public ResApi<Object> isAcc(@RequestParam("fAccount") String account) {
        return adminUserService.isAcc(account);
    }

    @RequiresPermissions("adminUser:insert")
    @ResponseBody
    @PostMapping("/add")
    public ResApi<Object> add(AdminUser adminUser) {
        return adminUserService.saveInfo(adminUser);
    }

    @RequiresPermissions("adminUser:select")
    @GetMapping("/toadd")
    public String toAdd() {
        return "admin/adminUser/add";
    }

}
