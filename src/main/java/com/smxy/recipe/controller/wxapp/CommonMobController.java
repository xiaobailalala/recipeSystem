/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/22 19:58
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.wxapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.utils.api.CodeApi;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@PathRestController("/mob/common")
public class CommonMobController {

    @PostMapping("/getCode")
    public Map<String, Object> getCode(String num) {
        Random random = new Random();
        String code = "";
        int codeCount = 6;
        for (int i = 0; i < codeCount; i++) {
            code += random.nextInt(10);
        }
        CodeApi.getRequest(CodeApi.REG_VERIFY, num, code);
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", code);
        return map;
    }

}
