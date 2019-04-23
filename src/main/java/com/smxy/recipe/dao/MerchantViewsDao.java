package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantViews;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Demo MerchantViewsDao
 *
 * @author Yangyihui
 * @date 2019/4/21 0021 17:41
 */
public interface MerchantViewsDao {

    /**
     * 功能描述: 获取一周内访问量
     *
     * @param fMid   1
     * @param monday
     * @param sunday
     * @return : java.util.List<com.smxy.recipe.entity.MerchantViews>
     * @author : yangyihui
     * @date : 2019/4/21 0021 17:43
     */
    List<MerchantViews> getViewWeekByMerchantId(@Param("fMid") Integer fMid, @Param("monday") String monday, @Param("sunday") String sunday);

    /**
     * 功能描述: 获取访问量总数
     *
     * @param fMid 1
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2019/4/21 0021 17:43
     */
    Integer getViewsCountByMerchantId(Integer fMid);

    /**
     * 功能描述: 获取所有有访问量的商家id
     *
     * @return : java.util.List<java.lang.Integer>
     * @author : yangyihui
     * @date : 2019/4/21 0021 20:08
     */
    List<Integer> getAllViewsMerchantId();

    /**
     * 功能描述: 插入访问量数据
     *
     * @param viewsList 1
     * @return : java.lang.Integer
     * @author : yangyihui
     * @date : 2019/4/22 0022 8:26
     */
    Integer insertViewsList(@Param("viewsList") List<MerchantViews> viewsList);

    List<MerchantViews> getViewsCountByMerchantIdsAndTime(@Param("idList") List<Integer> idList, @Param("startTime") String startTime, @Param("endTime") String endTime);
}
