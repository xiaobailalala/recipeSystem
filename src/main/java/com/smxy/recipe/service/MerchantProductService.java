package com.smxy.recipe.service;

import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.entity.MerchantProductClassify;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Demo MerchantProductService
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 15:19
 */
public interface MerchantProductService {
    /**
     * 功能描述:获取所有商品类
     * @return 所有商品类
     * @auther yangyihui
     * @date 2018/11/22 0022 10:02
     */
    List<MerchantProduct> productAll();
    /**
     * 功能描述: 保存一条商品类
     * @param merchantProduct
     * @return ResApi工具类
     * @auther yangyihui
     * @date 2018/11/22 0022 10:03
     */
    ResApi<String> saveProduct(MultipartFile[] productImage, String pro_title, MerchantProduct merchantProduct, String[] pro_price, String[] pro_repertory,
                               MultipartFile[] marqueImage, String[] productDetailsContent,
                               MultipartFile[] detailsImage,String[] marqueName, HttpServletRequest request);
}
