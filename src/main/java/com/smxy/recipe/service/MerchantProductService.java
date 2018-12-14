package com.smxy.recipe.service;

import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.utils.ResApi;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Demo MerchantProductService
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 15:19
 */
public interface MerchantProductService {
    /**
     * 功能描述:获取所有商品类
     *
     * @return Object(所有商品类)
     * @auther yangyihui
     * @date 2018/11/22 0022 10:02
     */
    Map<String, Object> productAll();

    /**
     * 功能描述: 根据商家ID获取商品所有信息
     * @param mId 商家ID
     * @return : java.util.Map<java.lang.String,java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/11 0011 21:05
     */
    Map<String, Object> productAllPyId(Integer mId);

    /**
     * 功能描述: 保存一条商品类
     *
     * @param productImage          商品主图图片
     * @param pro_title             商品名称
     * @param merchantProduct       商品实体类
     * @param pro_price             商品价格
     * @param pro_repertory         商品库存
     * @param marqueImage           商品类型图片
     * @param productDetailsContent 商品详情文字
     * @param detailsImage          商品详情图片
     * @param marqueName            商品类型名称
     * @param request               HttpServletRequest对象
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String> ResApi工具类
     * @author : yangyihui
     * @date : 2018/12/8 0008 22:49
     */
    ResApi<String> saveProduct(MultipartFile[] productImage, String pro_title, MerchantProduct merchantProduct, String[] pro_price, String[] pro_repertory,
                               MultipartFile[] marqueImage, String[] productDetailsContent,
                               MultipartFile[] detailsImage, String[] marqueName, HttpServletRequest request);

    /**
     * 功能描述: 根据ID删除商品
     *
     * @param fId 要删除的商品ID
     * @return ResApi工具类
     * @auther yangyihui
     * @date 2018/11/26 0026 11:26
     */
    ResApi<String> deleteProductBuId(Integer fId);

    /**
     * 功能描述: 根据商品ID 更新商品状态
     *
     * @param fId    商品ID
     * @param fState 商品状态
     * @return ResApi 工具类
     * @auther yangyihui
     * @date 2018/11/26 0026 16:07
     */
    ResApi<String> updateProductStatusById(Integer fId, String fState);

    ResApi<String> updateProductInfo(Integer fId, MerchantProduct merchantProduct,
                                     MultipartFile[] productImage, MultipartFile[] proMarqueImg,
                                     MultipartFile[] proDetailsImg, String proTitle,
                                     String[] marqueName, String[] marquePrice,
                                     String[] marqueRepository, String[] productDetailsContent,
                                     Integer[] productImageId, Integer[] marqueId,
                                     Integer[] productDetailsId, Integer[] deleteMarqueImageFlag,
                                     Integer[] deleteDetailImageFlag, Integer[] marqueImageFlag,
                                     Integer[] detailImageFlag,
                                     HttpServletRequest request);

    /**
     * 功能描述: 根据ID获取商品信息
     *
     * @param fId 商品ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/2 0002 14:11
     */
    ResApi<Object> getProductById(Integer fId);

}
