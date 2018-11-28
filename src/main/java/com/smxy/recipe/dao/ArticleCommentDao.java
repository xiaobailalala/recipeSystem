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
 * Build File @date: 2018/11/23 20:19
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.ArticleComment;
import com.smxy.recipe.entity.ArticleCommentGreat;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentDao {

    List<ArticleComment> getInfoByAid(Integer fAid);

    Integer getCountByAid(Integer fAid);

    ArticleComment getInfoByIdBrief(Integer fId);

    List<ArticleCommentGreat> getGreatCount(Integer fId);

    Integer saveInfoGreat(@Param("fUid") Integer uid, @Param("fCid") Integer cid);

    Integer deleteInfoGreat(@Param("fUid") Integer uid, @Param("fCid") Integer cid);

    Integer saveInfo(ArticleComment articleComment);
}
