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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return new ResApi<>(200, "success", filePath);
    }

    @Override
    public ResApi<Object> commentSaveInfo(FoodComment foodComment) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        Integer result = foodCommentDao.saveInfo(foodComment);
        if (result > 0) {
            resApi = new ResApi<>(200, "success", "success");
        }
        return resApi;
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
        return new ResApi<>(200, "success", map);
    }

    private void isContain(Integer uid, List<FoodComment> foodComments) {
        foodComments.forEach(item -> {
            item.setFGood(item.getFoodCommentGreats().size());
            Optional<FoodCommentGreat> foodCommentGreatOptional = item.getFoodCommentGreats().stream().filter(greats -> greats.getFUid().equals(uid)).findFirst();
            if (foodCommentGreatOptional.isPresent()){
                item.setUserGreat(1);
            } else {
                item.setUserGreat(0);
            }
        });
    }

    @Override
    public ResApi<Object> getInfoByRidAndPage(Integer page, Integer rid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        List<FoodComment> foodComments = foodCommentDao.getInfoByRid(rid);
        map.put("dataLen", foodComments.size());
        if (page.equals(1)) {
            if (foodComments.size() > 10) {
                foodComments = foodComments.subList(0, 10);
                map.put("isAll", 0);
            } else {
                map.put("isAll", 1);
            }
        } else {
            if (foodComments.size() > (page * 10)) {
                foodComments = foodComments.subList((page - 1) * 10, page * 10);
                map.put("isAll", 0);
            } else {
                foodComments = foodComments.subList((page - 1) * 10, foodComments.size());
                map.put("isAll", 1);
            }
        }
        isContain(uid, foodComments);
        map.put("dataList", foodComments);
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> greatOperation(Integer open, Integer cid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("open", open);
        map.put("cid", cid);
        map.put("uid", uid);
        rabbitTemplate.convertAndSend("recipeSystem.direct", "recipeCommentGreatUpload.queue", map);
        return new ResApi<>(200, "success", "success");
    }
}
