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
 * Build File @date: 2018/10/23 19:39
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.FoodComment;
import com.smxy.recipe.entity.FoodCommentGreat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FoodCommentDao {

    Integer saveInfo(FoodComment foodComment);

    List<FoodComment> getInfoByRid(Integer fRid);

    Integer saveInfoGreat(@Param("fCid") Integer cid, @Param("fUid") Integer uid);

    Integer deleteInfoGreat(@Param("fCid") Integer cid, @Param("fUid") Integer uid);

    FoodComment getInfoByIdBrief(Integer fId);

    List<FoodCommentGreat> getGreatCount(Integer fId);

}
