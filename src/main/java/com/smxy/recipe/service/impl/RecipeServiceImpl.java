/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/4 22:34
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ClassifyOneDao;
import com.smxy.recipe.dao.TipsDao;
import com.smxy.recipe.entity.Tips;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    TipsDao tipsDao;
    @Autowired
    ClassifyOneDao classifyOneDao;

    @Override
    public ResApi<Object> getAddData() {
        Map<String, Object> map = new HashMap<>();
        List<Tips> tips=tipsDao.getAllInfo();
        List<Tips> tipsData=new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0,tips.size()-1,10);
        for (int item:arr){
            tipsData.add(tips.get(item));
        }
        map.put("tips", tipsData);
        map.put("clas",classifyOneDao.getAllInfo());
        return new ResApi<>(200,"success",map);
    }
}
