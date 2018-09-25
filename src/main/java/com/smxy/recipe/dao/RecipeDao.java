/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/4 22:35
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Recipe;

import java.util.List;

public interface RecipeDao {
    Integer saveInfo(Recipe recipe);

    List<Recipe> getAllInfo();

    List<Recipe> getAllInfoBre();

    Recipe getInfoById(Integer fId);

    Integer deleteInfoById(Integer fId);
}
