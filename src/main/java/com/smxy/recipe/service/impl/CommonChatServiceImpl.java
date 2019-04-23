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

import com.smxy.recipe.dao.CommonChatDao;
import com.smxy.recipe.dao.CommonChatUnreadDao;
import com.smxy.recipe.dao.CommonLinkmanDao;
import com.smxy.recipe.dao.CommonUserDao;
import com.smxy.recipe.entity.CommonChat;
import com.smxy.recipe.entity.CommonLinkman;
import com.smxy.recipe.service.CommonChatService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Transactional(rollbackFor = Exception.class)
@Service("commonChatService")
public class CommonChatServiceImpl implements CommonChatService {

    private final CommonChatDao commonChatDao;
    private final RabbitTemplate rabbitTemplate;
    private final CommonChatUnreadDao commonChatUnreadDao;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final CommonLinkmanDao commonLinkmanDao;
    private final CommonUserDao commonUserDao;

    @Autowired
    public CommonChatServiceImpl(CommonChatDao commonChatDao, RabbitTemplate rabbitTemplate, CommonChatUnreadDao commonChatUnreadDao, SimpMessagingTemplate simpMessagingTemplate, CommonLinkmanDao commonLinkmanDao, CommonUserDao commonUserDao) {
        this.commonChatDao = commonChatDao;
        this.rabbitTemplate = rabbitTemplate;
        this.commonChatUnreadDao = commonChatUnreadDao;
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.commonLinkmanDao = commonLinkmanDao;
        this.commonUserDao = commonUserDao;
    }

    @Override
    public ResApi<Object> chatSaveMessage(MultipartFile file, CommonChat commonChat) {
        if (commonChat.getFType() == 0) {
            commonChat.setFContent(ToolsApi.base64Encode(commonChat.getFContent()));
        } else {
            commonChat.setFUrl(ToolsApi.multipartFileUploadFile(file, null));
        }
        String dateTime = System.currentTimeMillis()+"";
        commonChatDao.saveInfo(commonChat.setFDatetime(dateTime));
        String[] timeArr = commonChat.getFRelease().split(" ");
        CommonLinkman commonLinkman = new CommonLinkman().setFUid(commonChat.getFUid()).
                setFOid(commonChat.getFOid()).setFLastMsg(commonChat.getFType() == 0 ? commonChat.getFContent() :
                (commonChat.getFType() == 1 ? ToolsApi.base64Encode("[语音]") : ToolsApi.base64Encode("[图片]"))).
                setFLastTime(timeArr[1]).setFLastDate(timeArr[0]).setFLastDatetime(dateTime);
        synchronized (this) {
            if (commonLinkmanDao.queryJudgeExist(commonChat.getFUid(), commonChat.getFOid()) != null) {
                commonLinkmanDao.updateInfo(commonLinkman);
            } else {
                commonLinkmanDao.insertInfo(commonLinkman);
            }
            if (commonLinkmanDao.queryJudgeExist(commonChat.getFOid(), commonChat.getFUid()) != null) {
                commonLinkmanDao.updateInfo(commonLinkman.setFUid(commonChat.getFOid()).setFOid(commonChat.getFUid()));
            } else {
                commonLinkmanDao.insertInfo(commonLinkman.setFUid(commonChat.getFOid()).setFOid(commonChat.getFUid()));
            }
        }
        simpMessagingTemplate.convertAndSend("/chat/userMsg/" + commonChat.getFOid(), commonChat.setFContent(ToolsApi.base64Decode(commonChat.getFContent())));
        return ResApi.getSuccess(commonChat);
    }

    @Override
    public ResApi<Object> showMessage(Integer uid, Integer oid) {
        Map<String, Object> data = new HashMap<>(8);
        List<CommonChat> commonChats = new LinkedList<>(commonChatDao.findInfoByUidAndOid(uid, oid));
        commonChats.addAll(commonChatDao.findInfoByUidAndOid(oid, uid));
        commonChats.forEach(item -> {
            if (item.getFType() == 0) {
                item.setFContent(ToolsApi.base64Decode(item.getFContent()));
            }
        });
        commonChats.sort(Comparator.comparing(CommonChat::getFDatetime));
        this.changeChatState(uid, oid);
        data.put("list", commonChats);
        data.put("otherInfo", commonUserDao.getInfoByIdBrief(oid));
        return ResApi.getSuccess(data);
    }

    @Override
    public ResApi<String> changeReadState(CommonChat commonChat) {
        this.changeChatState(commonChat.getFUid(), commonChat.getFOid());
        return ResApi.getSuccess();
    }

    private void changeChatState(Integer uid, Integer oid) {
        commonChatDao.changeChatReadState(uid, oid, 1);
    }

}
