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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional(rollbackFor = Exception.class)
@Service("classifyService")
public class ClassifyServiceImpl implements ClassifyService {

    private final ClassifyDao classifyDao;
    private final ClassifyOneDao classifyOneDao;
    private final ClassifyTwoDao classifyTwoDao;

    @Autowired
    public ClassifyServiceImpl(ClassifyDao classifyDao, ClassifyOneDao classifyOneDao, ClassifyTwoDao classifyTwoDao) {
        this.classifyDao = classifyDao;
        this.classifyOneDao = classifyOneDao;
        this.classifyTwoDao = classifyTwoDao;
    }

    @Cacheable(value = "recipeClassify")
    @Override
    public ResApi<Object> getAllInfo() {
        return ResApi.getSuccess(classifyDao.getAllInfo());
    }

    @Override
    public ResApi<Object> getClaOneAllInfo() {
        return ResApi.getSuccess(classifyOneDao.getAllInfo());
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> saveInfo(Classify classify) {
        if (classifyDao.getInfoByNameAndTid(classify)==null){
            if (classifyDao.saveInfo(classify)>0){
                return ResApi.getSuccess();
            }
        }else{
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }
        return ResApi.getError();
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> deleteInfo(Integer id) {
        if (classifyDao.deleteInfo(id)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> getInfoById(Integer id) {
        Map<String, Object> map=new HashMap<>(8);
        map.put("item",classifyDao.getInfoById(id));
        map.put("one",classifyOneDao.getAllInfo());
        map.put("two",classifyTwoDao.getInfoAll());
        return ResApi.getSuccess(map);
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> updateInfo(Integer id, Classify classify) {
        classify.setFId(id);
        if (classifyDao.getInfoByNameAndTid(classify)==null){
            if (classifyDao.updateInfo(classify)>0){
                return ResApi.getSuccess();
            }
        }else{
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getInfoByTid(Integer id) {
        return ResApi.getSuccess(classifyDao.getInfoByTid(id));
    }
}
