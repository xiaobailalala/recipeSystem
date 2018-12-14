package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.dao.MerchantProductDetailsDao;
import com.smxy.recipe.entity.MerchantProductDetails;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Demo TestController
 *
 * @author Yangyihui
 * @date 2018/11/29 0029 09:26
 */
@PathRestController("/merchantMob/testController")
public class TestController {
    @Autowired
    MerchantProductDao merchantProductDao;
    @Autowired
    MerchantProductService merchantProductService;

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

    @RequestMapping("/testProductId")
    public ResApi<Object> testProductId(Integer fPid){
        return ResApi.getSuccess(merchantProductDao.getProductById(fPid));
    }

    @GetMapping("/edit/{id}")
    public ResApi<Object> testEditor(@PathVariable("id") Integer id){
        return merchantProductService.getProductById(id);
    }
}
