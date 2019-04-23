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
 * Build File @date: 2019/4/21 13:54
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantUserLinkman;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MerchantUserLinkmanDao {

    MerchantUserLinkman queryJudgeExist(@Param("uid") Integer uid, @Param("oid") Integer oid, @Param("user") Integer user);

    Integer updateInfo(MerchantUserLinkman merchantUserLinkman);

    Integer insertInfo(MerchantUserLinkman merchantUserLinkman);

    List<MerchantUserLinkman> queryInfo(MerchantUserLinkman merchantUserLinkman);

}
