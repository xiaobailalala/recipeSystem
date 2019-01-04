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
import com.smxy.recipe.service.ArticleService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@PathRestController("/vue/article")
public class ArticleVueController {

    @SuppressWarnings("all")
    @Autowired
    private ArticleService articleService;

    @GetMapping("/getInfoAndRecipeList")
    public ResApi<Object> getInfoAndRecipeList() {
        return articleService.getInfoAndRecipeList();
    }

}
