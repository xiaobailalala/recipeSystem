package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantProductDetails;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.service.ProductActiveDiscountService;
import com.smxy.recipe.service.ProductActiveReductionConditionService;
import com.smxy.recipe.service.ProductActiveReductionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Demo MerchantProductMobController
 *
 * @author Yangyihui
 * @date 2018/12/6 0006 15:39
 */
@PathRestController("/merchantMob/merchantProductMob")
public class MerchantProductMobController {

    private final MerchantProductService merchantProductService;
    private final ProductActiveDiscountService productActiveDiscountService;
    private final ProductActiveReductionService productActiveReductionService;
    private final ProductActiveReductionConditionService productActiveReductionConditionService;

    @Autowired
    public MerchantProductMobController(MerchantProductService merchantProductService, ProductActiveDiscountService productActiveDiscountService, ProductActiveReductionService productActiveReductionService, ProductActiveReductionConditionService productActiveReductionConditionService) {
        this.merchantProductService = merchantProductService;
        this.productActiveDiscountService = productActiveDiscountService;
        this.productActiveReductionService = productActiveReductionService;
        this.productActiveReductionConditionService = productActiveReductionConditionService;
    }

    @GetMapping("/getAllProduct")
    public ResApi<Object> getAllProduct() {
        return ResApi.getSuccess(merchantProductService.productAll());
    }

    @GetMapping("/list/{id}")
    public Map<String, Object> list(@PathVariable("id") Integer mId, HttpServletRequest request) {
        return merchantProductService.productAllById(mId);
    }

    @PostMapping("/add/{id}")
    public ResApi<Object> add(@PathVariable("id") Integer userID,
                              @RequestParam("productImage") MultipartFile[] productImage,
                              @RequestParam("marqueImage") MultipartFile[] marqueImage, @RequestParam("productName") String productName,
                              @RequestParam("productClassifyID") Integer productClassifyID, @RequestParam("jsonArray") String json,
                              @RequestParam("freightID") Integer freightID) {
        return merchantProductService.mobSaveProduct(userID, productImage, marqueImage, productName, productClassifyID, json, freightID);
    }

    @DeleteMapping("/delete/{id}")
    public ResApi<String> deleteProductById(@PathVariable("id") Integer fId) {
        return merchantProductService.deleteProductBuId(fId);
    }

    @PostMapping("/updateProduct/{uId}/{pId}")
    public ResApi<String> update(@PathVariable("uId") Integer uId, @PathVariable("pId") Integer pId, @RequestParam("productImage") MultipartFile[] productImage,
                                 @RequestParam("productImageId") Integer[] productImageId,
                                 @RequestParam("freightID") Integer freightId,
                                 @RequestParam("productName") String productName, @RequestParam("productClassifyID") Integer productClassifyID,
                                 @RequestParam("marqueImage") MultipartFile[] marqueImage,
                                 @RequestParam("marqueId") Integer[] marqueId, @RequestParam("marqueName") String[] marqueName,
                                 @RequestParam("marquePrice") String[] marquePrice,
                                 @RequestParam("marqueRepository") String[] marqueRepository,
                                 @RequestParam("marqueImageFlag") Integer[] marqueImageFlag) {
        return merchantProductService.mobUpdateProduct(uId, pId, productImage, productImageId, freightId, productName, productClassifyID, marqueImage, marqueId, marqueName, marquePrice, marqueRepository, marqueImageFlag);
    }

    @GetMapping("/getProductByID/{id}")
    public ResApi<Object> getProductByID(@PathVariable("id") Integer fId) {
        return merchantProductService.getProductById(fId);
    }


    @PostMapping("/update/{id}/{state}")
    @ResponseBody
    public ResApi<String> updateProductStatus(@PathVariable("state") String state, @PathVariable("id") Integer id) {
        return merchantProductService.updateProductStatusById(id, state);
    }

    @PostMapping("/saveProductDetail/{id}")
    public ResApi<String> saveProductDetail(@RequestParam("images") MultipartFile[] images, @RequestParam("details") String details, @PathVariable("id") Integer userId) {
        return merchantProductService.mobSaveProductDetails(images, details, userId);
    }

    @GetMapping("/getProductDetailByPid/{pid}")
    public ResApi<List<MerchantProductDetails>> getProductDetailByPid(@PathVariable("pid") Integer pid) {
        return merchantProductService.getProductDetailByPid(pid);
    }

    @PostMapping("/editorProductDetailByPid/{pid}")
    public ResApi<String> editorProductDetailByPid(@PathVariable("pid") Integer pid, @RequestParam("images") MultipartFile[] images,
                                                   @RequestParam("details") String[] details, @RequestParam("ids") Integer[] ids) {
        return merchantProductService.editorProductDetailByPid(pid, images, details, ids);
    }



    //商品活动
    @GetMapping("/getAllDiscountProductByMid/{mid}")
    public Map<String, Object> getAllDiscountProductByMid(@PathVariable("mid") Integer fMid, HttpServletRequest request) {
        return productActiveDiscountService.getProductActiveDiscountListByMid(fMid, request);
    }

    @PostMapping("/uploadProductActiveDiscount/{pid}/{mid}")
    public ResApi<String> uploadProductActiveDiscount(ProductActiveDiscount productActiveDiscount, @PathVariable("pid") Integer fPid, @PathVariable("mid") Integer fMid) {
        return productActiveDiscountService.insertProductActiveDiscount(productActiveDiscount, fPid, fMid);
    }

    @PostMapping("/editorProductActiveDiscount/{id}")
    public ResApi<String> editorProductActiveDiscount(@PathVariable("id") Integer fId, ProductActiveDiscount productActiveDiscount){
        System.out.println(productActiveDiscount);
        return productActiveDiscountService.editorProductActiveDiscountById(productActiveDiscount, fId);
    }

    @PostMapping("/deleteProductActiveDiscountById/{id}")
    public ResApi<String> deleteProductActiveDiscountById(@PathVariable("id") Integer fId) {
        return productActiveDiscountService.deleteProductActiveDiscountById(fId);
    }

    @GetMapping("/getProductActiveDiscountById/{id}")
    public ResApi<Object> getProductActiveDiscountById(@PathVariable("id") Integer fId) {
        return productActiveDiscountService.getProductActiveDiscountById(fId);
    }

    @PostMapping("/editorProductActiveReductionById/{id}")
    public ResApi<String> editorProductActiveReductionById(@PathVariable("id") Integer fId, ProductActiveReduction productActiveReduction,
                                                           @RequestParam("conditionID") Integer[] conditionID,
                                                           @RequestParam("newfullmoney") Double[] newFullMoney,
                                                           @RequestParam("newreducemoney") Double[] newReduceMoney,
                                                           @RequestParam("fullmoney") Double[] fullMoney,
                                                           @RequestParam("reducemoney") Double[] reduceMoney){
        return productActiveReductionService.editorProductActiveReductionById(fId, productActiveReduction, conditionID, newFullMoney, newReduceMoney, fullMoney, reduceMoney);
    }

    @PostMapping("/uploadProductActiveReduction/{pid}/{mid}")
    public ResApi<String> uploadProductActiveReduction(ProductActiveReduction productActiveReduction, @RequestParam("fullmoney") Double[] fullMoney,
                                                       @RequestParam("reducemoney") Double[] reduceMoney, @PathVariable("pid") Integer fPid, @PathVariable("mid") Integer fMid) {
        return productActiveReductionService.insertProductActiveReduction(productActiveReduction, fullMoney, reduceMoney, fPid, fMid);
    }

    @GetMapping("/getProductActiveReductionListByMid/{mid}")
    public Map<String, Object> getProductActiveReductionListByMid(@PathVariable("mid") Integer fMid, HttpServletRequest request) {
        return productActiveReductionService.getProductActiveReductionListByMid(fMid, request);
    }

    @PostMapping("/deleteProductActiveReductionById/{id}")
    public ResApi<String> deleteProductActiveReductionById(@PathVariable("id") Integer fId) {
        return productActiveReductionService.deleteProductActiveReductionById(fId);
    }

    @GetMapping("/getProductActiveReductionListById/{fId}")
    public ResApi<Object> getProductActiveReductionListById(@PathVariable("fId") Integer fId) {
        return productActiveReductionService.getProductActiveDiscountById(fId);
    }

    @PostMapping("/deleteProductActiveReductionConditionById/{id}")
    public ResApi<String> deleteProductActiveReductionConditionById(@PathVariable("id") Integer fId) {
        return productActiveReductionConditionService.deleteProductActiveReductionConditionById(fId);
    }

}