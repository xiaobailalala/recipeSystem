package com.smxy.recipe.controller;

/**
 * Demo MerchantController
 *
 * @author Yangyihui
 * @date 2018/11/30 0030 14:56
 */

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.service.MerchantProductClassifyService;
import com.smxy.recipe.service.ProductFreightService;
import com.smxy.recipe.service.ProductMarqueClassifyService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import com.smxy.recipe.utils.api.CodeApi;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@PathController("/merchantCommon")
public class MerchantCommonController {
    @SuppressWarnings("all")
    @Autowired
    private ProductFreightService productFreightService;

    @SuppressWarnings("all")
    @Autowired
    MerchantProductClassifyService merchantProductClassifyService;

    @SuppressWarnings("all")
    @Autowired
    private ProductMarqueClassifyService productMarqueClassifyService;

    @GetMapping("/login")
    public String goLogin() {
        return "merchant/login";
    }
    @GetMapping("/register")
    public String goRegister() {
        return "merchant/register";
    }

    @GetMapping("/merchantLogout")
    public String merchantLogout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/merchantCommon/login";
    }

    @PostMapping("/verifyCode")
    @ResponseBody
    public Map<String,Object> verifyCode(String phoneNumber){
        System.out.println(phoneNumber);
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

    @ResponseBody
    @GetMapping("/getAllFreight")
    public ResApi<Object> getAllFreight(){
        return productFreightService.getAllProductFreight();
    }

    @GetMapping("/getAllProductCla")
    @ResponseBody
    public ResApi<Object> getAllProductCla(){
        return merchantProductClassifyService.getAllProductClassify();
    }

    @GetMapping("/getAllMarqueClassify")
    @ResponseBody
    public ResApi<Object> getAllMarqueClassify(){
        return productMarqueClassifyService.getAllMarqueClassify();
    }
}
