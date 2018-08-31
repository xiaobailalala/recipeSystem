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

    public List<ClassifyOne> getAllInfo();

    public ClassifyOne getInfoByName(String fName);

    public int saveInfo(String fName);

    public int deleteInfoById(Integer fId);

    public Classify getInfoById(Integer fId);

    public int updateInfoById(Classify classify);

}
