/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/25 14:22
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ClassifyOneDao;
import com.smxy.recipe.dao.ClassifyTwoDao;
import com.smxy.recipe.entity.ClassifyOne;
import com.smxy.recipe.entity.ClassifyTwo;
import com.smxy.recipe.service.ClassifyTwoService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("classifyTwoService")
public class ClassifyTwoServiceImpl implements ClassifyTwoService {

    private ClassifyTwoDao classifyTwoDao;
    private ClassifyOneDao classifyOneDao;

    @Autowired
    public ClassifyTwoServiceImpl(ClassifyTwoDao classifyTwoDao, ClassifyOneDao classifyOneDao) {
        this.classifyTwoDao = classifyTwoDao;
        this.classifyOneDao = classifyOneDao;
    }

    @Override
    public ResApi<Object> getInfoAll() {
        return ResApi.getSuccess(classifyTwoDao.getInfoAll());
    }

    @Override
    public List<ClassifyOne> getClassifyOne() {
        return classifyOneDao.getAllInfo();
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> saveInfo(MultipartFile multipartFile, ClassifyTwo classifyTwo) {
        if (classifyTwoDao.getInfoByNameAndOid(classifyTwo)!=null){
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }else{
            if (ToolsApi.imgLimit(ToolsApi.suffixName(multipartFile.getOriginalFilename()))){
                String name = ToolsApi.multipartFileUploadFile(multipartFile, null);
                classifyTwo.setFCover(name);
                if (classifyTwoDao.saveInfo(classifyTwo)>0){
                    return ResApi.getSuccess();
                }
            }else{
                ResApi.getError(502,"上传的封面格式不符合要求。");
            }
        }
        return ResApi.getError();
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> deleteInfo(Integer id) {
        if (classifyTwoDao.deleteInfo(id)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getInfoOne(Integer id) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("item",classifyTwoDao.getInfoById(id));
        map.put("one",classifyOneDao.getAllInfo());
        return ResApi.getSuccess(map);
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<String> updateInfo(MultipartFile multipartFile, Integer id, ClassifyTwo classifyTwo) {
        classifyTwo.setFId(id);
        if (multipartFile==null||multipartFile.getSize()==0){
            if (classifyTwoDao.updateInfo(classifyTwo)>0){
                return ResApi.getSuccess();
            }
        }else{
            ToolsApi.multipartFileDeleteFile(classifyTwo.getFCover());
            if (ToolsApi.imgLimit(ToolsApi.suffixName(multipartFile.getOriginalFilename()))){
                String name = ToolsApi.multipartFileUploadFile(multipartFile, null);
                classifyTwo.setFCover(name);
                if (classifyTwoDao.updateInfo(classifyTwo)>0){
                    return ResApi.getSuccess();
                }
            }else{
                ResApi.getError(502,"上传的头像格式不符合要求。");
            }
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getInfoByOid(Integer oid) {
        return ResApi.getSuccess(classifyTwoDao.getInfoByOid(oid));
    }
}
