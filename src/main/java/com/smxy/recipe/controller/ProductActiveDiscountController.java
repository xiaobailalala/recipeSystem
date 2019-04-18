package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.service.ProductActiveDiscountService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Demo ProductActiveDiscountController
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 15:53
 */
@PathController("/merchant/productActiveDiscount")
public class ProductActiveDiscountController {

    private final ProductActiveDiscountService productActiveDiscountService;

    @Autowired
    public ProductActiveDiscountController(ProductActiveDiscountService productActiveDiscountService) {
        this.productActiveDiscountService = productActiveDiscountService;
    }

    @GetMapping("/goDiscountAll")
    public String goDiscountAll() {
        return "merchant/pages/product/active/discounts_list";
    }


    @RequiresPermissions("product:select")
    @GetMapping("/getAllDiscountProductByMid/{mid}")
    @ResponseBody
    public Map<String, Object> getAllDiscountProductByMid(@PathVariable("mid") Integer fMid, HttpServletRequest request) {
        return productActiveDiscountService.getProductActiveDiscountListByMid(fMid, request);
    }


    @RequiresPermissions("product:insert")
    @ResponseBody
    @PostMapping("/uploadProductActiveDiscount/{pid}/{mid}")
    public ResApi<String> uploadProductActiveDiscount(ProductActiveDiscount productActiveDiscount, @PathVariable("pid") Integer fPid, @PathVariable("mid") Integer fMid) {
        return productActiveDiscountService.insertProductActiveDiscount(productActiveDiscount, fPid, fMid);
    }

    @GetMapping("/editorProductActiveDiscount/{id}")
    public String editorProductActiveDiscount(@PathVariable("id") Integer fId, Model model){
        model.addAttribute("item", productActiveDiscountService.getProductActiveDiscountById(fId));
        return "merchant/pages/product/active/discounts_editor";
    }
    @RequiresPermissions("product:update")
    @ResponseBody
    @PutMapping("/editorProductActiveDiscount/{id}")
    public ResApi<String> editorProductActiveDiscount(@PathVariable("id") Integer fId, ProductActiveDiscount productActiveDiscount){
        System.out.println(productActiveDiscount);
        return productActiveDiscountService.editorProductActiveDiscountById(productActiveDiscount, fId);
    }

    @DeleteMapping("/deleteProductActiveDiscountById/{id}")
    @ResponseBody
    public ResApi<String> deleteProductActiveDiscountById(@PathVariable("id") Integer fId) {
        return productActiveDiscountService.deleteProductActiveDiscountById(fId);

    }
}
