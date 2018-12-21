package com.smxy.recipe.service;

import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.utils.ResApi;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Demo ProductActiveDiscountService
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 16:10
 */
public interface ProductActiveDiscountService {
    /**
     * 功能描述: 向商品添加限时折扣活动
     * @param productActiveDiscount 限时活动实体类
     * @param fPid 商品ID
     * @param fMid 商家ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/18 0018 15:44
     */
    ResApi<String> insertProductActiveDiscount(ProductActiveDiscount productActiveDiscount, Integer fPid, Integer fMid);

    /**
     * 功能描述: 通过商家ID获取所有有限时活动的商品
     * @param fMid 商家ID
     * @param httpServletRequest 请求域数据（用于分页）
     * @return : java.util.Map<java.lang.String,java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/19 0019 11:26
     */
    Map<String, Object> getProductActiveDiscountListByMid(Integer fMid, HttpServletRequest httpServletRequest);

    /**
     * 功能描述: 更新商品限时折扣
     * @param productActiveDiscount 商品限时折扣活动实体类
     * @param fId 活动ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/20 0020 10:36
     */
    ResApi<String> editorProductActiveDiscountById(ProductActiveDiscount productActiveDiscount, Integer fId);

    /**
     * 功能描述: 通过ID获取限时活动信息
     * @param fId 限时折扣活动ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/20 0020 11:02
     */
    ResApi<Object> getProductActiveDiscountById(Integer fId);

    /**
     * 功能描述: 通过ID删除限时活动信息
     * @param fId 限时折扣活动ID
     * @return : com.smxy.recipe.utils.ResApi<java.lang.String>
     * @author : yangyihui
     * @date : 2018/12/20 0020 20:32
     */
    ResApi<String> deleteProductActiveDiscountById(Integer fId);

}
