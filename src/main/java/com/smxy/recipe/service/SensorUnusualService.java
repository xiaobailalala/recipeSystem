/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/10/15 18:00
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.SensorUnusual;
import com.smxy.recipe.utils.ResApi;

public interface SensorUnusualService {
    ResApi<Object> saveInfo(SensorUnusual sensorUnusual);

    ResApi<Object> showDate(SensorUnusual sensorUnusual);

    ResApi<Object> getDataByUidAndTypeAndDate(SensorUnusual sensorUnusual);
}
