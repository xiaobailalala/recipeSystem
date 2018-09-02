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

import java.util.Random;

@Service("tipsService")
public class TipsServiceImpl implements TipsService {

    @Autowired
    TipsDao tipsDao;

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200, "success", tipsDao.getAllInfo());
    }

    @Override
    public ResApi<Object> saveInfo(Tips tips) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (tipsDao.getInfoByName(tips.getfName()) != null) {
            resApi = new ResApi<>(501, "该标签已存在，请勿重复添加", "failed");
        } else {
            tips.setfBg(ToolsApi.getUndertint());
            tips.setfColor(ToolsApi.getDertint());
            if (tipsDao.saveInfo(tips) > 0) {
                resApi = new ResApi<>(200, "success", "success");
            }
        }
        return resApi;
    }

}
