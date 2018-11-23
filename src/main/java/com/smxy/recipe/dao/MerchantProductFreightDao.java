package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductFreight;

import java.util.List;

/**
 * Demo MerchantProductFreightDao
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 16:58
 */
public interface MerchantProductFreightDao {
    /**
     * 功能描述:获取所有商品运费模板
     * @return 所有商品运费模板集合
     * @auther yangyihui
     * @date 2018/11/22 0022 16:59
     */
    List<MerchantProductFreight> getAllMerchantProductFreight();
}
