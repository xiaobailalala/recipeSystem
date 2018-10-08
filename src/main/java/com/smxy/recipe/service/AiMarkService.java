/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/30 21:36
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.AiMark;
import com.smxy.recipe.utils.ResApi;

public interface AiMarkService {

    ResApi<Object> getAllInfo();

    ResApi<Object> saveInfo(AiMark aiMark);

    ResApi<Object> deleteInfo(Integer id);

    AiMark getInfoById(Integer id);

    ResApi<Object> updateInfo(Integer id, AiMark aiMark);

}
