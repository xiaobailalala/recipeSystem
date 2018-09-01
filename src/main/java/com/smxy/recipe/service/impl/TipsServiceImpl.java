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
import com.smxy.recipe.service.TipsService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tipsService")
public class TipsServiceImpl implements TipsService {

    @Autowired
    TipsDao tipsDao;

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200,"success", tipsDao.getAllInfo());
    }
}
