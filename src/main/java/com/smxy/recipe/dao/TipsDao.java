/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/1 22:27
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Tips;

import java.util.List;

public interface TipsDao {

    List<Tips> getAllInfo();

    Integer saveInfo(Tips tips);

    Tips getInfoByName(String fName);

    Integer deleteInfo(Integer fId);

    Tips getInfoById(Integer fId);

    Integer updateInfo(Tips tips);

    List<Tips> searchInfo(String fName);

}
