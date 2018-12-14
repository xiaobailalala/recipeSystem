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
 * Build File @date: 2018/12/13 8:59
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.socket;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.entity.SysNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(rollbackFor = Exception.class)
@Service("sysNotificationSocketService")
public class SysNotificationSocketService {

    @SuppressWarnings("all")
    @Autowired
    private SimpMessagingTemplate template;

    public void pushSystemMessageForUser(String message) {
        SysNotification sysNotification = JSONObject.parseObject(message, SysNotification.class);
        template.convertAndSend("/systemMessage/userMsg/" + sysNotification.getFUid(), message);
    }

}
