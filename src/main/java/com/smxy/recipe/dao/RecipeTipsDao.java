/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/18 20:15
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.RecipeTips;

import java.util.List;

public interface RecipeTipsDao {
    Integer saveInfo(RecipeTips recipeTips);
    List<RecipeTips> getInfoByRid(Integer fRid);
    Integer deleteInfoByRid(Integer fRid);
}
