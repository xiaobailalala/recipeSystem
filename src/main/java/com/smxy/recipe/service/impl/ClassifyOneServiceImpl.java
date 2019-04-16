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
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("classifyOneService")
public class ClassifyOneServiceImpl implements ClassifyOneService {

    private final ClassifyOneDao classifyOneDao;

    @Autowired
    public ClassifyOneServiceImpl(ClassifyOneDao classifyOneDao) {
        this.classifyOneDao = classifyOneDao;
    }

    @Cacheable(value = "recipeAllClassify")
    @Override
    public ResApi<Object> getAllInfo() {
        return ResApi.getSuccess(classifyOneDao.getAllInfo());
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> saveInfo(String name) {
        if (classifyOneDao.getInfoByName(name)!=null){
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }else{
            if (classifyOneDao.saveInfo(name)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> deleteInfo(Integer id) {
        if (classifyOneDao.deleteInfoById(id)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public Classify getInfoById(Integer id) {
        return classifyOneDao.getInfoById(id);
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> updateInfo(Classify classify) {
        if (classifyOneDao.getInfoByName(classify.getFName())!=null){
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }else{
            if (classifyOneDao.updateInfoById(classify)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }
}
