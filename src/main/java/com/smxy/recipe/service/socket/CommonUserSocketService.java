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
 * Build File @date: 2018/10/12 21:29
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.socket;

import com.smxy.recipe.entity.CommonChat;
import com.smxy.recipe.entity.tools.InMessage;
import com.smxy.recipe.service.CommonChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("commonUserSocketService")
public class CommonUserSocketService {

    private final SimpMessagingTemplate template;
    private final CommonChatService commonChatService;

    private static List<InMessage> fireListeningList;

    private static List<InMessage> smogListeningList;

    private static List<InMessage> infraredListeningList;

    private static List<InMessage> distanceListeningList;

    static {
        fireListeningList = new ArrayList<>();
        smogListeningList = new ArrayList<>();
        infraredListeningList = new ArrayList<>();
        distanceListeningList = new ArrayList<>();
    }

    @Autowired
    public CommonUserSocketService(SimpMessagingTemplate template, CommonChatService commonChatService) {
        this.template = template;
        this.commonChatService = commonChatService;
    }

    public void fireNumberPush(InMessage inMessage) {
        fireListeningList.add(inMessage);
    }

    public void fireNumberPop(Integer uid) {
        for (InMessage item: fireListeningList){
            if (item.getUid().equals(uid)){
                fireListeningList.remove(item);
                break;
            }
        }
    }

    public void smogNumberPush(InMessage inMessage) {
        smogListeningList.add(inMessage);
    }

    public void smogNumberPop(Integer uid) {
        for (InMessage item: smogListeningList){
            if (item.getUid().equals(uid)){
                smogListeningList.remove(item);
                break;
            }
        }
    }

    public void infraredNumberPush(InMessage inMessage) {
        infraredListeningList.add(inMessage);
    }

    public void infraredNumberPop(Integer uid) {
        for (InMessage item: infraredListeningList){
            if (item.getUid().equals(uid)){
                infraredListeningList.remove(item);
                break;
            }
        }
    }

    public void distanceNumberPush(InMessage inMessage) {
        distanceListeningList.add(inMessage);
    }

    public void distanceNumberPop(Integer uid) {
        for (InMessage item: distanceListeningList){
            if (item.getUid().equals(uid)){
                distanceListeningList.remove(item);
                break;
            }
        }
    }

    public void sendFireNumber(){
        template.convertAndSend("/sensorData/sendFireNumber", fireListeningList);
    }

    public void sendSmogNumber(){
        template.convertAndSend("/sensorData/sendSmogNumber", smogListeningList);
    }

    public void sendInfraredNumber(){
        template.convertAndSend("/sensorData/sendInfraredNumber", infraredListeningList);
    }

    public void sendDistanceNumber(){
        template.convertAndSend("/sensorData/sendDistanceNumber", distanceListeningList);
    }

    public void changeChatState(CommonChat commonChat) {
        commonChatService.changeReadState(commonChat);
    }
}
