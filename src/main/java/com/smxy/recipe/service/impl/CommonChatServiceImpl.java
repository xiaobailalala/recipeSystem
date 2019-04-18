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
 * Build File @date: 2018/12/17 11:13
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.dao.CommonChatDao;
import com.smxy.recipe.dao.CommonChatUnreadDao;
import com.smxy.recipe.entity.CommonChat;
import com.smxy.recipe.entity.CommonChatUnread;
import com.smxy.recipe.service.CommonChatService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Comparator;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("commonChatService")
public class CommonChatServiceImpl implements CommonChatService {

    private final CommonChatDao commonChatDao;
    private final RabbitTemplate rabbitTemplate;
    private final CommonChatUnreadDao commonChatUnreadDao;

    @Autowired
    public CommonChatServiceImpl(CommonChatDao commonChatDao, RabbitTemplate rabbitTemplate, CommonChatUnreadDao commonChatUnreadDao) {
        this.commonChatDao = commonChatDao;
        this.rabbitTemplate = rabbitTemplate;
        this.commonChatUnreadDao = commonChatUnreadDao;
    }

    @Override
    public ResApi<String> chatSaveMessage(MultipartFile file, CommonChat commonChat) {
        switch (commonChat.getFType()) {
            case 1:
                commonChat.setFContent(ToolsApi.base64Encode(commonChat.getFContent()));
                break;
            case 2:
                commonChat.setFUrl(ToolsApi.multipartFileUploadFile(file, null));
                break;
            default:
                commonChat.setFUrl(ToolsApi.multipartFileUploadFile(file, null));
                break;
        }
        commonChatDao.saveInfo(commonChat);
        commonChatUnreadDao.saveInfo(new CommonChatUnread(null, commonChat.getFOid(), commonChat.getFUid(), commonChat.getFId()));
        String jsonStr = JSONObject.toJSONString(commonChat);
        rabbitTemplate.convertAndSend("recipeSystem.direct", "chatMessage.queue", jsonStr);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> showMessage(Integer uid, Integer oid) {
        List<CommonChat> commonChats = commonChatDao.findInfoByUidAndOid(uid, oid);
        commonChatUnreadDao.deleteInfoByUidAndOid(uid, oid);
        commonChats.forEach(item -> {
            if (item.getFType().equals(1)) {
                item.setFContent(ToolsApi.base64Decode(item.getFContent()));
            }
        });
        commonChats.sort(Comparator.comparing(CommonChat::getFRelease));
        return ResApi.getSuccess(commonChats);
    }
}
