/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/2 21:12
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.utils;

import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.entity.sensor.Dht11Data;
import com.smxy.recipe.entity.sensor.Gp2y1051Data;
import com.smxy.recipe.entity.sensor.Hcsr04Data;
import com.smxy.recipe.entity.sensor.Hcsr501Data;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SensorDataApi {

    public static List<Dht11Data> dht11List;

    public static List<Gp2y1051Data> gp2y1051DataList;

    public static List<Hcsr04Data> hcsr04DataList;

    public static List<Hcsr501Data> hcsr501DataList;

    private int listMaxCount = 30;

    static {
        dht11List = new ArrayList<>(30);
        gp2y1051DataList = new ArrayList<>(30);
        hcsr04DataList = new ArrayList<>(30);
        hcsr501DataList = new ArrayList<>(30);
    }

    @RabbitListener(queues = "sensorData.queue")
    public void queueSensorData(String jsonData) {
        if (gp2y1051DataList.size() == listMaxCount) {
            gp2y1051DataList.remove(gp2y1051DataList.get(0));
        }
        gp2y1051DataList.add(new Gp2y1051Data(JSONObject.parseObject(jsonData).getString("pm")));
        if (dht11List.size() == listMaxCount) {
            dht11List.remove(dht11List.get(0));
        }
        dht11List.add(new Dht11Data(JSONObject.parseObject(jsonData).getString("rh"),
                JSONObject.parseObject(jsonData).getString("tmp")));
    }

    @RabbitListener(queues = "bodyDisData.queue")
    public void queueBodyDisData(String jsonData) {
        if (hcsr04DataList.size() == listMaxCount) {
            hcsr04DataList.remove(hcsr04DataList.get(0));
        }
        hcsr04DataList.add(new Hcsr04Data(JSONObject.parseObject(jsonData).getString("dis")));
        if (hcsr501DataList.size() == listMaxCount) {
            hcsr501DataList.remove(hcsr501DataList.get(0));
        }
        hcsr501DataList.add(new Hcsr501Data(JSONObject.parseObject(jsonData).getString("body")));
    }

}

