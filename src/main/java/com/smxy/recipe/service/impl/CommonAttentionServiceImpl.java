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
 * Build File @date: 2018/11/25 20:54
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.CommonAttentionDao;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.entity.CommonAttention;
import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.service.CommonAttentionService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("commonAttentionService")
public class CommonAttentionServiceImpl implements CommonAttentionService {

    private final CommonAttentionDao commonAttentionDao;

    @Autowired
    public CommonAttentionServiceImpl(CommonAttentionDao commonAttentionDao) {
        this.commonAttentionDao = commonAttentionDao;
    }

    @Override
    public ResApi<String> addAttention(CommonAttention commonAttention) {
        if (commonAttentionDao.saveInfo(commonAttention)>0) {
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<String> deleteAttention(CommonAttention commonAttention) {
        if (commonAttentionDao.deleteInfo(commonAttention)>0) {
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> attentionInfo(Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        List<CommonAttention> commonAttentions1 = commonAttentionDao.findInfoByUidAndType(uid, 1);
        List<CommonAttention> commonAttentions2 = commonAttentionDao.findInfoByOidAndType(uid, 1);
        List<Recipe> recipes = new LinkedList<>();
        List<Article> articles = new LinkedList<>();
        commonAttentions1.forEach(item -> recipes.addAll(item.getRecipes()));
        commonAttentions1.forEach(item -> item.getArticles().forEach(second -> {
            second.setFName(ToolsApi.base64Decode(second.getFName()));
            second.setFContent(ToolsApi.base64Decode(second.getFContent()));
            articles.add(second);
        }));
        map.put("recipes", recipes);
        map.put("articles", articles);
        map.put("attentionInfo", commonAttentions1);
        map.put("fansInfo", commonAttentions2);
        return ResApi.getSuccess(map);
    }
}
