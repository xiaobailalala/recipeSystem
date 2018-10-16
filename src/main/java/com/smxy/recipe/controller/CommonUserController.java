package com.smxy.recipe.controller;

import com.smxy.recipe.service.CommonUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zpx
 */
@Controller
@RequestMapping("/commonUser")
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

}
