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
 * Build File @date: 2018/9/30 21:36
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.AiMarkDao;
import com.smxy.recipe.entity.AiMark;
import com.smxy.recipe.service.AiMarkService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import com.smxy.recipe.utils.api.BaiduTtsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("aiMarkService")
public class AiMarkServiceImpl implements AiMarkService {

    @Autowired
    private AiMarkDao aiMarkDao;

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200, "success", aiMarkDao.getAllInfo());
    }

    @Override
    public ResApi<Object> saveInfo(AiMark aiMark) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        if (aiMarkDao.getInfoByMark(aiMark) != null) {
            resApi = new ResApi<>(501, "该代号已存在，请勿重复添加", "failed");
        } else {
            aiMark.setFVoice(BaiduTtsApi.sendVoiceData(aiMark.getFContent()));
            if (aiMarkDao.saveInfo(aiMark)>0){
                resApi = new ResApi<>(200, "success", "success");
            }
        }
        return resApi;
    }

    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "failed");
        if (aiMarkDao.deleteInfo(id)>0){
            resApi = new ResApi<>(200, "success", "success");
        }
        return resApi;
    }

    @Override
    public AiMark getInfoById(Integer id) {
        return aiMarkDao.getInfoById(id);
    }

    @Override
    public ResApi<Object> updateInfo(Integer id, AiMark aiMark) {
        aiMark.setFId(id);
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        ToolsApi.multipartFileDeleteFile(aiMark.getFVoice());
        aiMark.setFVoice(BaiduTtsApi.sendVoiceData(aiMark.getFContent()));
        if (aiMarkDao.updateInfoById(aiMark)>0){
            resApi = new ResApi<>(200, "success", "success");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getVoiceForWXReady(String readyMark, String fireMark, String smogMark) {
        List<String> strings = new ArrayList<>();
        AiMark aiMark = new AiMark();
        aiMark.setFMark(readyMark);
        strings.add(aiMarkDao.getInfoByMark(aiMark).getFVoice());
        aiMark.setFMark(fireMark);
        strings.add(aiMarkDao.getInfoByMark(aiMark).getFVoice());
        aiMark.setFMark(smogMark);
        strings.add(aiMarkDao.getInfoByMark(aiMark).getFVoice());
        return new ResApi<>(200, "success", strings);
    }
}
