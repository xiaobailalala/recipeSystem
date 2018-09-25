/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/23 22:31
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Classify;

import java.util.List;

public interface ClassifyDao {

    List<Classify> getAllInfo();

    Integer saveInfo(Classify classify);

    Classify getInfoByNameAndTid(Classify classify);

    Integer deleteInfo(Integer fId);

    Classify getInfoById(Integer fId);

    Integer updateInfo(Classify classify);

    List<Classify> getInfoByTid(Integer fTid);

}
