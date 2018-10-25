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
import com.smxy.recipe.service.FoodCommentService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("foodCommentService")
public class FoodCommentServiceImpl implements FoodCommentService {

    @Autowired
    FoodCommentDao foodCommentDao;

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
    public ResApi<Object> getInfoByRid(Integer rid) {
        Map<String, Object> map = new HashMap<>(8);
        List<FoodComment> foodComments = foodCommentDao.getInfoByRid(rid);
        map.put("dataLen", foodComments.size());
        if (foodComments.size() > 10) {
            foodComments = foodComments.subList(0, 10);
            map.put("isAll", 0);
        } else {
            map.put("isAll", 1);
        }
        map.put("dataList", foodComments);
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> getInfoByRidAndPage(Integer page, Integer rid) {
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
                foodComments = foodComments.subList(0, page * 10);
                map.put("isAll", 0);
            } else {
                map.put("isAll", 1);
            }
        }
        map.put("dataList", foodComments);
        return new ResApi<>(200, "success", map);
    }
}
