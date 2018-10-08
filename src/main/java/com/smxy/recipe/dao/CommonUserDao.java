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

public interface CommonUserDao {

	Integer isUser(CommonUser common_user);

	CommonUser isLogin(CommonUser common_user);
	
	Integer saveUser(CommonUser common_user);
	
	Integer updateUserInfo(CommonUser common_user);

	CommonUser getInfoByIdBrief(Integer fId);

}

