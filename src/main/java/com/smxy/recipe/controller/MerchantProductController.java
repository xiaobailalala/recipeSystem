package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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

    @SuppressWarnings("all")
    @Autowired
    MerchantProductService merchantProductService;

    @RequiresPermissions("product:select")
    @GetMapping("/goProductAdd")
    public String goProductAdd() {
        return "/merchant/pages/product/add";
    }

    @RequiresPermissions("product:select")
    @GetMapping("/goProductEdit")
    public String goEdit() {
        return "/merchant/pages/product/edit";
    }

    @RequiresPermissions("product:select")
    @GetMapping("/goProductList")
    public String goProductList() {
        return "/merchant/pages/product/list";
    }

    @RequiresPermissions("product:select")
    @ResponseBody
    @GetMapping("/list/{id}")
    public Map<String, Object> list(@PathVariable("id") Integer mId, HttpServletRequest request) {
        System.out.println(request.getParameter("page") + "----" + request.getParameter("limit"));
        return merchantProductService.productAllPage(mId, request);
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
    public ResApi<String> deleteProductById(@PathVariable("id") Integer fId) {
        return merchantProductService.deleteProductBuId(fId);
    }

    @RequiresPermissions("product:update")
    @PutMapping("/update/{id}/{state}")
    @ResponseBody
    public ResApi<String> updateProductStatus(@PathVariable("state") String state, @PathVariable("id") Integer id) {
        return merchantProductService.updateProductStatusById(id, state);
    }

    @RequiresPermissions("product:update")
    @PutMapping("/editor/{id}")
    @ResponseBody
    public ResApi<String> updateProduct(@PathVariable("id") Integer fId,
                                        @RequestParam("productImage") MultipartFile[] productImage,
                                        @RequestParam("proMarqueImg") MultipartFile[] proMarqueImg,
                                        @RequestParam("proDetailsImg") MultipartFile[] proDetailsImg,
                                        @RequestParam("pro_title") String proTitle,
                                        @RequestParam("marqueName") String[] marqueName,
                                        @RequestParam("marquePrice") String[] marquePrice,
                                        @RequestParam("marqueRepository") String[] marqueRepository,
                                        @RequestParam("productDetailsContent") String[] productDetailsContent,
                                        @RequestParam(value = "imgsId", required = false) Integer[] productImageId,
                                        @RequestParam(value = "marqueId", required = false) Integer[] marqueId,
                                        @RequestParam(value = "pro_details_Id", required = false) Integer[] productDetailsId,
                                        @RequestParam("deleteMarqueImageFlag") Integer[] deleteMarqueImageFlag,
                                        @RequestParam("deleteDetailImageFlag") Integer[] deleteDetailImageFlag,
                                        @RequestParam("marqueImageFlag") Integer[] marqueImageFlag,
                                        @RequestParam("detailImageFlag") Integer[] detailImageFlag,
                                        MerchantProduct merchantProduct,
                                        HttpServletRequest request) {
        System.out.println(fId + ", " + productImage.length + ", " + proMarqueImg.length + ", " + proDetailsImg.length + ", "
                + proTitle + ", " + marqueName.length + ", " + marquePrice.length + ", " + marqueRepository.length + ", "
                + productDetailsContent.length + ", " + Arrays.toString(productImageId) + ", " + Arrays.toString(marqueId) + ", " + Arrays.toString(productDetailsId));
        System.out.println(merchantProduct.toString());
        System.out.println(Arrays.toString(deleteMarqueImageFlag));
        System.out.println(Arrays.toString(deleteDetailImageFlag));
        System.out.println(Arrays.toString(marqueImageFlag));
        System.out.println(Arrays.toString(detailImageFlag));
        return merchantProductService.updateProductInfo(fId, merchantProduct, productImage, proMarqueImg,
                proDetailsImg, proTitle, marqueName, marquePrice, marqueRepository,
                productDetailsContent, productImageId, marqueId, productDetailsId,
                deleteMarqueImageFlag, deleteDetailImageFlag, marqueImageFlag, detailImageFlag, request);
    }

    @RequiresPermissions("product:update")
    @GetMapping("/editor/{id}")
    public String editProductInfo(@PathVariable("id") Integer fId, Model model) {
        model.addAttribute("item", merchantProductService.getProductById(fId));
        return "merchant/pages/product/edit";
    }

}
