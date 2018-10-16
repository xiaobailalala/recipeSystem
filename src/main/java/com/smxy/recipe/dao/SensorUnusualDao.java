/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/10/15 18:04
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.SensorUnusual;

import java.util.List;

public interface SensorUnusualDao {

    Integer saveInfo(SensorUnusual sensorUnusual);

    List<SensorUnusual> getInfoAll();

    List<SensorUnusual> getInfoByUidAndTypeAndDate(SensorUnusual sensorUnusual);

}
