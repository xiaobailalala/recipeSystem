/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/8 12:30
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MaterialDao;
import com.smxy.recipe.entity.Material;
import com.smxy.recipe.service.MaterialService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    MaterialDao materialDao;

    @Override
    public ResApi<Object> saveInfo(String fName) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (materialDao.getInfoByName(fName)!=null){
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }else {
            if (materialDao.saveInfo(fName)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }
        return resApi;
    }

    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (materialDao.deleteInfo(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200,"success",materialDao.getAllInfo());
    }

    @Override
    public Material getInfoById(Integer id) {
        return materialDao.getInfoById(id);
    }

    @Override
    public ResApi<Object> updateInfo(Integer id, Material material) {
        material.setfId(id);
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (materialDao.getInfoByName(material.getfName())!=null){
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }else {
            if (materialDao.updateInfo(material)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }
        return resApi;
    }


}
