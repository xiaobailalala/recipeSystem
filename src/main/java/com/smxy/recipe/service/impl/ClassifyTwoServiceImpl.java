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
    @Autowired
    private ClassifyTwoDao classifyTwoDao;
    @Autowired
    private ClassifyOneDao classifyOneDao;
    @Override
    public ResApi<Object> getInfoAll() {
        return new ResApi<>(200,"success",classifyTwoDao.getInfoAll());
    }

    @Override
    public List<ClassifyOne> getClassifyOne() {
        return classifyOneDao.getAllInfo();
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> saveInfo(MultipartFile multipartFile, ClassifyTwo classifyTwo) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyTwoDao.getInfoByNameAndOid(classifyTwo)!=null){
            resApi=new ResApi<>(501,"该分类已存在，请勿重复添加","failed");
        }else{
            if (ToolsApi.imgLimit(ToolsApi.suffixName(multipartFile.getOriginalFilename()))){
                String name = ToolsApi.multipartFileUploadFile(multipartFile, null);
                classifyTwo.setFCover(name);
                if (classifyTwoDao.saveInfo(classifyTwo)>0){
                    resApi=new ResApi<>(200,"success","success");
                }
            }else{
                resApi=new ResApi<>(502,"上传的封面格式不符合要求。","failed");
            }
        }
        return resApi;
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi=new ResApi<>(500,"系统出错","error");
        if (classifyTwoDao.deleteInfo(id)>0){
            resApi=new ResApi<>(200,"success","success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getInfoOne(Integer id) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("item",classifyTwoDao.getInfoById(id));
        map.put("one",classifyOneDao.getAllInfo());
        return new ResApi<>(200,"success",map);
    }

    @CacheEvict(value = {"recipeClassify","recipeAllClassify"}, allEntries = true)
    @Override
    public ResApi<Object> updateInfo(MultipartFile multipartFile, Integer id, ClassifyTwo classifyTwo) {
        classifyTwo.setFId(id);
        ResApi<Object> resApi=new ResApi<>(500,"系统出错。","error");
        if (multipartFile==null||multipartFile.getSize()==0){
            if (classifyTwoDao.updateInfo(classifyTwo)>0){
                resApi=new ResApi<>(200,"success","success");
            }
        }else{
            ToolsApi.multipartFileDeleteFile(classifyTwo.getFCover());
            if (ToolsApi.imgLimit(ToolsApi.suffixName(multipartFile.getOriginalFilename()))){
                String name = ToolsApi.multipartFileUploadFile(multipartFile, null);
                classifyTwo.setFCover(name);
                if (classifyTwoDao.updateInfo(classifyTwo)>0){
                    resApi=new ResApi<>(200,"success","success");
                }
            }else{
                resApi=new ResApi<>(502,"上传的头像格式不符合要求。","failed");
            }
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getInfoByOid(Integer oid) {
        return new ResApi<>(200,"success",classifyTwoDao.getInfoByOid(oid));
    }
}
