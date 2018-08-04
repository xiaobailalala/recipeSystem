/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.service 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:31:29 
 */
package com.smxy.recipe.service;

import org.springframework.web.multipart.MultipartFile;

import com.smxy.recipe.entity.Common_user;
import com.smxy.recipe.utils.ResApi;

/**
 * @author zpx
 *
 */
public interface Common_userService {
	public ResApi<Common_user> commonUserLogin(Common_user common_user);
	public ResApi<String> commonUserReg(Common_user common_user);
	public ResApi<String> commonUsersaveHead(MultipartFile file, int img, String preImg);
	public ResApi<Common_user> commonUserSaveInfo(Common_user common_user);
}
