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
 * Build File @date: 2018/12/26 14:40
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.socket;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.entity.CommonChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@Service("commonChatSocketService")
public class CommonChatSocketService {

    private final SimpMessagingTemplate template;

    @Autowired
    public CommonChatSocketService(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void pushChatMessageForUser(String message) {
        CommonChat commonChat = JSONObject.parseObject(message, CommonChat.class);
        System.out.println("/chat/userMsg/" + commonChat.getFOid());
        template.convertAndSend("/chat/userMsg/" + commonChat.getFOid(), commonChat);
    }

}
