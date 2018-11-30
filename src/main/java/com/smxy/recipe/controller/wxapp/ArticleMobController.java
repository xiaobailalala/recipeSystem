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
 * Build File @date: 2018/11/14 16:09
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.service.ArticleService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@PathRestController("/mob/article")
public class ArticleMobController {

    @SuppressWarnings("all")
    @Autowired
    private ArticleService articleService;

    @PostMapping("/saveInfo")
    public ResApi<String> saveInfo(Article article) {
        return articleService.saveInfo(article);
    }

    @PostMapping("/uploadCover")
    public ResApi<Object> uploadCover(@RequestParam("cover") MultipartFile multipartFile) {
        return articleService.uploadCover(multipartFile);
    }

    @GetMapping("/index")
    public ResApi<Object> articleIndex(Integer aid, Integer uid){
        return articleService.articleIndex(aid, uid);
    }

    @GetMapping("/greatOperation")
    public ResApi<String> greatOperation(Integer open, @RequestParam("fAid") Integer aid, @RequestParam("fUid") Integer uid){
        return articleService.greatOperation(open, aid, uid);
    }

    @GetMapping("/collectArticle")
    public ResApi<String> collectArticle(Integer open, Collect collect){
        return articleService.collectArticle(open, collect);
    }

    @GetMapping("/listIndex")
    public ResApi<Object> articleListIndex(){
        return articleService.articleListIndex();
    }

    @GetMapping("/articleForClassify")
    public ResApi<Object> articleForTopic(Integer classify){
        return articleService.articleForTopic(classify);
    }

}
