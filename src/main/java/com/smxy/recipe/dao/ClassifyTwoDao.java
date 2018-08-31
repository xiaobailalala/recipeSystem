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

    public List<ClassifyTwo> getInfoAll();

    public int saveInfo(ClassifyTwo classifyTwo);

    public ClassifyTwo getInfoByNameAndOid(ClassifyTwo classifyTwo);

    public int deleteInfo(Integer fId);

    public ClassifyTwo getInfoById(Integer fId);

    public int updateInfo(ClassifyTwo classifyTwo);

    public List<ClassifyTwo> getInfoByOid(Integer fOid);
}
