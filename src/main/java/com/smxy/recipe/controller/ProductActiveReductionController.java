package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.service.ProductActiveReductionService;
import com.smxy.recipe.utils.ResApi;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;

/**
 * Demo ProductActiveReductionController
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 15:52
 */
@PathController("/merchant/productActiveReduction")
public class ProductActiveReductionController {
    @Autowired
    private ProductActiveReductionService productActiveReductionService;

    @GetMapping("/goReductionAll")
    public String goReductionAll(){
        return "merchant/pages/product/active/reduction_list";
    }



    @RequiresPermissions("product:insert")
    @ResponseBody
    @PostMapping("/uploadProductActiveReduction/{pid}/{mid}")
    public ResApi<String> uploadProductActiveReduction(ProductActiveReduction productActiveReduction, @RequestParam("fullmoney") Double[] fullMoney,
                                                       @RequestParam("reducemoney") Double[] reduceMoney, @PathVariable("pid") Integer fPid, @PathVariable("mid") Integer fMid) {
        return productActiveReductionService.insertProductActiveReduction(productActiveReduction, fullMoney, reduceMoney, fPid, fMid);
    }

    @RequiresPermissions("product:select")
    @ResponseBody
    @GetMapping("/getProductActiveReductionListByMid/{mid}")
    public Map<String, Object> getProductActiveReductionListByMid(@PathVariable("mid") Integer fMid, HttpServletRequest request) {
        return productActiveReductionService.getProductActiveReductionListByMid(fMid, request);
    }

    @DeleteMapping("/deleteProductActiveReductionById/{id}")
    @ResponseBody
    public ResApi<String> deleteProductActiveReductionById(@PathVariable("id") Integer fId) {
        return productActiveReductionService.deleteProductActiveReductionById(fId);
    }

    @GetMapping("/editorProductActiveReductionById/{id}")
    public String editorProductActiveReductionById(@PathVariable("id") Integer fId, Model model) {
        model.addAttribute("item", productActiveReductionService.getProductActiveDiscountById(fId));
        return "merchant/pages/product/active/reduction_editor";
    }

    @RequiresPermissions("product:update")
    @PutMapping("/editorProductActiveReductionById/{id}")
    @ResponseBody
    public ResApi<String> editorProductActiveReductionById(@PathVariable("id") Integer fId, ProductActiveReduction productActiveReduction,
                                                           @RequestParam("conditionID") Integer[] conditionID,
                                                           @RequestParam("newfullmoney") Double[] newFullMoney,
                                                           @RequestParam("newreducemoney") Double[] newReduceMoney,
                                                           @RequestParam("fullmoney") Double[] fullMoney,
                                                           @RequestParam("reducemoney") Double[] reduceMoney){
        System.out.println(fId);
        System.out.println(productActiveReduction);
        System.out.println(Arrays.toString(conditionID));
        System.out.println(Arrays.toString(newFullMoney));
        System.out.println(Arrays.toString(newReduceMoney));
        System.out.println(Arrays.toString(fullMoney));
        System.out.println(Arrays.toString(reduceMoney));
        return productActiveReductionService.editorProductActiveReductionById(fId, productActiveReduction, conditionID, newFullMoney, newReduceMoney, fullMoney, reduceMoney);
    }

}
