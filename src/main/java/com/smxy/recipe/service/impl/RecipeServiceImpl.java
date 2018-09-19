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

import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.entity.Process;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.apache.lucene.geo3d.Tools;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @Autowired
    RecipeClassifyDao recipeClassifyDao;
    @Autowired
    RecipeDao recipeDao;
    @Autowired
    RecipeTipsDao recipeTipsDao;
    @Autowired
    RecipeMaterialDao recipeMaterialDao;
    @Autowired
    ProcessDao processDao;

    @Override
    public ResApi<Object> getAddData() {
        Map<String, Object> map = new HashMap<>();
        List<Tips> tips = tipsDao.getAllInfo();
        List<Tips> tipsData = new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
        for (int item : arr) {
            tipsData.add(tips.get(item));
        }
        map.put("tips", tipsData);
        map.put("clas", classifyOneDao.getAllInfo());
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> saveInfo(MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                                   Integer[] tipArr, String[] materialNumber, Integer[] materialId,
                                   String[] stepContent, String[] stepTime, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("aduser");
        recipe.setfAuthor(adminUser.getfUsername());
        recipe.setfRelease(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow());
        recipe.setfType(2);
        recipe.setfCover(ToolsApi.multipartFile_upload_file(file, null));
        int saveRecipe = recipeDao.saveInfo(recipe);
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (saveRecipe > 0) {
            for (Integer item : tipArr) {
                recipeTipsDao.saveInfo(new RecipeTips(recipe.getfId(), item));
            }
            for (Integer item : twoArr) {
                recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getfId(), item, 0));
            }
            for (Integer item : threeArr) {
                recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getfId(), 0, item));
            }
            for (int i = 0; i < materialId.length; i++) {
                recipeMaterialDao.saveInfo(new RecipeMaterial(recipe.getfId(), materialId[i], materialNumber[i]));
            }
            for (int i = 0; i < stepContent.length; i++) {
                processDao.saveInfo(new Process(recipe.getfId(), stepContent[i], stepTime[i], ToolsApi.multipartFile_upload_file(processImg[i], null)));
            }
            resApi = new ResApi<>(200, "success", "success");
        } else {
            resApi = new ResApi<>(501, "基本信息保存出错，请重新尝试", "failed");
        }
        return resApi;
    }
}
