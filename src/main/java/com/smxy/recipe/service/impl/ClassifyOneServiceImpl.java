/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/24 15:42
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ClassifyOneDao;
import com.smxy.recipe.entity.Classify;
import com.smxy.recipe.service.ClassifyOneService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service("classifyOneService")
public class ClassifyOneServiceImpl implements ClassifyOneService {

    @Autowired
    ClassifyOneDao classifyOneDao;

    @Cacheable(value = "recipeAllClassify")
    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200,"success",classifyOneDao.getAllInfo());
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> saveInfo(String name) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyOneDao.getInfoByName(name)!=null){
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }else{
            if (classifyOneDao.saveInfo(name)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }
        return resApi;
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyOneDao.deleteInfoById(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public Classify getInfoById(Integer id) {
        return classifyOneDao.getInfoById(id);
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> updateInfo(Classify classify) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyOneDao.getInfoByName(classify.getFName())!=null){
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }else{
            if (classifyOneDao.updateInfoById(classify)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }
        return resApi;
    }
}
