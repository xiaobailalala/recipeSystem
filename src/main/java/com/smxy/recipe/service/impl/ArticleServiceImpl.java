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
import com.smxy.recipe.dao.ArticleGreatDao;
import com.smxy.recipe.dao.CollectDao;
import com.smxy.recipe.dao.CommonAttentionDao;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.entity.ArticleCommentGreat;
import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.service.ArticleService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private ArticleDao articleDao;
    private RabbitTemplate rabbitTemplate;
    private ArticleGreatDao articleGreatDao;
    private CollectDao collectDao;
    private CommonAttentionDao commonAttentionDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, RabbitTemplate rabbitTemplate, ArticleGreatDao articleGreatDao, CollectDao collectDao, CommonAttentionDao commonAttentionDao) {
        this.articleDao = articleDao;
        this.rabbitTemplate = rabbitTemplate;
        this.articleGreatDao = articleGreatDao;
        this.collectDao = collectDao;
        this.commonAttentionDao = commonAttentionDao;
    }

    @Override
    public ResApi<String> saveInfo(Article article) {
        article.setFContent(ToolsApi.base64Encode(article.getFContent()));
        article.setFName(ToolsApi.base64Encode(article.getFName()));
        articleDao.saveInfo(article);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> uploadCover(MultipartFile multipartFile) {
        String filePath = ToolsApi.multipartFileUploadFile(multipartFile, null);
        return ResApi.getSuccess(filePath);
    }

    @Override
    public ResApi<Object> articleIndex(Integer aid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        Article article = articleDao.findInfoById(aid);
        article.setFName(ToolsApi.base64Decode(article.getFName()));
        article.setFContent(ToolsApi.base64Decode(article.getFContent()));
        rabbitTemplate.convertAndSend("recipeSystem.direct", "articleCountUpload.queue", aid);
        if (article.getArticleComments().size()>3){
            article.setArticleComments(article.getArticleComments().subList(0, 3));
        }
        article.getArticleComments().forEach(item -> {
            item.setFGood(item.getArticleCommentGreats().size());
            item.setFContent(ToolsApi.base64Decode(item.getFContent()));
        });
        article.getArticleComments().sort((o1, o2) -> o2.getFGood().compareTo(o1.getFGood()));
        if (!uid.equals(-1) && articleGreatDao.getByAidAndUid(aid, uid).size() != 0) {
            map.put("isGreat", true);
        } else {
            map.put("isGreat", false);
        }
        if (!uid.equals(-1) && collectDao.findByAllBrief(new Collect(uid, aid, 2)).size() != 0){
            map.put("isCollect", true);
        } else {
            map.put("isCollect", false);
        }
        if (commonAttentionDao.findInfoByUidAndOidAndType(uid, article.getCommonUser().getFId(), 1) != 0) {
            map.put("isAttention", true);
        } else {
            map.put("isAttention", false);
        }
        map.put("article", article);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> greatOperation(Integer open, Integer aid, Integer uid) {
        if (open.equals(1)) {
            articleGreatDao.saveInfo(aid, uid);
        } else {
            articleGreatDao.deleteInfo(aid, uid);
        }
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<String> collectArticle(Integer open, Collect collect) {
        if (open.equals(1)) {
            collectDao.saveInfo(collect);
        } else {
            collectDao.deleteInfo(collect);
        }
        return ResApi.getSuccess();
    }
}
