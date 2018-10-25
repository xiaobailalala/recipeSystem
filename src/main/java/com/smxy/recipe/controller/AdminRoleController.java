/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/16 16:23
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.entity.AdminRole;
import com.smxy.recipe.service.AdminRoleService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manage/adminRole")
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @RequiresPermissions("adminRole:select")
    @GetMapping("/role")
    public String list(Model model){
        model.addAttribute("list",adminRoleService.roleList());
        return "admin/adminRole/list";
    }

    @RequiresPermissions("adminRole:select")
    @GetMapping("/add")
    public String add(){
        return "admin/adminRole/add";
    }

    @RequiresPermissions("adminRole:select")
    @ResponseBody
    @PostMapping("/isName")
    public ResApi<Object> isNmae(AdminRole adminRole){
        return adminRoleService.isName(adminRole);
    }

    @RequiresPermissions("adminRole:insert")
    @ResponseBody
    @PostMapping("/role")
    public ResApi<Object> saveRole(AdminRole adminRole){
        return adminRoleService.saveRole(adminRole);
    }

    @RequiresPermissions("adminRole:select")
    @GetMapping("/role/toeditor/{id}")
    public String toEditor(@PathVariable("id") Integer id, Model model){
        model.addAttribute("role",adminRoleService.getOneById(id));
        return "admin/adminRole/editor";
    }

    @RequiresPermissions("adminRole:update")
    @ResponseBody
    @PutMapping("/role/{id}")
    public ResApi<Object> updateInfo(@PathVariable("id") Integer id, AdminRole adminRole){
        return adminRoleService.updateRole(id, adminRole);
    }

    @RequiresPermissions("adminRole:delete")
    @ResponseBody
    @DeleteMapping("/role/{id}")
    public ResApi<Object> deleteInfo(@PathVariable("id") Integer id){
        return adminRoleService.deleteRole(id);
    }

    @RequiresPermissions("adminRole:select")
    @ResponseBody
    @GetMapping("/role/getPerm/{id}")
    public AdminRole getPerm(@PathVariable("id") Integer id){
        return adminRoleService.getOneById(id);
    }

    @RequiresPermissions("adminRole:select")
    @GetMapping("/role/perm/{id}")
    public String roleToPerm(@PathVariable("id") Integer id, Model model){
        model.addAttribute("data",adminRoleService.toPerm(id));
        return "admin/adminRole/perm";
    }

    @RequiresPermissions("adminRole:delete")
    @ResponseBody
    @PostMapping("/role/perm/delete")
    public ResApi<Object> permDelete(@RequestParam("includeArr")Integer[] includeArr,@RequestParam("rid")Integer rid){
        return adminRoleService.deletePerm(includeArr,rid);
    }

    @RequiresPermissions("adminRole:insert")
    @ResponseBody
    @PostMapping("/role/perm/add")
    public ResApi<Object> permAdd(@RequestParam("excludeArr")Integer[] excludeArr,@RequestParam("rid")Integer rid){
        return adminRoleService.addPerm(excludeArr,rid);
    }


}
