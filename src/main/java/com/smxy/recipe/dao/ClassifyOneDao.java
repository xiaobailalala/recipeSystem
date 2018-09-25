/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/24 15:43
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Classify;
import com.smxy.recipe.entity.ClassifyOne;

import java.util.List;

public interface ClassifyOneDao {

    List<ClassifyOne> getAllInfo();

    ClassifyOne getInfoByName(String fName);

    Integer saveInfo(String fName);

    Integer deleteInfoById(Integer fId);

    Classify getInfoById(Integer fId);

    Integer updateInfoById(Classify classify);

}
