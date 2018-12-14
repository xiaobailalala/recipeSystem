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
 * Build File @date: 2018/10/11 13:58
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.socket;

import com.smxy.recipe.entity.sensor.Dht11Data;
import com.smxy.recipe.entity.sensor.Gp2y1051Data;
import com.smxy.recipe.entity.sensor.Hcsr04Data;
import com.smxy.recipe.entity.sensor.Hcsr501Data;
import com.smxy.recipe.utils.SensorDataApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service("recipeSocketService")
public class RecipeSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void getSensorFireData() {
        List<Dht11Data> dht11DataList = SensorDataApi.dht11List;
        template.convertAndSend("/sensorData/fire",
                dht11DataList.size() == 0 ? 0 : dht11DataList.get(dht11DataList.size() - 1));
    }

    public void getSensorSmogData() {
        List<Gp2y1051Data> gp2y1051DataList = SensorDataApi.gp2y1051DataList;
        template.convertAndSend("/sensorData/smog",
                gp2y1051DataList.size() == 0 ? 0 : gp2y1051DataList.get(gp2y1051DataList.size() - 1));
    }

    public void getSensorInfraredData() {
        List<Hcsr501Data> hcsr501DataList = SensorDataApi.hcsr501DataList;
        template.convertAndSend("/sensorData/infrared",
                hcsr501DataList.size() == 0 ? 0 : hcsr501DataList.get(hcsr501DataList.size() - 1));
    }

    public void getSensorDistanceData() {
        List<Hcsr04Data> hcsr04DataList = SensorDataApi.hcsr04DataList;
        template.convertAndSend("/sensorData/distance",
                hcsr04DataList.size() == 0 ? 0 : hcsr04DataList.get(hcsr04DataList.size() - 1));
    }
}
