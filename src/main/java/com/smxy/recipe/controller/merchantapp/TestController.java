package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.utils.ResApi;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Demo TestController
 *
 * @author Yangyihui
 * @date 2018/11/29 0029 09:26
 */
@PathRestController("/merchantApp/testController")
public class TestController {
    @PostMapping("/hello")
    @ResponseBody
    public Map<String, String> Hello(String name){
        Map<String, String> map = new HashedMap();
        System.out.println(name);
        map.put("testRes", name);
        return map;
    }

    @PostMapping("/testLogin")
    public ResApi<Object> testLogin(MerchantUser merchantUser){
        return ResApi.getSuccess(merchantUser);
    }
}
