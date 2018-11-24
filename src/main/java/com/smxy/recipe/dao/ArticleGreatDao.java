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
 * Build File @date: 2018/11/23 8:45
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ArticleGreat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleGreatDao {

    Integer saveInfo(@Param("fAid") Integer aid, @Param("fUid") Integer uid);

    Integer deleteInfo(@Param("fAid") Integer aid, @Param("fUid") Integer uid);

    Integer getCountByAid(Integer fAid);

    List<ArticleGreat> getByAidAndUid(@Param("fAid") Integer aid, @Param("fUid") Integer uid);

}
