/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 16:55
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.entity.AdminUser;
import com.smxy.recipe.service.AdminUserService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage/adm")
public class AdminController {

    @Autowired
    private AdminUserService adminUserService;

    @GetMapping("/adlogout")
    public String adlogout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/manage/adm/adLogin";
    }

    @GetMapping("/adLogin")
    public String adLogin() {
        return "admin/login";
    }

    @ResponseBody
    @PostMapping("/adlogin")
    public ResApi<String> adAdd(AdminUser adminUser, boolean rememberMe, HttpServletRequest request) {
        return adminUserService.userLogin(adminUser, request, rememberMe);
    }

    @RequiresRoles(value = {"sysAdmin", "pubAdmim"}, logical = Logical.OR)
    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }

    @RequiresRoles("sysAdmin")
    @GetMapping("/editor/{id}")
    public String toEditor(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("user", adminUserService.userById(id));
        return "admin/adminUser/editor";
    }

    @RequiresRoles("sysAdmin")
    @ResponseBody
    @PutMapping("/editor/name/{id}")
    public ResApi<Object> editorUsername(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile multipartFile,
                                         @PathVariable("id") Integer id, AdminUser adminUser) {
        return adminUserService.editorInfo(request, multipartFile, id, adminUser);
    }

    @RequiresRoles("sysAdmin")
    @ResponseBody
    @PostMapping("/verify/email")
    public ResApi<Object> verifyEmail(String fEmail) {
        return adminUserService.isEmail(fEmail);
    }

    @RequiresRoles("sysAdmin")
    @ResponseBody
    @GetMapping("/verify/email/send")
    public ResApi<Object> emailSend(@RequestParam("email") String email,
                                    @RequestParam("account") String account) {
        return adminUserService.sendEmail(email, "膳食膳房——Manage 管理员邮箱绑定", "邮箱绑定", account);
    }

    @RequiresRoles("sysAdmin")
    @ResponseBody
    @PutMapping("/verify/email/{id}")
    public ResApi<Object> editorEmail(@PathVariable("id") Integer id, AdminUser adminUser) {
        return adminUserService.editorEmail(id, adminUser);
    }

    @RequiresRoles("sysAdmin")
    @ResponseBody
    @PutMapping("/reset/{id}")
    public ResApi<Object> resetPwd(@RequestParam("isRe") Boolean isRe, @RequestParam("prePassword") String prePassword, @PathVariable("id") Integer id, AdminUser adminUser) {
        return adminUserService.resetPwd(isRe, id, adminUser, prePassword);
    }

}
