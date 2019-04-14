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
 * Build File @date: 2018/11/23 20:02
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.ArticleComment;
import com.smxy.recipe.service.ArticleCommentService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@PathRestController("/mob/articleComment")
public class ArticleCommentMobController {

    @SuppressWarnings("all")
    @Autowired
    private ArticleCommentService articleCommentService;

    @GetMapping("/getInfoByRid")
    public ResApi<Object> getInfoByRid(Integer rid, Integer uid){
        return articleCommentService.getInfoByRid(rid, uid);
    }

    @GetMapping("/greatOperation")
    public ResApi<String> greatOperation(Integer cid, Integer uid, Integer open){
        return articleCommentService.greatOperation(open, cid, uid);
    }

    @GetMapping("/getInfoByRidAndPage")
    public ResApi<Object> getInfoByRidAndPage(Integer page, Integer rid, Integer uid){
        return articleCommentService.getInfoByRidAndPage(page, rid, uid);
    }

    @PostMapping("/imgupload")
    public ResApi<Object> commentImgUpload(@RequestParam("commentImg")MultipartFile file){
        return articleCommentService.commentImgUpload(file);
    }

    @PostMapping("/saveInfo")
    public ResApi<String> commentSaveInfo(ArticleComment articleComment){
        return articleCommentService.commentSaveInfo(articleComment);
    }

}
