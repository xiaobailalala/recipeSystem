/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/30 21:31
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.AiMark;

import java.util.List;

public interface AiMarkDao {

    List<AiMark> getAllInfo();

    AiMark getInfoByMark(AiMark aiMark);

    Integer saveInfo(AiMark aiMark);

    Integer deleteInfo(Integer fId);

    AiMark getInfoById(Integer fId);

    Integer updateInfoById(AiMark aiMark);

}
