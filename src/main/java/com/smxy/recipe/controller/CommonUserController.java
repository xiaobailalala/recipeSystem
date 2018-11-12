package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.CommonUser;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zpx
 */
@PathController("/manage/commonUser")
public class CommonUserController {

    @Autowired
    private CommonUserService commonUserService;

    @RequiresPermissions("commonUser:select")
    @GetMapping("/info")
    public String list(Model model) {
        model.addAttribute("list", commonUserService.getAllInfo());
        return "admin/commonUser/list";
    }

    @RequiresPermissions("commonUser:monitor")
    @GetMapping("/monitor/{id}")
    public String monitor(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("item", commonUserService.getOneById(id));
        return "admin/commonUser/monitor";
    }

    @RequiresPermissions("commonUser:select")
    @GetMapping("/add")
    public String add() {
        return "admin/commonUser/add";
    }

    @RequiresPermissions("commonUser:insert")
    @PostMapping("/info")
    @ResponseBody
    public ResApi<String> saveInfo(CommonUser commonUser) {
        return commonUserService.commonUserReg(commonUser);
    }

    @RequiresPermissions("commonUser:update")
    @PostMapping("/repwd")
    @ResponseBody
    public ResApi<Object> rePwd(CommonUser commonUser) {
        return commonUserService.updateCommonUserPwd(commonUser);
    }

}
