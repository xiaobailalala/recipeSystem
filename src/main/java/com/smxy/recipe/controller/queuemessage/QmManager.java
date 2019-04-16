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
 * Build File @date: 2018/10/8 21:54
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.queuemessage;

import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.entity.CommonProduct;
import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.service.socket.CommonChatSocketService;
import com.smxy.recipe.service.socket.SysNotificationSocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class QmManager {

    private static final Logger logger = LoggerFactory.getLogger(QmManager.class);

    private final RecipeDao recipeDao;
    private final FoodCommentDao foodCommentDao;
    private final ArticleDao articleDao;
    private final ArticleCommentDao articleCommentDao;
    private final SysNotificationSocketService sysNotificationSocketService;
    private final CommonChatSocketService commonChatSocketService;
    private final CommonProductDao commonProductDao;

    @Autowired
    public QmManager(RecipeDao recipeDao, FoodCommentDao foodCommentDao, ArticleDao articleDao,
                     ArticleCommentDao articleCommentDao, SysNotificationSocketService sysNotificationSocketService,
                     CommonChatSocketService commonChatSocketService, CommonProductDao commonProductDao) {
        this.recipeDao = recipeDao;
        this.foodCommentDao = foodCommentDao;
        this.articleDao = articleDao;
        this.articleCommentDao = articleCommentDao;
        this.sysNotificationSocketService = sysNotificationSocketService;
        this.commonChatSocketService = commonChatSocketService;
        this.commonProductDao = commonProductDao;
    }

    @RabbitListener(queues = "recipeCountUpload.queue")
    public void updateRecipeCount(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        recipe.setFCount(recipe.getFCount() + 1);
        recipeDao.updateRecipeCount(recipe);
    }

    @RabbitListener(queues = "recipeCommentGreatUpload.queue")
    public void uploadRecipeCommentGreat(Map<String, Object> map) {
        String paramOpen = "open", paramCid = "cid", paramUid = "uid";
        if (map.get(paramOpen).equals(1)) {
            foodCommentDao.saveInfoGreat((Integer) map.get(paramCid), (Integer) map.get(paramUid));
        } else {
            foodCommentDao.deleteInfoGreat((Integer) map.get(paramCid), (Integer) map.get(paramUid));
        }
    }

    @RabbitListener(queues = "articleCountUpload.queue")
    public void uploadArticleCount(Integer id) {
        Article article = articleDao.findInfoById(id);
        article.setFCount(article.getFCount() + 1);
        articleDao.updateCount(article);
    }

    @RabbitListener(queues = "worksCountUpload.queue")
    public void uploadWorksCount(Integer id) {
        CommonProduct commonProduct = commonProductDao.queryInfoById(id);
        commonProduct.setFCount(commonProduct.getFCount() + 1);
        commonProductDao.updateCount(commonProduct);
    }

    @RabbitListener(queues = "articleCommentGreatUpload.queue")
    public void uploadArticleCommentGreat(Map<String, Object> map){
        String paramOpen = "open", paramCid = "cid", paramUid = "uid";
        if (map.get(paramOpen).equals(1)) {
            articleCommentDao.saveInfoGreat((Integer) map.get(paramUid), (Integer) map.get(paramCid));
        } else {
            articleCommentDao.deleteInfoGreat((Integer) map.get(paramUid), (Integer) map.get(paramCid));
        }
    }

    @RabbitListener(queues = "systemMessage.queue")
    public void systemMessage(String jsonStr) {
        sysNotificationSocketService.pushSystemMessageForUser(jsonStr);
    }

    @RabbitListener(queues = "chatMessage.queue")
    public void chatMessage(String jsonStr) {
        commonChatSocketService.pushChatMessageForUser(jsonStr);
    }


}
