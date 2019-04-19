package com.smxy.recipe.controller.merchantapp;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.entity.MerchantProductDetails;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import org.omg.CORBA.OBJECT_NOT_EXIST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.element.NestingKind;
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

    @Autowired
    public MerchantProductMobController(MerchantProductService merchantProductService) {
        this.merchantProductService = merchantProductService;
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

    public ResApi<Object> update(@PathVariable("pid") Integer pid, @RequestParam("productImage") MultipartFile[] productImage,
                                 @RequestParam("marqueImage") MultipartFile[] marqueImage, @RequestParam("productName") String productName,
                                 @RequestParam("productClassifyID") Integer productClassifyID, @RequestParam("jsonArray") String json,
                                 @RequestParam("freightID") Integer freightID) {
        return null;
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
    public ResApi<String> saveProductDetail(@RequestParam("images") MultipartFile[] images, @RequestParam("details") String[] details, @PathVariable("id") Integer userId) {
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

}