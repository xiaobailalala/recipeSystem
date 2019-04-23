package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Demo MerchantOrderDao
 *
 * @author Yangyihui
 * @date 2019/4/6 0006 17:03
 */
public interface MerchantOrderDao {
    /**
     * 功能描述: 插入一个新的订单
     * @param merchantOrder 1
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2019/4/9 0009 20:50
     */
    Integer saveOrderById(MerchantOrder merchantOrder);

    /**
     * 功能描述: 根据id更新订单信息
     * @param orderId 1
     * @param fState
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2019/4/9 0009 20:51
     */
    Integer updateOrderState(@Param("orderId") Integer orderId,@Param("fState") Integer fState);

    /**
     * 功能描述: 根据merchant_user_id type查询订单详情
     * @param userId 1
     * @return : com.smxy.recipe.entity.MerchantOrder
     * @author : yangyihui
     * @date : 2019/4/9 0009 21:18
     */
    List<MerchantOrder> getOrderByTypeAndId(@Param("userId") Integer userId, @Param("fType") Integer fType);

    /**
     * 功能描述: 根据merchant_user_id type查询订单详（根据用户id 分组）
     * @param userId 1
     * @param fType 2
     * @return : java.util.List<com.smxy.recipe.entity.MerchantOrder>
     * @author : yangyihui
     * @date : 2019/4/21 0021 19:10
     */
    List<MerchantOrder> getOrderByTypeAndIdGroupByuserId(@Param("userId") Integer userId, @Param("fType") Integer fType);

    /**
     * 功能描述: 根据id 查询订单详情
     * @param orderId 1
     * @return : com.smxy.recipe.entity.MerchantOrder
     * @author : yangyihui
     * @date : 2019/4/9 0009 21:18
     */
    MerchantOrder getOrderById(Integer orderId);

    /**
     * 功能描述: 根据商家用户id查询所有订单信息
     * @param merId 1
     * @return : java.util.List<com.smxy.recipe.entity.MerchantOrder>
     * @author : yangyihui
     * @date : 2019/4/13 0013 16:28
     */
    List<MerchantOrder> getOrderByUserId(Integer merId);

    /**
     * 功能描述: 根据用户id、订单状态、订单类型、查询订单详情
     * @param userId 1
     * @param fState 2
     * @param fType 3
     * @return : java.util.List<com.smxy.recipe.entity.MerchantOrder>
     * @author : yangyihui
     * @date : 2019/4/13 0013 18:55
     */
    List<MerchantOrder> getOrderByUserIdAndStateAndType(@Param("userId") Integer userId, @Param("fState") Integer fState, @Param("fType") Integer fType);


    /**
     * 功能描述: 根据订单状态和id 获取订单信息
     * @param userId 1
     * @param fState 2
     * @return : java.util.List<com.smxy.recipe.entity.MerchantOrder>
     * @author : yangyihui
     * @date : 2019/4/15 19:54
     */
    List<MerchantOrder> getOrderByStateAndId(@Param("userId") Integer userId,@Param("fState") Integer fState);
}
