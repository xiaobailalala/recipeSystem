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

import com.smxy.recipe.entity.sensor.Dht11Data;
import com.smxy.recipe.entity.sensor.Gp2y1051Data;
import com.smxy.recipe.entity.sensor.Hcsr04Data;
import com.smxy.recipe.entity.sensor.Hcsr501Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sensorData")
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

    @GetMapping("/getDht11Data")
    @ResponseBody
    public Map<String, Object> getDht11Data(String rh, String tmp) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("success", "success");
        if (dht11List.size() == listMaxCount) {
            dht11List.remove(dht11List.get(0));
        }
        dht11List.add(new Dht11Data(rh, tmp));
        return map;
    }

    @GetMapping("/getGp2y1051Data")
    @ResponseBody
    public Map<String, Object> getGp2y1051Data(String pm) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("success", "success");
        if (gp2y1051DataList.size() == listMaxCount) {
            gp2y1051DataList.remove(gp2y1051DataList.get(0));
        }
        gp2y1051DataList.add(new Gp2y1051Data(pm));
        return map;
    }

    @GetMapping("/getSensorData")
    @ResponseBody
    public Map<String, Object> getSensorData(String pm, String rh, String tmp) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("success", "success");
        if (gp2y1051DataList.size() == listMaxCount) {
            gp2y1051DataList.remove(gp2y1051DataList.get(0));
        }
        gp2y1051DataList.add(new Gp2y1051Data(pm));
        if (dht11List.size() == listMaxCount) {
            dht11List.remove(dht11List.get(0));
        }
        dht11List.add(new Dht11Data(rh, tmp));
        return map;
    }

    @GetMapping("/getBodyDistance")
    @ResponseBody
    public Map<String, Object> getBodyDistance(String body, String dis) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("success", "success");
        if (hcsr04DataList.size() == listMaxCount) {
            hcsr04DataList.remove(hcsr04DataList.get(0));
        }
        hcsr04DataList.add(new Hcsr04Data(dis));
        if (hcsr501DataList.size() == listMaxCount) {
            hcsr501DataList.remove(hcsr501DataList.get(0));
        }
        hcsr501DataList.add(new Hcsr501Data(body));
        return map;
    }


    @GetMapping("/test1")
    @ResponseBody
    public List<Dht11Data> test1() {
        return SensorDataApi.dht11List;
    }

    @GetMapping("/test2")
    @ResponseBody
    public List<Gp2y1051Data> test2() {
        return SensorDataApi.gp2y1051DataList;
    }


}

