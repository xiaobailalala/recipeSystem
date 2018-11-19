/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/23 22:43
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Classify;
import com.smxy.recipe.utils.ResApi;

public interface ClassifyService {

    ResApi<Object> getAllInfo();

    ResApi<Object> getClaOneAllInfo();

    ResApi<String> saveInfo(Classify classify);

    ResApi<String> deleteInfo(Integer id);

    ResApi<Object> getInfoById(Integer id);

    ResApi<String> updateInfo(Integer id, Classify classify);

    ResApi<Object> getInfoByTid(Integer id);
}
