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

    public List<Classify> getAllInfo();

    public int saveInfo(Classify classify);

    public Classify getInfoByNameAndTid(Classify classify);

    public int deleteInfo(Integer fId);

    public Classify getInfoById(Integer fId);

    public int updateInfo(Classify classify);

    public List<Classify> getInfoByTid(Integer fTid);

}
