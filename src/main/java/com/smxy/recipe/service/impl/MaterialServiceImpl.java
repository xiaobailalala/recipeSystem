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
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Transactional(rollbackFor = Exception.class)
@Service("materialService")
public class MaterialServiceImpl implements MaterialService {

    @SuppressWarnings("all")
    @Autowired
    private MaterialDao materialDao;

    @Override
    public ResApi<String> saveInfo(MultipartFile file, String fName) {
        if (materialDao.getInfoByName(fName)!=null){
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }else {
            String fCover = ToolsApi.multipartFileUploadFile(file, null);
            if (materialDao.saveInfo(fName, fCover)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<String> deleteInfo(Integer id, String filePath) {
        if (materialDao.deleteInfo(id)>0){
            ToolsApi.multipartFileDeleteFile(filePath);
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return ResApi.getSuccess(materialDao.getAllInfo());
    }

    @Override
    public Material getInfoById(Integer id) {
        return materialDao.getInfoById(id);
    }

    @Override
    public ResApi<String> updateInfo(Integer id, MultipartFile file, Material material) {
        material.setFId(id);
        if (file.getSize() == 0 && materialDao.getInfoByName(material.getFName())!=null){
            return ResApi.getError(501,"该分类已存在，请勿重复添加");
        }else {
            if (file.getSize()>0) {
                ToolsApi.multipartFileDeleteFile(material.getFCover());
                material.setFCover(ToolsApi.multipartFileUploadFile(file, null));
            }
            if (materialDao.updateInfo(material)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getOneByName(String fName) {
        ResApi<Object> resApi=new ResApi<>(500,"failed","failed");
        Material material=materialDao.getInfoByName(fName);
        if (material!=null){
            resApi=new ResApi<>(200,"success",material);
        }
        return resApi;
    }

    @Override
    public ResApi<Object> randomList() {
        List<Material> materials = materialDao.getAllInfo();
        int[] randomNumber = ToolsApi.randomArray(0, materials.size() - 1, 20);
        List<Material> randomList = new LinkedList<>();
        for (int index : Objects.requireNonNull(randomNumber)) {
            randomList.add(materials.get(index));
        }
        return ResApi.getSuccess(randomList);
    }

    @Override
    public ResApi<Object> getDataByVagueName(String name) {
        return ResApi.getSuccess(materialDao.getDataByVagueName(name));
    }


}
