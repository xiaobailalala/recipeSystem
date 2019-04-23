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
 * Build File @date: 2018/12/16 18:30
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.vueClient;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.service.ArticleService;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@PathRestController("/vue/article")
public class ArticleVueController {

    private final ArticleService articleService;
    private final CommonUserService commonUserService;
    private final RecipeService recipeService;

    @Autowired
    public ArticleVueController(ArticleService articleService, CommonUserService commonUserService, RecipeService recipeService) {
        this.articleService = articleService;
        this.commonUserService = commonUserService;
        this.recipeService = recipeService;
    }

    @GetMapping("/getInfoAndRecipeList")
    public ResApi<Object> getInfoAndRecipeList() {
        return articleService.getInfoAndRecipeList();
    }

    @GetMapping("/hotArticle")
    public ResApi<Object> hotArticle() {
        return articleService.articleListIndex();
    }

    @GetMapping("/getAllInfoByPage")
    public ResApi<Object> getAllInfoByPage(Integer index) {
        return articleService.getAllInfoByPage(index);
    }

    @GetMapping("/index")
    public ResApi<Object> articleIndex(Integer aid, Integer uid) {
        return articleService.articleIndex(aid, uid);
    }

    @GetMapping("/getArticleByUid")
    public ResApi<Object> getArticleByUid(Integer fUid) {
        return articleService.getArticleByUid(fUid);
    }

    @GetMapping("/peopleInfoDetail")
    public ResApi<Object> peopleInfoDetail(Integer uid) {
        return commonUserService.peopleInfoDetail(uid);
    }

    @GetMapping("/collectionInfo")
    public ResApi<Object> collectionInfo(Integer uid){
        return commonUserService.collectionInfo(uid);
    }

    @PostMapping("/uploadCover")
    public ResApi<Object> uploadCover(@RequestParam("cover") MultipartFile multipartFile) {
        return articleService.uploadCover(multipartFile);
    }

    @PostMapping("/saveInfo")
    public ResApi<String> saveInfo(Article article, Integer[] peopleArr, Integer[] articleArr, Integer[] recipeArr) {
        return articleService.saveInfo(article, peopleArr, articleArr, recipeArr);
    }

}
