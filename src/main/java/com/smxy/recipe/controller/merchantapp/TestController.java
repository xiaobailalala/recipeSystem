package com.smxy.recipe.controller.merchantapp;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo TestController
 *
 * @author Yangyihui
 * @date 2018/11/29 0029 09:26
 */
@PathRestController("/merchantMob/testController")
public class TestController {

    private final MerchantProductDao merchantProductDao;
    private final MerchantProductService merchantProductService;

    @Autowired
    public TestController(MerchantProductDao merchantProductDao, MerchantProductService merchantProductService) {
        this.merchantProductDao = merchantProductDao;
        this.merchantProductService = merchantProductService;
    }

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

    @PostMapping("/testAddProduct/{id}")
    @ResponseBody
    public ResApi<String> testAddProduct(@PathVariable("id") Integer userID,
                                         @RequestParam("productImage") MultipartFile[] productImage,
                                         @RequestParam("marqueImage") MultipartFile[] marqueImage, @RequestParam("productName") String productName,
                                         @RequestParam("productClassifyID") Integer productClassifyID, @RequestParam("jsonArray") String json,
                                         @RequestParam("freightID") Integer freightID) {
        System.out.println(userID);
        System.out.println(productImage.length);
        System.out.println(marqueImage.length);
        System.out.println(productName);
        System.out.println(productClassifyID);
        System.out.println(json);
        JSONArray objects = JSONArray.parseArray(json);
        List<Map<String, Object>> marqueList = new ArrayList<>();
        for (Object arr : objects) {
            Map map = JSON.parseObject(arr.toString(), Map.class);
            Map<String, Object> tempMap = new HashMap<>(8);
            for (Object o : map.keySet()) {
                tempMap.put(o.toString(), map.get(o));
                System.out.println("key: " + o.toString() + "value: " + map.get(o));
            }
            marqueList.add(tempMap);
        }
        System.out.println(marqueList.size());
        for (Map<String, Object> map : marqueList) {
            String filename;
            if ((boolean)map.get("isImg")) {
                System.out.println("--------->"+map.get("isImg"));
            }
            System.out.println("--->"+map.get("price"));
            System.out.println("--->"+map.get("model"));
            System.out.println("--->"+map.get("inventory"));
//            if ("true".equals(map.get("isImg"))) {
//                System.out.println("--->"+map.get("model"));
//            }
        }
        System.out.println(freightID);
        return ResApi.getSuccess();
    }

//    public ResApi<String> testAddProductDetails()
}
