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
 * Build File @date: 2018/9/27 22:26
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mob/recipe")
public class RecipeMobController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("/getDataByClaId")
    public ResApi<Object> getDataByClaId(Integer twoid, Integer threeid){
        return recipeService.getDataByClaId(twoid, threeid);
    }

    @GetMapping("/getRecipeById")
    public ResApi<Object> getRecipeById(Integer id){
        return recipeService.getDetailInfo(id);
    }

    @GetMapping("/updateRecipeCount")
    public ResApi<Object> updateRecipeCount(Integer id){
        return recipeService.updateRecipeCount(id);
    }

    @PostMapping("/uploadProcessCover")
    public ResApi<Object> uploadProcessCover(@RequestParam("process")MultipartFile multipartFile, String index){
        return recipeService.uploadProcessCover(multipartFile, index);
    }

    @PostMapping("/uploadRecipeInfo")
    public ResApi<Object> uploadRecipeInfo(@RequestParam("cover")MultipartFile multipartFile, Recipe recipe, String jsonArr) {
        return recipeService.uploadRecipeInfo(multipartFile, recipe, jsonArr);
    }

}
