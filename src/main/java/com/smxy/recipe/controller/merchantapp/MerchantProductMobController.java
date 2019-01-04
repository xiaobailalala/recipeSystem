package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Demo MerchantProductMobController
 *
 * @author Yangyihui
 * @date 2018/12/6 0006 15:39
 */
@PathRestController("/merchantMob/merchantProductMob")
public class MerchantProductMobController {
    @SuppressWarnings("all")
    @Autowired
    private MerchantProductService merchantProductService;

    @GetMapping("/getAllProduct")
    public ResApi<Object> getAllProduct(){
        return ResApi.getSuccess(merchantProductService.productAll());
    }

    @RequiresPermissions("product:select")
    @GetMapping("/list/{id}")
    public Map<String, Object> list(@PathVariable("id") Integer mId, HttpServletRequest request) {
        return merchantProductService.productAllById(mId);
    }

    @ResponseBody
    @PostMapping("/add/{id}")
    public ResApi<String> add(@PathVariable("id") Integer userID,
                              @RequestParam("productImage") MultipartFile[] productImage,
                              @RequestParam("marqueImage") MultipartFile[] marqueImage, @RequestParam("productName") String productName,
                              @RequestParam("productClassifyID") Integer productClassifyID, @RequestParam("jsonArray") String json,
                              @RequestParam("freightID") Integer freightID){
        return merchantProductService.mobSaveProduct(userID, productImage, marqueImage, productName, productClassifyID, json, freightID);
    }
}
