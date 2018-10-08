/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/22 19:58
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.webApp;

import com.smxy.recipe.utils.api.CodeApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/mob/common")
public class CommonMobController {

    @PostMapping("/getCode")
    public Map<String, Object> getCode(String num){
        Random random = new Random();
        String code="";
        for (int i=0;i<6;i++) {
            code+=random.nextInt(10);
        }
        CodeApi.getRequest2(num,code);
        Map<String, Object> map=new HashMap<>();
        map.put("code", code);
        return map;
    }

}
