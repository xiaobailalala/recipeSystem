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

    /**
     * 功能描述: 保存商品型号信息
     * @param merchantProductMarque 商品类型对象
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/23 0023 20:23
     */
    Integer saveMarqueInfo(MerchantProductMarque merchantProductMarque);

    /**
     * 功能描述: 根据商品ID删除对应类
     * @param fPid 商品Id
     * @return 数据库更新数
     * @auther yangyihui
     * @date 2018/11/26 0026 20:30
     */
    Integer deleteMarqueByFPid(Integer fPid);

    /**
     * 功能描述: 根据商品类型ID删除对应商品类型
     * @param fId 商品类型ID
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2018/12/9 0009 21:50
     */
    Integer deleteMarqueByFId(Integer fId);

    /**
     * 功能描述: 更新商品类型
     * @param merchantProductMarque 商品类型
     * @return : java.lang.Integer 数据库更新数
     * @author : yangyihui
     * @date : 2018/12/9 0009 20:39
     */
    Integer updateMarqueInfo(MerchantProductMarque merchantProductMarque);

    /**
     * 功能描述: 根据商品类型ID获取商品类型图片路径
     * @param fId 商品类型ID
     * @return : com.smxy.recipe.entity.MerchantProductMarque
     * @author : yangyihui
     * @date : 2018/12/9 0009 21:07
     */
    String getMarqueImagePathById(Integer fId);

    /**
     * 功能描述: 根据商品类型ID获取商品类型
     * @param fId 1
     * @return : com.smxy.recipe.entity.MerchantProductMarque
     * @author : yangyihui
     * @date : 2019/4/15 12:03
     */
    MerchantProductMarque getMarqueById(Integer fId);

    /**
     * 功能描述: 根据商品ID查找所有商品类型ID
     * @param fPid 商品ID
     * @return : java.util.List<java.lang.Integer>
     * @author : yangyihui
     * @date : 2018/12/9 0009 21:47
     */
    List<Integer> getMarqueId(Integer fPid);
}
