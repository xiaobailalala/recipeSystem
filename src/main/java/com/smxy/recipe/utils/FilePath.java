/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.utils 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:36:30 
 */
package com.smxy.recipe.utils;

/**
 * @author zpx
 *
 */

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.io.File;

@Component
@PropertySource("classpath:custom.properties")
public class FilePath {

    public static String realPathStr(String filePath) {
        String path=realPath+filePath;
        File file=new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        return path;
    }

    public static String realPath;
    @Value("${filepath.realPath}")
    public void setRealPath(String realPath) {
        FilePath.realPath = realPath;
    }

    public static String commonUserHeadPath;
    @Value("${filepath.commonUserHeadPath}")
    public void setCommonUserHeadPath(String commonUserHeadPath) {
        FilePath.commonUserHeadPath = commonUserHeadPath;
    }

    public static String adminUserHeadPath;
    @Value("${filepath.adminUserHeadPath}")
    public void setAdminUserHeadPath(String adminUserHeadPath){
        FilePath.adminUserHeadPath=adminUserHeadPath;
    }

    public static String recipeClassifyTwoPath;
    @Value("${filepath.recipeClassifyTwoPath}")
    public void setRecipeClassifyTwoPath(String recipeClassifyTwoPath){
        FilePath.recipeClassifyTwoPath=recipeClassifyTwoPath;
    }
}

