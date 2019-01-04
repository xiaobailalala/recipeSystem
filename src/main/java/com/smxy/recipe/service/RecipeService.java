/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/4 22:34
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface RecipeService {

    ResApi<Object> getAddData();
    ResApi<String> saveInfo(MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                            Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                            String[] stepContent, String[] stepTime);

    ResApi<Object> getAllInfo();

    ResApi<Object> getDetailInfo(Collect collect);

    ResApi<Object> getDetailInfo(Integer id);

    ResApi<String> deleteInfo(Integer id);

    ResApi<Object> getOneInfo(Integer id);

    ResApi<String> updateInfo(Integer id, MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr, Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName, String[] stepContent, String[] stepTime, Integer[] stepPreid, HttpServletRequest request);

    ResApi<Object> getDataByClaId(Integer twoid, Integer threeid);

    ResApi<String> updateRecipeCount(Integer id);

    ResApi<Object> uploadProcessCover(MultipartFile multipartFile, String index);

    ResApi<String> uploadRecipeInfo(MultipartFile multipartFile, Recipe recipe, String jsonArr);

    ResApi<String> saveRecipeCollection(Collect collect);

    ResApi<String> deleteRecipeCollect(Collect collect);

    ResApi<Object> clientIndexData();

    ResApi<Object> randomRecipe();


    ResApi<Object> handpickList();

    ResApi<Object> getDataByMid(Integer mid);
}
