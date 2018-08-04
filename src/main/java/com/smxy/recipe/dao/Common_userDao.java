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

import com.smxy.recipe.entity.Common_user;

public interface Common_userDao {

	public int isUser(Common_user common_user);

	public Common_user isLogin(Common_user common_user);
	
	public int saveUser(Common_user common_user);
	
	public int updateUserInfo(Common_user common_user);
	
}

