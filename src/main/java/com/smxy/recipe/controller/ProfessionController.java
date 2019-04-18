package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.service.ProfessionService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author zpx
 */
@PathController("/manage/profession")
public class ProfessionController {

    private final ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @RequiresPermissions("profess:select")
    @GetMapping("/info")
    public String professList(Model model) {
        model.addAttribute("list", professionService.getAllInfo());
        return "admin/profession/list";
    }

    @RequiresPermissions("profess:select")
    @GetMapping("/toAdd")
    public String toAdd() {
        return "admin/profession/add";
    }

    @RequiresPermissions("profess:delete")
    @ResponseBody
    @DeleteMapping("/info/{id}")
    public ResApi<Object> deleteInfo(@PathVariable("id") Integer id) {
        return professionService.deleteInfo(id);
    }

    @RequiresPermissions("profess:insert")
    @ResponseBody
    @PostMapping("/info")
    public ResApi<Object> addInfo(Profession profession) {
        return professionService.saveInfo(profession);
    }

    @RequiresPermissions("profess:select")
    @GetMapping("/info/toeditor/{id}")
    public String toUpdate(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("profess", professionService.getOneById(id));
        return "admin/profession/editor";
    }

    @RequiresPermissions("profess:update")
    @ResponseBody
    @PutMapping("/info/{id}")
    public ResApi<Object> editorInfo(@PathVariable("id") Integer id, Profession profession) {
        return professionService.updateInfo(id, profession);
    }
}
