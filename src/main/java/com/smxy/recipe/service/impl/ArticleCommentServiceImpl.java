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
 * Build File @date: 2018/11/23 20:21
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ArticleCommentDao;
import com.smxy.recipe.entity.ArticleComment;
import com.smxy.recipe.entity.ArticleCommentGreat;
import com.smxy.recipe.service.ArticleCommentService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Transactional(rollbackFor = Exception.class)
@Service("articleCommentService")
public class ArticleCommentServiceImpl implements ArticleCommentService {

    private final ArticleCommentDao articleCommentDao;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ArticleCommentServiceImpl(ArticleCommentDao articleCommentDao, RabbitTemplate rabbitTemplate) {
        this.articleCommentDao = articleCommentDao;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public ResApi<Object> getInfoByRid(Integer rid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        List<ArticleComment> articleComments = articleCommentDao.getInfoByAid(rid);
        map.put("dataLen", articleComments.size());
        if (articleComments.size() > 10) {
            articleComments = articleComments.subList(0, 10);
            map.put("isAll", 0);
        } else {
            map.put("isAll", 1);
        }
        isContain(uid, articleComments);
        map.put("dataList", articleComments);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> greatOperation(Integer open, Integer cid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("open", open);
        map.put("cid", cid);
        map.put("uid", uid);
        rabbitTemplate.convertAndSend("recipeSystem.direct", "articleCommentGreatUpload.queue", map);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> getInfoByRidAndPage(Integer page, Integer rid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        List<ArticleComment> articleComments = articleCommentDao.getInfoByAid(rid);
        map.put("dataLen", articleComments.size());
        FoodCommentServiceImpl.getPagingData(page, articleComments, map);
        isContain(uid, articleComments);
        map.put("dataList", articleComments);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> commentSaveInfo(ArticleComment articleComment) {
        articleComment.setFContent(ToolsApi.base64Encode(articleComment.getFContent()));
        Integer result = articleCommentDao.saveInfo(articleComment);
        if (result > 0) {
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> commentImgUpload(MultipartFile file) {
        String filePath = ToolsApi.multipartFileUploadFile(file, null);
        return ResApi.getSuccess(filePath);
    }

    private void isContain(Integer uid, List<ArticleComment> articleComments) {
        articleComments.forEach(item -> {
            item.setFGood(item.getArticleCommentGreats().size());
            item.setFContent(ToolsApi.base64Decode(item.getFContent()));
            if (!item.getFReplyid().equals(-1)) {
                item.getArticleComment().setFContent(ToolsApi.base64Decode(item.getArticleComment().getFContent()));
            }
            Optional<ArticleCommentGreat> articleCommentGreatOptional = item.getArticleCommentGreats().stream().filter(greats -> greats.getFUid().equals(uid)).findFirst();
            if (articleCommentGreatOptional.isPresent()){
                item.setUserGreat(1);
            } else {
                item.setUserGreat(0);
            }
        });
    }
}
