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
 * Build File @date: 2018/9/30 10:04
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ProcessDao;
import com.smxy.recipe.entity.Process;
import com.smxy.recipe.service.ProcessService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.api.BaiduTtsApi;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    private final ProcessDao processDao;

    @Autowired
    public ProcessServiceImpl(ProcessDao processDao) {
        this.processDao = processDao;
    }

    @Override
    public synchronized ResApi<Object> produceVoiceForId(Integer fId) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        Process infoByFid = processDao.getInfoByFid(fId);
        if (infoByFid.getFVoice().equals("0") ) {
            String filePath = null;
            String request = null;
            try {
                filePath = BaiduTtsApi.sendVoiceData(infoByFid.getFContent());
                request = BaiduTtsApi.sendVoiceData("该步骤的执行时长为" + infoByFid.getFRequest() + "秒");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (filePath != null && request != null) {
                infoByFid.setFVoice(filePath);
                infoByFid.setFReqVoice(request);
            }
            if (processDao.updateVoiceById(infoByFid) > 0) {
                return new ResApi<>(200, "success", infoByFid);
            }
            return resApi;
        } else {
            return new ResApi<>(200, "success", infoByFid);
        }
    }
}
