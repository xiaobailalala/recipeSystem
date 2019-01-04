/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/18 16:49
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.RecipeMaterial;

import java.util.List;

public interface RecipeMaterialDao {

    Integer saveInfo(RecipeMaterial recipeMaterial);

    List<RecipeMaterial> getInfoByRid(Integer fRid);

    Integer deleteInfoByRid(Integer fRid);

    List<RecipeMaterial> getInfoByMid(Integer fMid);

}
