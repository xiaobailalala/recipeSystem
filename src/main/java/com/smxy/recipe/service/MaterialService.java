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

    public ResApi<Object> saveInfo(String fName);

    public ResApi<Object> deleteInfo(Integer id);

    public ResApi<Object> getAllInfo();

    public Material getInfoById(Integer id);

    public ResApi<Object> updateInfo(Integer id, Material material);

    public ResApi<Object> getOneByName(String fName);
}
