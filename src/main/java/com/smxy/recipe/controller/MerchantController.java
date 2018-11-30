package com.smxy.recipe.controller;

/**
 * Demo MerchantController
 *
 * @author Yangyihui
 * @date 2018/11/30 0030 14:56
 */

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.utils.ToolsApi;
import com.smxy.recipe.utils.api.CodeApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@PathController("/merchant")
public class MerchantController {

    @PostMapping("/verifyCode")
    @ResponseBody
    public Map<String,Object> VerifyCode(String phoneNumber){
        int[] code = ToolsApi.randomArray(0, 9, 6);
        String CODE = "";
        for (int i : code) {
            CODE += i;
        }
        CodeApi.getRequest(CodeApi.REG_VERIFY, phoneNumber, CODE);
        Map<String,Object> map = new HashMap<>(8);
        map.put("code", CODE);
        return map;
    }
}
