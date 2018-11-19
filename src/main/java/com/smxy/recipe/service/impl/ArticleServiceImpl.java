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
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ArticleDao;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.service.ArticleService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Override
    public ResApi<String> saveInfo(Article article) {
        article.setFContent(ToolsApi.base64Encode(article.getFContent()));
        articleDao.saveInfo(article);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> uploadCover(MultipartFile multipartFile) {
        String filePath = ToolsApi.multipartFileUploadFile(multipartFile, null);
        return ResApi.getSuccess(filePath);
    }
}
