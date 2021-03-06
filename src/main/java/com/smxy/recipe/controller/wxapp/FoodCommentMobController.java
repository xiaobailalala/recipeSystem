/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/10/23 19:33
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.FoodComment;
import com.smxy.recipe.service.FoodCommentService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@PathRestController("/mob/foodComment")
public class FoodCommentMobController {

    private final FoodCommentService foodCommentService;

    @Autowired
    public FoodCommentMobController(FoodCommentService foodCommentService) {
        this.foodCommentService = foodCommentService;
    }

    @PostMapping("/imgupload")
    public ResApi<Object> commentImgupload(@RequestParam("commentImg") MultipartFile file){
        return foodCommentService.commentImgupload(file);
    }

    @PostMapping("/saveInfo")
    public ResApi<String> commentSaveInfo(FoodComment foodComment){
        return foodCommentService.commentSaveInfo(foodComment);
    }

    @GetMapping("/getInfoByRid")
    public ResApi<Object> getInfoByRid(Integer rid, Integer uid){
        return foodCommentService.getInfoByRid(rid, uid);
    }

    @GetMapping("/getInfoByRidAndPage")
    public ResApi<Object> getInfoByRidAndPage(Integer page, Integer rid, Integer uid){
        return foodCommentService.getInfoByRidAndPage(page, rid, uid);
    }

    @GetMapping("/greatOperation")
    public ResApi<String> greatOperation(Integer open, Integer cid, Integer uid) {
        return foodCommentService.greatOperation(open, cid, uid);
    }
}
