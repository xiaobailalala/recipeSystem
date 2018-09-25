/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/8 12:30
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Material;
import com.smxy.recipe.utils.ResApi;

public interface MaterialService {

    ResApi<Object> saveInfo(String fName);

    ResApi<Object> deleteInfo(Integer id);

    ResApi<Object> getAllInfo();

    Material getInfoById(Integer id);

    ResApi<Object> updateInfo(Integer id, Material material);

    ResApi<Object> getOneByName(String fName);
}
