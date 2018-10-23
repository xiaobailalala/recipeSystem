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
        if (result > 0){
            resApi = new ResApi<>(200, "success", "success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getInfoByRid(Integer rid) {
        return new ResApi<>(200, "success", foodCommentDao.getInfoByRid(rid));
    }
}
