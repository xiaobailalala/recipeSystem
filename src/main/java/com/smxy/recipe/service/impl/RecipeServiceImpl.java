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
import com.smxy.recipe.entity.ToolsEntity.RecipeClassifyList;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    ClassifyTwoDao classifyTwoDao;
    @Autowired
    ClassifyDao classifyDao;

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
                                   Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                   String[] stepContent, String[] stepTime, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("aduser");
        recipe.setFAuthor(adminUser.getFUsername());
        recipe.setFRelease(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow());
        recipe.setFCover(ToolsApi.multipartFile_upload_file(file, null));
        int saveRecipe = recipeDao.saveInfo(recipe);
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (saveRecipe > 0) {
            for (Integer item : tipArr) {
                recipeTipsDao.saveInfo(new RecipeTips(recipe.getFId(), item));
            }
            for (Integer item : twoArr) {
                recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getFId(), item, 0));
            }
            for (Integer item : threeArr) {
                recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getFId(), 0, item));
            }
            for (int i = 0; i < materialId.length; i++) {
                recipeMaterialDao.saveInfo(new RecipeMaterial(recipe.getFId(), materialId[i], materialNumber[i], materialName[i]));
            }
            for (int i = 0; i < stepContent.length; i++) {
                String fileName;
                if (processImg[i] == null || processImg[i].getSize() == 0) {
                    fileName = null;
                } else {
                    fileName = ToolsApi.multipartFile_upload_file(processImg[i], null);
                }
                processDao.saveInfo(new Process(recipe.getFId(), stepContent[i], stepTime[i], fileName));
            }
            resApi = new ResApi<>(200, "success", "success");
        } else {
            resApi = new ResApi<>(501, "基本信息保存出错，请重新尝试", "failed");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200, "success", recipeDao.getAllInfoBre());
    }

    @Override
    public ResApi<Object> getDetailInfo(Integer id) {
        return new ResApi<>(200, "success", recipeDao.getInfoById(id));
    }

    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        ToolsApi.multipartFile_delete_file(recipe.getFCover());
        recipe.getProcesses().forEach(item -> {
            ToolsApi.multipartFile_delete_file(item.getFCover());
        });
        processDao.deleteInfoByRid(id);
        recipeClassifyDao.deleteInfoByRid(id);
        recipeMaterialDao.deleteInfoByRid(id);
        recipeTipsDao.deleteInfoByRid(id);
        recipeDao.deleteInfoById(id);
        return new ResApi<>(200, "success", "success");
    }

    @Override
    public ResApi<Object> getOneInfo(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        Map<String, Object> map = new HashMap<>();
        List<Tips> tips = tipsDao.getAllInfo();
        List<Tips> tipsData = new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
        for (int item : arr) {
            tipsData.add(tips.get(item));
        }
        map.put("item", recipe);
        map.put("tips", tipsData);
        List<RecipeClassifyList> recipeClassifyLists = new ArrayList<>();
        List<ClassifyOne> classifyOnes = classifyOneDao.getAllInfo();
        recipe.getRecipeClassifies().forEach(item -> {
            List<ClassifyTwo> classifyTwos;
            List<Classify> classifies;
            Integer one, two, three;
            if (item.getFTwoId() == 0) {
                classifyTwos = classifyTwoDao.getInfoByOid(item.getClassify().
                        getClassifyTwo().getClassifyOne().getFId());
                classifies = classifyDao.getInfoByTid(item.getClassify().
                        getClassifyTwo().getFId());
                one = item.getClassify().getClassifyTwo().getClassifyOne().getFId();
                two = item.getClassify().getClassifyTwo().getFId();
                three = item.getClassify().getFId();
            } else {
                classifyTwos = classifyTwoDao.getInfoByOid(item.getClassifyTwo().getClassifyOne().getFId());
                classifies = classifyDao.getInfoByTid(item.getClassifyTwo().getFId());
                one = item.getClassifyTwo().getClassifyOne().getFId();
                two = item.getClassifyTwo().getFId();
                three = 0;
            }
            recipeClassifyLists.add(new RecipeClassifyList(classifyOnes, classifyTwos, classifies, one, two, three));
        });
        map.put("clas", recipeClassifyLists);
        map.put("materials", recipeMaterialDao.getInfoByRid(recipe.getFId()));
        map.put("processes", processDao.getInfoByRid(recipe.getFId()));
        return new ResApi<>(200, "success", map);
    }
}
