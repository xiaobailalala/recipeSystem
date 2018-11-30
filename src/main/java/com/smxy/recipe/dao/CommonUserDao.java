/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.dao 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:15:18 
 */
package com.smxy.recipe.dao;

/**
 * @author zpx
 *
 */

import com.smxy.recipe.entity.CommonUser;

import java.util.List;

public interface CommonUserDao {

	Integer isUser(CommonUser commonUser);

	CommonUser isLogin(CommonUser commonUser);
	
	Integer saveUser(CommonUser commonUser);
	
	Integer updateUserInfo(CommonUser commonUser);

	CommonUser getInfoByIdBrief(Integer fId);

	List<CommonUser> getInfoAll();

	Integer updatePwdByAccount(CommonUser commonUser);

	Integer updateUserHead(CommonUser commonUser);

	Integer updateUserBg(CommonUser commonUser);

	CommonUser getInfoById(Integer fId);

	CommonUser peopleInfoDetail(Integer fid);

}

