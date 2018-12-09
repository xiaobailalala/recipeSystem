/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/8 12:33
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MaterialDao {

    Material getInfoByName(String fName);

    Integer saveInfo(@Param("fName") String name, @Param("fCover") String cover);

    List<Material> getAllInfo();

    Integer deleteInfo(Integer fId);

    Integer updateInfo(Material material);

    Material getInfoById(Integer fId);

    List<Material> getDataByVagueName(String fName);
}
