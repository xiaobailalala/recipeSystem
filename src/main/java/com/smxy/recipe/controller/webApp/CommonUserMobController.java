/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/22 19:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.webApp;

import com.smxy.recipe.entity.CommonUser;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mob/commonUser")
public class CommonUserMobController {
    @Autowired
    private CommonUserService commonUserService;

    /**
     * @param common_user 用户实体类
     * @param request
     * @return
     */
    @PostMapping("/commonUserLogin")
    public ResApi<CommonUser> commonUserLogin(CommonUser common_user, HttpServletRequest request){
        return commonUserService.commonUserLogin(common_user);
    }

    /**
     * @param common_user 用户实体类
     * @return
     */
    @PostMapping("/commonUserReg")
    public ResApi<String> commonUserReg(CommonUser common_user){
        return commonUserService.commonUserReg(common_user);
    }

    /**
     * @param multipartFile 文件流
     * @param img 是否有图片
     * @param preImg 上一个图片的路径
     * @return
     */
    @PostMapping("/commonUsersaveHead")
    public ResApi<String> commonUsersaveHead(@RequestParam("file") MultipartFile multipartFile, int img, String preImg){
        return commonUserService.commonUsersaveHead(multipartFile,img,preImg);
    }

    /**
     * @param common_user 用户实体类
     * @return
     */
    @PostMapping("/commonUserSaveInfo")
    public ResApi<CommonUser> commonUserSaveInfo(CommonUser common_user){
        return commonUserService.commonUserSaveInfo(common_user);
    }
}
