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
 * Build File @date: 2018/11/14 16:14
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Article;
import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

public interface ArticleService {
    ResApi<String> saveInfo(Article article, Integer[] peopleArr, Integer[] articleArr, Integer[] recipeArr);

    ResApi<Object> uploadCover(MultipartFile multipartFile);

    ResApi<Object> articleIndex(Integer aid, Integer uid);

    ResApi<String> greatOperation(Integer open, Integer aid, Integer uid);

    ResApi<String> collectArticle(Integer open, Collect collect);

    ResApi<Object> articleListIndex();

    ResApi<Object> articleForTopic(Integer classify);

    ResApi<Object> handpickList();

    ResApi<Object> getInfoAndRecipeList();

    ResApi<Object> getAllInfoByPage(Integer index);

    ResApi<Object> getArticleByUid(Integer fUid);

    ResApi<Object> referArticle();
}
