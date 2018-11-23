package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.entity.MerchantUser;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
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

    @ResponseBody
    @RequestMapping("/list")
    public List<MerchantProduct> list() {
        return merchantProductService.productAll();
    }

    @GetMapping("/goProductAdd")
    @RequiresPermissions("product:select")
    public String goProductAdd() {
        return "/merchant/pages/product/add";
    }

    @PostMapping("/add")
    @RequiresPermissions("product:insert")
    @ResponseBody
    public ResApi<String> addProduct(@RequestParam("productImage") MultipartFile[] productImage, String pro_title, MerchantProduct merchantProduct, String[] pro_price, String[] pro_repertory,
                                     @RequestParam("marqueImage") MultipartFile[] marqueImage, String[] productDetailsContent,
                                     @RequestParam("detailsImage") MultipartFile[] detailsImage, String[] marqueName, HttpServletRequest request) {
        System.out.println("productImage:" + productImage.length + ",pro_title:" + pro_title + ",merchantProduct:" + merchantProduct.toString() + ",price:" + Arrays.toString(pro_price) + ",repertory:"
                + Arrays.toString(pro_repertory) + ",marqueImage:" + marqueImage.length + ",productDetailsContent:" +
                Arrays.toString(productDetailsContent) + ",detailsImage:" + detailsImage.length + ",marqueName:" + marqueName.length + "request:");
        return merchantProductService.saveProduct(productImage, pro_title, merchantProduct, pro_price, pro_repertory, marqueImage, productDetailsContent, detailsImage, marqueName, request);
    }
}
