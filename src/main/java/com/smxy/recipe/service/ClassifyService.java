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

import java.util.List;

public interface ClassifyService {

    public ResApi<Object> getAllInfo();

    public ResApi<Object> getClaOneAllInfo();

    public ResApi<Object> saveInfo(Classify classify);

    public ResApi<Object> deleteInfo(Integer id);

    public ResApi<Object> getInfoById(Integer id);

    public ResApi<Object> updateInfo(Integer id, Classify classify);

    public ResApi<Object> getInfoByTid(Integer id);
}
