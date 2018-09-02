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

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SensorDataApi {

    @GetMapping("/getDht11Data")
    @ResponseBody
    public Map<String, Object> getDht11Data(String rh, String tmp){
        System.out.println(rh+" "+tmp);
        Map<String, Object> map=new HashMap<>();
        map.put("success","success");
        return map;
    }

}
