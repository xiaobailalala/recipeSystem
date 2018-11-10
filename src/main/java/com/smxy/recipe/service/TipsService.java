/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/1 22:46
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Tips;
import com.smxy.recipe.utils.ResApi;

public interface TipsService {

    ResApi<Object> getAllInfo();

    ResApi<Object> saveInfo(Tips tips);

    ResApi<Object> deleteInfo(Integer id);

    Tips getInfoById(Integer id);

    ResApi<Object> updateInfo(Integer id, Tips tips);

    ResApi<Object> searchInfo(String fName);

    ResApi<Object> getInfoRandom();
}
