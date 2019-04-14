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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("aiMarkService")
public class AiMarkServiceImpl implements AiMarkService {

    private AiMarkDao aiMarkDao;

    @Autowired
    public AiMarkServiceImpl(AiMarkDao aiMarkDao) {
        this.aiMarkDao = aiMarkDao;
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return ResApi.getSuccess(aiMarkDao.getAllInfo());
    }

    @Override
    public ResApi<String> saveInfo(AiMark aiMark) {
        if (aiMarkDao.getInfoByMark(aiMark) != null) {
            return ResApi.getError(501, "该代号已存在，请勿重复添加");
        } else {
            aiMark.setFVoice(BaiduTtsApi.sendVoiceData(aiMark.getFContent()));
            if (aiMarkDao.saveInfo(aiMark)>0){
                return ResApi.getSuccess();
            }
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<String> deleteInfo(Integer id) {
        if (aiMarkDao.deleteInfo(id)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public AiMark getInfoById(Integer id) {
        return aiMarkDao.getInfoById(id);
    }

    @Override
    public ResApi<String> updateInfo(Integer id, AiMark aiMark) {
        aiMark.setFId(id);
        ToolsApi.multipartFileDeleteFile(aiMark.getFVoice());
        aiMark.setFVoice(BaiduTtsApi.sendVoiceData(aiMark.getFContent()));
        if (aiMarkDao.updateInfoById(aiMark)>0){
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> getVoiceForWXReady(String readyMark, String fireMark, String smogMark, String distanceMark, String cookTipMark) {
        List<String> strings = new ArrayList<>();
        strings.add(aiMarkDao.getInfoByMark(new AiMark().setFMark(readyMark)).getFVoice());
        strings.add(aiMarkDao.getInfoByMark(new AiMark().setFMark(fireMark)).getFVoice());
        strings.add(aiMarkDao.getInfoByMark(new AiMark().setFMark(smogMark)).getFVoice());
        strings.add(aiMarkDao.getInfoByMark(new AiMark().setFMark(distanceMark)).getFVoice());
        strings.add(aiMarkDao.getInfoByMark(new AiMark().setFMark(cookTipMark)).getFVoice());
        return ResApi.getSuccess(strings);
    }
}
