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

import java.util.List;


public interface MaterialDao {

    public Material getInfoByName(String fName);

    public int saveInfo(String fName);

    public List<Material> getAllInfo();

    public int deleteInfo(Integer fId);

    public int updateInfo(Material material);

    public Material getInfoById(Integer fId);
}
