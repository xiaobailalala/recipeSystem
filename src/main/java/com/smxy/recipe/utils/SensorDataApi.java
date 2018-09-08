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

import com.smxy.recipe.entity.Dht11Data;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequestMapping("/sensorData")
public class SensorDataApi {

    public static List<Dht11Data> dht11List;

    static {
        dht11List=new ArrayList<>();
    }

    @GetMapping("/getDht11Data")
    @ResponseBody
    public Map<String, Object> getDht11Data(String rh, String tmp){
        Map<String, Object> map=new HashMap<>();
        map.put("success","success");
        if (dht11List.size()==30){
            dht11List.remove(dht11List.get(0));
        }
        dht11List.add(new Dht11Data(rh, tmp));
        return map;
    }


    @GetMapping("/test")
    @ResponseBody
    public List<Dht11Data> test(){
        System.out.println(213);
        return SensorDataApi.dht11List;
    }

}
