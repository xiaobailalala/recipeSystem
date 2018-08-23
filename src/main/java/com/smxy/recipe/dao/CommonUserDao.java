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

	public int isUser(CommonUser common_user);

	public CommonUser isLogin(CommonUser common_user);
	
	public int saveUser(CommonUser common_user);
	
	public int updateUserInfo(CommonUser common_user);
	
}

