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
 * Build File @date: 2018/10/23 19:37
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.FoodCommentDao;
import com.smxy.recipe.entity.FoodComment;
import com.smxy.recipe.entity.FoodCommentGreat;
import com.smxy.recipe.service.FoodCommentService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service("foodCommentService")
public class FoodCommentServiceImpl implements FoodCommentService {

    private static final Logger logger = LoggerFactory.getLogger(FoodCommentServiceImpl.class);

    @Autowired
    private FoodCommentDao foodCommentDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ResApi<Object> commentImgupload(MultipartFile file) {
        String filePath = ToolsApi.multipartFileUploadFile(file, null);
        return ResApi.getSuccess(filePath);
    }

    @Override
    public ResApi<String> commentSaveInfo(FoodComment foodComment) {
        foodComment.setFContent(ToolsApi.base64Encode(foodComment.getFContent()));
        Integer result = foodCommentDao.saveInfo(foodComment);
        if (result > 0) {
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getInfoByRid(Integer rid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        List<FoodComment> foodComments = foodCommentDao.getInfoByRid(rid);
        map.put("dataLen", foodComments.size());
        if (foodComments.size() > 10) {
            foodComments = foodComments.subList(0, 10);
            map.put("isAll", 0);
        } else {
            map.put("isAll", 1);
        }
        isContain(uid, foodComments);
        map.put("dataList", foodComments);
        return ResApi.getSuccess(map);
    }

    private void isContain(Integer uid, List<FoodComment> foodComments) {
        foodComments.forEach(item -> {
            item.setFGood(item.getFoodCommentGreats().size());
            item.setFContent(ToolsApi.base64Decode(item.getFContent()));
            if (!item.getFReplyid().equals(-1)) {
                item.getFoodComment().setFContent(ToolsApi.base64Decode(item.getFoodComment().getFContent()));
            }
            Optional<FoodCommentGreat> foodCommentGreatOptional = item.getFoodCommentGreats().stream().filter(greats -> greats.getFUid().equals(uid)).findFirst();
            if (foodCommentGreatOptional.isPresent()){
                item.setUserGreat(1);
            } else {
                item.setUserGreat(0);
            }
        });
    }

    static void getPagingData(Integer page, List list, Map<String, Object> map){
        if (page.equals(1)) {
            if (list.size() > 10) {
                list = list.subList(0, 10);
                map.put("isAll", 0);
            } else {
                map.put("isAll", 1);
            }
        } else {
            if (list.size() > (page * 10)) {
                list = list.subList((page - 1) * 10, page * 10);
                map.put("isAll", 0);
            } else {
                list = list.subList((page - 1) * 10, list.size());
                map.put("isAll", 1);
            }
        }
    }

    @Override
    public ResApi<Object> getInfoByRidAndPage(Integer page, Integer rid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        List<FoodComment> foodComments = foodCommentDao.getInfoByRid(rid);
        map.put("dataLen", foodComments.size());
        FoodCommentServiceImpl.getPagingData(page, foodComments, map);
        isContain(uid, foodComments);
        map.put("dataList", foodComments);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> greatOperation(Integer open, Integer cid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("open", open);
        map.put("cid", cid);
        map.put("uid", uid);
        rabbitTemplate.convertAndSend("recipeSystem.direct", "recipeCommentGreatUpload.queue", map);
        return ResApi.getSuccess();
    }
}
