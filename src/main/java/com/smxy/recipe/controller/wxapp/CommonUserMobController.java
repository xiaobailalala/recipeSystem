/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/22 19:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.CommonUser;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@PathRestController("/mob/commonUser")
public class CommonUserMobController {

    @SuppressWarnings("all")
    @Autowired
    private CommonUserService commonUserService;

    /**
     * @param commonUser 用户实体类
     * @param request
     * @return
     */
    @PostMapping("/commonUserLogin")
    public ResApi<CommonUser> commonUserLogin(CommonUser commonUser, HttpServletRequest request){
        return commonUserService.commonUserLogin(commonUser);
    }

    /**
     * @param commonUser 用户实体类
     * @return
     */
    @PostMapping("/commonUserReg")
    public ResApi<String> commonUserReg(CommonUser commonUser){
        return commonUserService.commonUserReg(commonUser);
    }

    /**
     * @param multipartFile 文件流
     * @param img 是否有图片
     * @param preImg 上一个图片的路径
     * @return
     */
    @PostMapping("/commonUsersaveHead")
    public ResApi<String> commonUsersaveHead(@RequestParam("file") MultipartFile multipartFile, int img, String preImg, Integer fId){
        return commonUserService.commonUsersaveHead(multipartFile,img,preImg, fId);
    }

    /**
     * @param commonUser 用户实体类
     * @return
     */
    @PostMapping("/commonUserSaveInfo")
    public ResApi<CommonUser> commonUserSaveInfo(CommonUser commonUser){
        return commonUserService.commonUserSaveInfo(commonUser);
    }

    @GetMapping("/collectionInfo")
    public ResApi<Object> collectionInfo(Integer uid){
        return commonUserService.collectionInfo(uid);
    }

    @GetMapping("/peopleInfoDetail")
    public ResApi<Object> peopleInfoDetail(Integer uid) {
        return commonUserService.peopleInfoDetail(uid);
    }

    @PostMapping("/updateCommonUserBg")
    public ResApi<Object> updateCommonUserBg(@RequestParam("file") MultipartFile multipartFile, CommonUser commonUser) {
        return commonUserService.updateCommonUserBg(multipartFile, commonUser);
    }

}
