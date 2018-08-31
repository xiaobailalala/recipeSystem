/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/25 14:21
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.ClassifyOne;
import com.smxy.recipe.entity.ClassifyTwo;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClassifyTwoService {

    public ResApi<Object> getInfoAll();

    public List<ClassifyOne> getClassifyOne();

    public ResApi<Object> saveInfo(MultipartFile multipartFile, ClassifyTwo classifyTwo);

    public ResApi<Object> deleteInfo(Integer id);

    public ResApi<Object> getInfoOne(Integer id);

    public ResApi<Object> updateInfo(MultipartFile multipartFile, Integer id, ClassifyTwo classifyTwo);

    public ResApi<Object> getInfoByOid(Integer oid);

}
