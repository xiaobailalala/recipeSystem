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
 * Build File @date: 2018/10/27 14:17
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.SysResourceDao;
import com.smxy.recipe.entity.SysResource;
import com.smxy.recipe.service.SysResourceService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sysResourceService")
public class SysResourceServiceImpl implements SysResourceService {

    @Autowired
    SysResourceDao sysResourceDao;

    @Override
    public ResApi<Object> getInfoAll() {
        String[] imgLimit = {"gif", "jpeg", "jpg", "png", "svg"};
        String voiceLimit = "mp3";
        Map<String, List<SysResource>> map = new HashMap<>(8);
        List<SysResource> sysResources = new ArrayList<>();
        for (String s : imgLimit) {
            sysResources.addAll(sysResourceDao.getInfoByType(s));
        }
        map.put("imgList", sysResources);
        map.put("voiceList", sysResourceDao.getInfoByType(voiceLimit));
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> saveInfo(SysResource sysResource) {
        sysResourceDao.saveInfo(sysResource);
        return new ResApi<>(200, "success", "success");
    }

    @Override
    public ResApi<Object> deleteInfo(String fileName) {
        sysResourceDao.deleteInfo(fileName);
        return new ResApi<>(200, "success", "success");
    }
}
