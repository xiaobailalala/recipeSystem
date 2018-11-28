/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/11/19 8:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.Collect;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectDao {
    Integer saveInfo(Collect collect);

    Integer deleteInfo(Collect collect);

    List<Collect> findByAllBrief(Collect collect);

    Integer getCountByRidAndTypeForRecipe(Integer fRid);

    Integer getCountByRidAndTypeForArticle(Integer fRid);

    List<Collect> findByUidAndType(@Param("fUid") Integer uid, @Param("fType") Integer type);
}
