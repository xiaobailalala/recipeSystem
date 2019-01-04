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

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@PathRestController("/mob/recipe")
public class RecipeMobController {

    @SuppressWarnings("all")
    @Autowired
    private RecipeService recipeService;

    @GetMapping("/getDataByClaId")
    public ResApi<Object> getDataByClaId(Integer twoid, Integer threeid){
        return recipeService.getDataByClaId(twoid, threeid);
    }

    @GetMapping("/getDataByMid")
    public ResApi<Object> getDataByMid(Integer mid) {
        return recipeService.getDataByMid(mid);
    }

    @GetMapping("/getRecipeById")
    public ResApi<Object> getRecipeById(Collect collect){
        return recipeService.getDetailInfo(collect);
    }

    @GetMapping("/updateRecipeCount")
    public ResApi<String> updateRecipeCount(Integer id){
        return recipeService.updateRecipeCount(id);
    }

    @PostMapping("/uploadProcessCover")
    public ResApi<Object> uploadProcessCover(@RequestParam("process")MultipartFile multipartFile, String index){
        return recipeService.uploadProcessCover(multipartFile, index);
    }

    @PostMapping("/uploadRecipeInfo")
    public ResApi<String> uploadRecipeInfo(@RequestParam("cover")MultipartFile multipartFile, Recipe recipe, String jsonArr) {
        return recipeService.uploadRecipeInfo(multipartFile, recipe, jsonArr);
    }

    @GetMapping("/collectRecipe")
    public ResApi<String> collectRecipe(Collect collect){
        return recipeService.saveRecipeCollection(collect);
    }

    @DeleteMapping("/collectRecipe")
    public ResApi<String> deleteRecipeCollect(Collect collect){
        return recipeService.deleteRecipeCollect(collect);
    }

    @GetMapping("/handpickList")
    public ResApi<Object> handpickList() {
        return recipeService.handpickList();
    }

}
