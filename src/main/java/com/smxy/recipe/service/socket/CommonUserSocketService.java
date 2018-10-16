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

import com.smxy.recipe.entity.tools.InMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("commonUserSocketService")
public class CommonUserSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    private static List<InMessage> fireListeningList;

    private static List<InMessage> smogListeningList;

    static {
        fireListeningList = new ArrayList<>();
        smogListeningList = new ArrayList<>();
    }

    public void fireNumberPush(InMessage inMessage) {
        fireListeningList.add(inMessage);
    }

    public void smogNumberPush(InMessage inMessage) {
        smogListeningList.add(inMessage);
    }

    public void fireNumberPop(Integer uid) {
        for (InMessage item: fireListeningList){
            if (item.getUid().equals(uid)){
                fireListeningList.remove(item);
                break;
            }
        }
    }

    public void smogNumberPop(Integer uid) {
        for (InMessage item: smogListeningList){
            if (item.getUid().equals(uid)){
                smogListeningList.remove(item);
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
}
