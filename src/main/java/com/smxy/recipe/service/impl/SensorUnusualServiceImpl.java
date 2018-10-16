/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/10/15 18:10
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.SensorUnusualDao;
import com.smxy.recipe.entity.SensorUnusual;
import com.smxy.recipe.service.SensorUnusualService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("sensorUnusualService")
public class SensorUnusualServiceImpl implements SensorUnusualService {

    @Autowired
    private SensorUnusualDao sensorUnusualDao;

    @Override
    public ResApi<Object> saveInfo(SensorUnusual sensorUnusual) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (sensorUnusualDao.saveInfo(sensorUnusual) > 0){
            resApi = new ResApi<>(200, "success", "success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> showDate(SensorUnusual sensorUnusual) {
        List<SensorUnusual> dateList = sensorUnusualDao.getInfoAll();
        dateList = dateList.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(o -> o.getFDate()))), ArrayList::new)
        );
        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dateList);
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> getDataByUidAndTypeAndDate(SensorUnusual sensorUnusual) {
        List<SensorUnusual> sensorUnusuals = sensorUnusualDao.getInfoByUidAndTypeAndDate(sensorUnusual);
        Map<String, Object> map = new HashMap<>();
        map.put("list", sensorUnusuals);
        return new ResApi<>(200, "success", map);
    }
}
