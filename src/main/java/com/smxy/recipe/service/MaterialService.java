/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/8 12:30
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service;

import com.smxy.recipe.entity.Material;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

public interface MaterialService {

    ResApi<String> saveInfo(MultipartFile file, String fName);

    ResApi<String> deleteInfo(Integer id, String filePath);

    ResApi<Object> getAllInfo();

    Material getInfoById(Integer id);

    ResApi<String> updateInfo(Integer id, MultipartFile file, Material material);

    ResApi<Object> getOneByName(String fName);

    ResApi<Object> randomList(Integer number);

    ResApi<Object> getDataByVagueName(String name);
}
