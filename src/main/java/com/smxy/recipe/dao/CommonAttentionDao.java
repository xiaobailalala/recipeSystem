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
 * Build File @date: 2018/11/25 20:53
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.CommonAttention;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommonAttentionDao {

    Integer saveInfo(CommonAttention commonAttention);

    Integer findInfoByUidAndOidAndType(@Param("fUid") Integer uid, @Param("fOid") Integer oid, @Param("fType") Integer type);

    Integer deleteInfo(CommonAttention commonAttention);

    List<CommonAttention> findInfoByUidAndType(@Param("fUid") Integer uid, @Param("fType") Integer type);

    List<CommonAttention> findInfoByOidAndType(@Param("fOid") Integer oid, @Param("fType") Integer type);

}