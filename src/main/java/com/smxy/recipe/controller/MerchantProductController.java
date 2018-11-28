package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * Demo MerchantProductController
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 19:51
 */
@PathController("/merchant/merchantProduct")
public class MerchantProductController {

    @Autowired
    MerchantProductService merchantProductService;

    @RequiresPermissions("product:select")
    @ResponseBody
    @GetMapping("/list")
    public Map<String, Object> list() {
        return merchantProductService.productAll();
    }

    @RequiresPermissions("product:select")
    @GetMapping("/goProductAdd")
    public String goProductAdd() {
        return "/merchant/pages/product/add";
    }

    @RequiresPermissions("product:insert")
    @PostMapping("/add")
    @ResponseBody
    public ResApi<String> addProduct(@RequestParam("productImage") MultipartFile[] productImage, String pro_title, MerchantProduct merchantProduct, String[] pro_price, String[] pro_repertory,
                                     @RequestParam("marqueImage") MultipartFile[] marqueImage, String[] productDetailsContent,
                                     @RequestParam("detailsImage") MultipartFile[] detailsImage, String[] marqueName, HttpServletRequest request) {
        System.out.println("productImage:" + productImage.length + ",pro_title:" + pro_title + ",merchantProduct:" + merchantProduct.toString() + ",price:" + Arrays.toString(pro_price) + ",repertory:"
                + Arrays.toString(pro_repertory) + ",marqueImage:" + marqueImage.length + ",productDetailsContent:" +
                Arrays.toString(productDetailsContent) + ",detailsImage:" + detailsImage.length + ",marqueName:" + marqueName.length + "request:");
        return merchantProductService.saveProduct(productImage, pro_title, merchantProduct, pro_price, pro_repertory, marqueImage, productDetailsContent, detailsImage, marqueName, request);
    }

    @RequiresPermissions("product:delete")
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResApi<String> deleteProductById(@PathVariable("id") Integer fId){
        return merchantProductService.deleteProductBuId(fId);
    }

    @RequiresPermissions("product:update")
    @PutMapping("/update/{id}/{state}")
    @ResponseBody
    public ResApi<String> updateProductStatus(@PathVariable("state") String state,@PathVariable("id") Integer id ){
        return merchantProductService.updateProductStatusById(id, state);
    }
}
