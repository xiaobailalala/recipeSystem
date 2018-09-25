/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/18 16:48
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.RecipeClassify;

import java.util.List;

public interface RecipeClassifyDao {

    Integer saveInfo(RecipeClassify recipeClassify);

    List<RecipeClassify> getInfoByRid(Integer fRid);

    Integer deleteInfoByRid(Integer fRid);

}
