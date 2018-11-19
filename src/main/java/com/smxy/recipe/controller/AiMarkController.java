/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/30 21:37
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.AiMark;
import com.smxy.recipe.service.AiMarkService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@PathController("/manage/aiMark")
public class AiMarkController {

    @Autowired
    private AiMarkService aiMarkService;

    @RequiresPermissions("aiMark:select")
    @GetMapping("/info")
    public String list(Model model) {
        model.addAttribute("list", aiMarkService.getAllInfo());
        return "admin/ai/mark/list";
    }

    @RequiresPermissions("aiMark:select")
    @GetMapping("/add")
    public String add() {
        return "admin/ai/mark/add";
    }

    @RequiresPermissions("aiMark:insert")
    @PostMapping("/info")
    @ResponseBody
    public ResApi<String> saveInfo(AiMark aiMark) {
        return aiMarkService.saveInfo(aiMark);
    }

    @RequiresPermissions("aiMark:delete")
    @DeleteMapping("/info/{id}")
    @ResponseBody
    public ResApi<String> deleteInfo(@PathVariable("id") Integer id) {
        return aiMarkService.deleteInfo(id);
    }

    @RequiresPermissions("aiMark:select")
    @GetMapping("/editor/{id}")
    public String editor(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("item", aiMarkService.getInfoById(id));
        return "admin/ai/mark/editor";
    }

    @RequiresPermissions("aiMark:update")
    @PutMapping("/info/{id}")
    @ResponseBody
    public ResApi<String> updateInfo(@PathVariable("id") Integer id, AiMark aiMark) {
        return aiMarkService.updateInfo(id, aiMark);
    }

}
