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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("processService")
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessDao processDao;

    @Override
    public ResApi<Object> produceVoiceForId(Process process) {
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
        String filePath = BaiduTtsApi.sendVoiceData(process.getFContent());
        if (filePath != null) {
            process.setFVoice(filePath);
            if (processDao.updateVoiceById(process) > 0) {
                return new ResApi<>(200, "success", filePath);
            }
            return resApi;
        }
        return resApi;
    }
}
