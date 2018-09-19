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

    public ResApi<Object> getAllInfo();

    public ResApi<Object> saveInfo(Tips tips);

    public ResApi<Object> deleteInfo(Integer id);

    public Tips getInfoById(Integer id);

    public ResApi<Object> updateInfo(Integer id, Tips tips);

    public ResApi<Object> searchInfo(String fName);
}
