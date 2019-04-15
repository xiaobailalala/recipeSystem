package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantUserService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Demo MerchantUserController
 *
 * @author Yangyihui
 * @date 2018/11/11 0011 14:50
 */

@PathController("/merchant/merchantUser")
public class MerchantUserController {

    @SuppressWarnings("all")
    @Autowired
    private MerchantUserService merchantUserService;

    @RequiresRoles("merchant")
    @GetMapping("/index")
    public String goIndex() {
        return "merchant/index";
    }

    @ResponseBody
    @PostMapping("/login")
    public ResApi userLogin(MerchantUser merchantUser, HttpServletRequest request) {
        return merchantUserService.userLogin(merchantUser, request);
    }

    @ResponseBody
    @PostMapping("/register")
    public ResApi<String> userRegister(MerchantUser merchantUser, HttpServletRequest request) {
        System.out.println(merchantUser.toString());
        return merchantUserService.userRegister(merchantUser, request);
    }

    @GetMapping("/userInfo")
    public String userInfo() {
        return "merchant/pages/merchantUser/userInfo";
    }

    @RequiresRoles("merchant")
    @PostMapping("/editorImage/{id}")
    @ResponseBody
    public ResApi<String> editorImage(MultipartFile editorImage, @PathVariable("id") Integer fId, HttpServletRequest request) {
        System.out.println(editorImage.getOriginalFilename());
        return merchantUserService.editorUserCoverById(editorImage, fId, request);
    }

    @RequiresRoles("merchant")
    @PostMapping("/editorUserDetails/{id}")
    @ResponseBody
    public ResApi<String> editorUserDetails(MerchantUser merchantUser, @PathVariable("id") Integer fId) {
        return merchantUserService.editorUserDetails(merchantUser, fId);
    }

    @RequiresRoles("merchant")
    @PostMapping("/editorUserPassword/{id}")
    @ResponseBody
    public ResApi<String> editorUserPassword(String fPassword, String oldPassword, @PathVariable("id") Integer fId) {
        return merchantUserService.editorUserPassword(fPassword, oldPassword, fId);
    }

    @RequiresRoles("merchant")
    @ResponseBody
    @PostMapping("/forgetPassword")
    public ResApi<String> forgetPassword(String password, String account) {
        return merchantUserService.forgetPassword(account, password);
    }
}
