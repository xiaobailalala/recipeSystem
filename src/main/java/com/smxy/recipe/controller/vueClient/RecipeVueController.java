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
 * Build File @date: 2018/12/13 16:29
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.vueClient;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.service.*;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@PathRestController("/vue/recipe")
public class RecipeVueController {

    private final RecipeService recipeService;
    private final MaterialService materialService;
    private final TipsService tipsService;
    private final ClassifyTwoService classifyTwoService;
    private final ClassifyService classifyService;

    @Autowired
    public RecipeVueController(RecipeService recipeService, MaterialService materialService, TipsService tipsService, ClassifyTwoService classifyTwoService, ClassifyService classifyService) {
        this.recipeService = recipeService;
        this.materialService = materialService;
        this.tipsService = tipsService;
        this.classifyTwoService = classifyTwoService;
        this.classifyService = classifyService;
    }

    @GetMapping("/getRecipeInfoByClaId")
    public ResApi<Object> getRecipeInfoByClaId(Integer twoId, Integer threeId) {
        return recipeService.getDataByClaId(twoId, threeId);
    }

    @GetMapping("/getDataByMid")
    public ResApi<Object> getDataByMid(Integer mid) {
        return recipeService.getDataByMid(mid);
    }

    @GetMapping("/hotRecipe")
    public ResApi<Object> hotRecipe() {
        return recipeService.randomRecipe();
    }

    @GetMapping("/see-more")
    public ResApi<Object> seeMore() {
        return recipeService.randomRecipeAndArticle();
    }

    @GetMapping("/getDataByVagueName")
    public ResApi<Object> getDataByVagueName(String name) {
        return materialService.getDataByVagueName(name);
    }

    @GetMapping("/hotGroup")
    public ResApi<Object> hotGroup() {
        return recipeService.hotGroup();
    }

    @GetMapping("/getRecipeById")
    public ResApi<Object> getRecipeById(Collect collect){
        return recipeService.getDetailInfo(collect);
    }

    @GetMapping("/getRecipeByUid")
    public ResApi<Object> getRecipeByUid(Integer fUid) {
        return recipeService.getRecipeByUid(fUid);
    }

    @GetMapping("/updateRecipeCount")
    public ResApi<String> updateRecipeCount(Integer id){
        return recipeService.updateRecipeCount(id);
    }

    @GetMapping("/add/refresh")
    public ResApi<Object> refresh() {
        return recipeService.getAddData();
    }

    @GetMapping("/info/searchInfo")
    public ResApi<Object> searchInfo(String fName) {
        return tipsService.searchInfo(fName);
    }

    @PostMapping("/getbyoid/{id}")
    public ResApi<Object> getByOid(@PathVariable("id") Integer fOid) {
        return classifyTwoService.getInfoByOid(fOid);
    }

    @PostMapping("/getByTid/{id}")
    public ResApi<Object> getByTid(@PathVariable("id") Integer id) {
        return classifyService.getInfoByTid(id);
    }

    @GetMapping("/material/getByName")
    public ResApi<Object> getOneByName(String fName) {
        return materialService.getOneByName(fName);
    }

}
