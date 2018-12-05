/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package: com.smxy.recipe.serviceImpl
 * @author: 雏实。
 * Build File @date: 2018年7月4日 下午1:33:14
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.CollectDao;
import com.smxy.recipe.dao.CommonAttentionDao;
import com.smxy.recipe.dao.CommonUserDao;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import com.smxy.recipe.utils.api.CodeApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpx
 *
 */
@Service("commonUserService")
public class CommonUserServiceImpl implements CommonUserService {

    private CommonUserDao commonUserDao;
    private CollectDao collectDao;
    private CommonAttentionDao commonAttentionDao;

    @Autowired
    public CommonUserServiceImpl(CommonUserDao commonUserDao, CollectDao collectDao, CommonAttentionDao commonAttentionDao) {
        this.commonUserDao = commonUserDao;
        this.collectDao = collectDao;
        this.commonAttentionDao = commonAttentionDao;
    }

    @Override
    public ResApi<CommonUser> commonUserLogin(CommonUser commonUser) {
        // TODO Auto-generated method stub
        ResApi<CommonUser> resApi;
        if (commonUserDao.isUser(commonUser) > 0) {
            commonUser.setFPassword(ToolsApi.entryptBySaltMd5(commonUser.getFPassword(), commonUser.getFAccount()));
            commonUser = commonUserDao.isLogin(commonUser);
            if (commonUser != null) {
                resApi = new ResApi<>(200, "登录成功", commonUser);
            } else {
                resApi = new ResApi<>(401, "密码不正确", null);
            }
        } else {
            resApi = new ResApi<>(400, "用户不存在，请先注册", null);
        }
        return resApi;
    }

    @Override
    public ResApi<String> commonUserReg(CommonUser commonUser) {
        // TODO Auto-generated method stub
        ResApi<String> resApi;
        if (commonUserDao.isUser(commonUser) > 0) {
            resApi = new ResApi<>(402, "该用户已存在。", null);
        } else {
            commonUser.setFPassword(ToolsApi.entryptBySaltMd5(commonUser.getFPassword(), commonUser.getFAccount()));
            if (commonUserDao.saveUser(commonUser) > 0) {
                commonUser.setFUsername("膳客" + commonUser.getFId());
                if (commonUserDao.updateUserInfo(commonUser) > 0) {
                    resApi = new ResApi<>(200, "注册成功", null);
                } else {
                    resApi = new ResApi<>(401, "生成用户名失败。", null);
                }
            } else {
                resApi = new ResApi<>(400, "保存失败，请重试。", null);
            }
        }
        return resApi;
    }

    @Override
    public ResApi<String> commonUsersaveHead(MultipartFile file, int img, String preImg, Integer fId) {
        // TODO Auto-generated method stub
        int hasDeleteImg = 2;
        if (img == hasDeleteImg && preImg != null) {
            ToolsApi.multipartFileDeleteFile(preImg);
        }
        if (ToolsApi.imgLimit(ToolsApi.suffixName(file.getOriginalFilename()))) {
            String name = ToolsApi.multipartFileUploadFile(file, null);
            if (img == hasDeleteImg) {
                CommonUser commonUser = new CommonUser();
                commonUser.setFId(fId);
                commonUser.setFCover(name);
                commonUserDao.updateUserHead(commonUser);
            }
            return new ResApi<>(200, "success", name);
        } else {
            return new ResApi<>(400, "头像上传失败", null);
        }
    }

    @Override
    public ResApi<CommonUser> commonUserSaveInfo(CommonUser commonUser) {
        // TODO Auto-generated method stub
        int res = commonUserDao.updateUserInfo(commonUser);
        if (res > 0) {
            return new ResApi<>(200, "保存成功", commonUser);
        } else {
            return new ResApi<>(400, "保存失败", null);
        }
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200, "success", commonUserDao.getInfoAll());
    }

    @Override
    public ResApi<Object> getOneById(Integer id) {
        Map<String, Object> map = new HashMap(8);
        map.put("item", commonUserDao.getInfoByIdBrief(id));
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> updateCommonUserPwd(CommonUser commonUser) {
        CodeApi.getRequest(CodeApi.RESET_PWD, commonUser.getFAccount(), null);
        String newPwd = ToolsApi.entryptBySaltMd5("UcJAxskwvJxTNery", commonUser.getFAccount());
        commonUserDao.updatePwdByAccount(commonUser);
        return new ResApi<>(200, "success", "success");
    }

    @Override
    public ResApi<Object> collectionInfo(Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("recipe", collectDao.findByUidAndType(uid, 1));
        List<Collect> collectList = collectDao.findByUidAndType(uid, 2);
        collectList.forEach(item -> {
            item.getArticle().setFName(ToolsApi.base64Decode(item.getArticle().getFName()));
            item.getArticle().setFContent(ToolsApi.base64Decode(item.getArticle().getFContent()));
        });
        map.put("article", collectList);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> peopleInfoDetail(Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        CommonUser commonUser = commonUserDao.peopleInfoDetail(uid);
        commonUser.getArticles().forEach(item -> item.setFName(ToolsApi.base64Decode(item.getFName())));
        map.put("info", commonUser);
        List<CommonAttention> commonAttentions1 = commonAttentionDao.findInfoByUidAndType(uid, 1);
        List<CommonAttention> commonAttentions2 = commonAttentionDao.findInfoByOidAndType(uid, 1);
        map.put("attentionInfo", commonAttentions1);
        map.put("fansInfo", commonAttentions2);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> updateCommonUserBg(MultipartFile multipartFile, CommonUser commonUser) {
        if (commonUser.getFBg() != null) {
            ToolsApi.multipartFileDeleteFile(commonUser.getFBg());
        }
        String name = ToolsApi.multipartFileUploadFile(multipartFile, null);
        commonUser.setFBg(name);
        commonUserDao.updateUserBg(commonUser);
        return ResApi.getSuccess(name);
    }
}
