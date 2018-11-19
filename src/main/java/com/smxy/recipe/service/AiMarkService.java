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

    ResApi<String> saveInfo(AiMark aiMark);

    ResApi<String> deleteInfo(Integer id);

    AiMark getInfoById(Integer id);

    ResApi<String> updateInfo(Integer id, AiMark aiMark);

    ResApi<Object> getVoiceForWXReady(String readyMark, String fireMark, String smogMark, String distanceMark);
}
