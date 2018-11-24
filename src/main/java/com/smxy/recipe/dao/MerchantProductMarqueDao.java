package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductMarque;

import java.util.List;
import java.util.Map;

/**
 * Demo MerchantProductMarqueDao
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 09:58
 */
public interface MerchantProductMarqueDao {
    /**
     * 功能描述: 根据商品型号类ID，商品ID 查询所有商品型号
     * @param map 商品型号ID Pid，以及商品型号类ID fMarqueclaid 的参数集合
     * @return 符合条件的商品型号
     * @auther yangyihui
     * @date 2018/11/20 0020 10:27
     */
    List<MerchantProductMarque> getMarqueByClassifyIdAndProductId(Map<String,Integer> map);

    Integer saveMarqueInfo(MerchantProductMarque merchantProductMarque);
}
