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

import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public interface RecipeService {

    ResApi<Object> getAddData();
    ResApi<Object> saveInfo(MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                                   Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                   String[] stepContent, String[] stepTime, HttpServletRequest request);

    ResApi<Object> getAllInfo();

    ResApi<Object> getDetailInfo(Integer id);

    ResApi<Object> deleteInfo(Integer id);

    ResApi<Object> getOneInfo(Integer id);

    ResApi<Object> updateInfo(Integer id, MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr, Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName, String[] stepContent, String[] stepTime, Integer[] stepPreid, HttpServletRequest request);

    ResApi<Object> getDataByClaId(Integer twoid, Integer threeid);

    ResApi<Object> updateRecipeCount(Integer id);
}
