/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/23 22:44
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ClassifyDao;
import com.smxy.recipe.dao.ClassifyOneDao;
import com.smxy.recipe.dao.ClassifyTwoDao;
import com.smxy.recipe.entity.Classify;
import com.smxy.recipe.service.ClassifyService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("classifyService")
public class ClassifyServiceImpl implements ClassifyService {

    @Autowired
    ClassifyDao classifyDao;
    @Autowired
    ClassifyOneDao classifyOneDao;
    @Autowired
    ClassifyTwoDao classifyTwoDao;

    @Cacheable(value = "recipeClassify")
    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200,"success",classifyDao.getAllInfo());
    }

    @Override
    public ResApi<Object> getClaOneAllInfo() {
        return new ResApi<>(200,"success",classifyOneDao.getAllInfo());
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> saveInfo(Classify classify) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyDao.getInfoByNameAndTid(classify)==null){
            if (classifyDao.saveInfo(classify)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }else{
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }
        return resApi;
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyDao.deleteInfo(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> getInfoById(Integer id) {
        Map<String, Object> map=new HashMap<>();
        map.put("item",classifyDao.getInfoById(id));
        map.put("one",classifyOneDao.getAllInfo());
        map.put("two",classifyTwoDao.getInfoAll());
        return new ResApi<>(200,"success",map);
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> updateInfo(Integer id, Classify classify) {
        classify.setfId(id);
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyDao.getInfoByNameAndTid(classify)==null){
            if (classifyDao.updateInfo(classify)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }else{
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }
        return resApi;
    }
}
