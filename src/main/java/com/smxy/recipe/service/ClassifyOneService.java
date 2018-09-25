/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/24 15:36
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Classify;
import com.smxy.recipe.utils.ResApi;

public interface ClassifyOneService {

    ResApi<Object> getAllInfo();

    ResApi<Object> saveInfo(String name);

    ResApi<Object> deleteInfo(Integer id);

    Classify getInfoById(Integer id);

    ResApi<Object> updateInfo(Classify classify);
}
