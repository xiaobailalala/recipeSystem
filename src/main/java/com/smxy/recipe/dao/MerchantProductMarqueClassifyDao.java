package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantProductMarqueClassify;

import java.util.List;

/**
 * Demo MerchantProductMarqueClassifyDao
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 19:47
 */
public interface MerchantProductMarqueClassifyDao {
    /**
     * 功能描述: 得到所有商品型号类
     * @return 所有商品型号类
     * @auther yangyihui
     * @date 2018/11/19 0019 20:23
     */
    List<MerchantProductMarqueClassify> getAllMarqueClassify();
    /**
     * 功能描述: 保存一条商品型号类
     * @param merchantProductMarqueClassify 商品型号类实体类
     * @return 返回数据库更新数
     * @auther yangyihui
     * @date 2018/11/19 0019 20:24
     */
    Integer saveMarqueClassify(MerchantProductMarqueClassify merchantProductMarqueClassify);
    /**
     * 功能描述: 根据商品型号类ID删除一条商品型号类
     * @param fId 商品型号类ID
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/19 0019 20:25
     */
    Integer deleteMarqueClassifyById(Integer fId);
    /**
     * 功能描述:
     * @param merchantProductMarqueClassify 商品型号类实体类
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/19 0019 20:26
     */
    Integer updateMarqueClassify(MerchantProductMarqueClassify merchantProductMarqueClassify);

    /**
     * 功能描述: 通过ID过去商品型号类型
     * @param fId 商品型号类型ID
     * @return 商品型号类型实体类
     * @auther yangyihui
     * @date 2018/11/26 0026 7:46
     */
    MerchantProductMarqueClassify getMarqueClassifyById(Integer fId);
}
