/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/1 22:47
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.TipsDao;
import com.smxy.recipe.entity.Tips;
import com.smxy.recipe.service.TipsService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("tipsService")
public class TipsServiceImpl implements TipsService {

    private final TipsDao tipsDao;

    @Autowired
    public TipsServiceImpl(TipsDao tipsDao) {
        this.tipsDao = tipsDao;
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200, "success", tipsDao.getAllInfo());
    }

    @Override
    public ResApi<Object> saveInfo(Tips tips) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (tipsDao.getInfoByName(tips.getFName()) != null) {
            resApi = new ResApi<>(501, "该标签已存在，请勿重复添加", "failed");
        } else {
            String[] arr = {"btn-default", "btn-primary", "btn-success", "btn-info", "btn-warning",
                    "btn-danger", "btn-option1", "btn-option2", "btn-option3", "btn-option4", "btn-option5", "", "btn-lignt"};
            tips.setFStyle(arr[(int)Math.floor(Math.random()*13)]);
            if (tipsDao.saveInfo(tips) > 0) {
                resApi = new ResApi<>(200, "success", tips);
            }
        }
        return resApi;
    }

    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (tipsDao.deleteInfo(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public Tips getInfoById(Integer id) {
        return tipsDao.getInfoById(id);
    }

    @Override
    public ResApi<Object> updateInfo(Integer id,Tips tips) {
        tips.setFId(id);
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (tipsDao.updateInfo(tips)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> searchInfo(String fName) {
        ResApi<Object> resApi;
        List<Tips> tips=tipsDao.searchInfo(fName);
        if (tips.size() == 0){
            resApi=new ResApi<>(501,"不存在该项","failed");
        }else{
            resApi=new ResApi<>(200,"success",tips);
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getInfoRandom() {
        List<Tips> tips = tipsDao.getAllInfo();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
        List<Tips> tipsData = new ArrayList<>();
        for (int item : arr) {
            tipsData.add(tips.get(item));
        }
        return new ResApi<>(200, "success", tipsData);
    }

}
