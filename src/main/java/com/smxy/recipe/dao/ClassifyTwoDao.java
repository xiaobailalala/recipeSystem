/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/25 14:23
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ClassifyTwo;

import java.util.List;

public interface ClassifyTwoDao {

    List<ClassifyTwo> getInfoAll();

    Integer saveInfo(ClassifyTwo classifyTwo);

    ClassifyTwo getInfoByNameAndOid(ClassifyTwo classifyTwo);

    Integer deleteInfo(Integer fId);

    ClassifyTwo getInfoById(Integer fId);

    Integer updateInfo(ClassifyTwo classifyTwo);

    List<ClassifyTwo> getInfoByOid(Integer fOid);
}
