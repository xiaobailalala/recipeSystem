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
 * Build File @date: 2018/12/12 21:27
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.dao.SysNotificationDao;
import com.smxy.recipe.entity.SysNotification;
import com.smxy.recipe.service.SysNotificationService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("sysNotificationService")
public class SysNotificationServiceImpl implements SysNotificationService {

    private final SysNotificationDao sysNotificationDao;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public SysNotificationServiceImpl(SysNotificationDao sysNotificationDao, RabbitTemplate rabbitTemplate) {
        this.sysNotificationDao = sysNotificationDao;
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public ResApi<String> getNotificationMessage(MultipartFile file, SysNotification sysNotification) {
        if (file.getSize() != 0) {
            sysNotification.setFCover(ToolsApi.multipartFileUploadFile(file, null));
        }
        sysNotification.setFRelease(ToolsApi.getDateToDay());
        sysNotificationDao.saveInfo(sysNotification);
        String jsonObj = JSONObject.toJSONString(sysNotification);
        rabbitTemplate.convertAndSend("recipeSystem.direct", "systemMessage.queue", jsonObj);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> showMessage(Integer uid) {
        sysNotificationDao.updateStateByUid(uid);
        return ResApi.getSuccess(sysNotificationDao.findInfoByUid(uid));
    }

    @Override
    public ResApi<Object> showMessageCount(Integer uid) {
        return ResApi.getSuccess(sysNotificationDao.findInfoByUidAndState(uid));
    }

    @Override
    public ResApi<String> deleteMessage(Integer uid) {
        List<SysNotification> sysNotifications = sysNotificationDao.findInfoByUid(uid);
        sysNotifications.forEach(item -> {
            if (item.getFCover() != null) {
                ToolsApi.multipartFileDeleteFile(item.getFCover());
            }
        });
        sysNotificationDao.deleteInfoByUid(uid);
        return ResApi.getSuccess();
    }
}
