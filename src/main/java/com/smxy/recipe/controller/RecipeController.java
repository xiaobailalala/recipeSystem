/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/31 22:25
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    @RequiresPermissions("recipe:select")
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("data",recipeService.getAddData());
        return "admin/recipe/add";
    }

    @RequiresPermissions("recipe:select")
    @GetMapping("/add/refresh")
    @ResponseBody
    public ResApi<Object> refresh(){
        return recipeService.getAddData();
    }

    @RequiresPermissions("recipe:insert")
    @PostMapping("/info")
    @ResponseBody
    public ResApi<Object> saveInfo(@RequestParam("file") MultipartFile file, @RequestParam("processImg") MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                                   Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                   String[] stepContent, String[] stepTime, HttpServletRequest request){
        return recipeService.saveInfo(file, processImg, recipe, twoArr, threeArr, tipArr, materialNumber, materialId, materialName, stepContent, stepTime, request);
    }

    @RequiresPermissions("recipe:select")
    @GetMapping("/info")
    public String list(Model model){
        model.addAttribute("list",recipeService.getAllInfo());
        return "admin/recipe/list";
    }

    @RequiresPermissions("recipe:select")
    @GetMapping("/detailInfo/{id}")
    @ResponseBody
    public ResApi<Object> detailInfo(@PathVariable("id") Integer id){
        return recipeService.getDetailInfo(id);
    }

    @RequiresPermissions("recipe:delete")
    @DeleteMapping("/info/{id}")
    @ResponseBody
    public ResApi<Object> deleteInfo(@PathVariable("id") Integer id){
        return recipeService.deleteInfo(id);
    }

    @RequiresPermissions("recipe:select")
    @GetMapping("/editor/{id}")
    public String toEditor(@PathVariable("id") Integer id, Model model){
        model.addAttribute("item", recipeService.getOneInfo(id));
        return "admin/recipe/editor";
    }

    @RequiresPermissions("recipe:select")
    @GetMapping("/load/getData/{id}")
    @ResponseBody
    public ResApi<Object> loadGetData(@PathVariable("id") Integer id){
        return recipeService.getOneInfo(id);
    }

    @RequiresPermissions("recipe:update")
    @PutMapping("/info/{id}")
    @ResponseBody
    public ResApi<Object> updateInfo(@PathVariable("id") Integer id, @RequestParam(value = "file", required = false) MultipartFile file,
                                     @RequestParam(value = "processImg", required = false) MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                                     Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                     String[] stepContent, String[] stepTime, Integer[] stepPreid, HttpServletRequest request){
        return recipeService.updateInfo(id, file, processImg, recipe, twoArr, threeArr, tipArr, materialNumber, materialId, materialName, stepContent, stepTime, stepPreid, request);
    }

}
