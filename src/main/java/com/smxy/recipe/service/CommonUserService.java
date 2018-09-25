/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.service 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:31:29 
 */
package com.smxy.recipe.service;

import org.springframework.web.multipart.MultipartFile;

import com.smxy.recipe.entity.CommonUser;
import com.smxy.recipe.utils.ResApi;

/**
 * @author zpx
 *
 */
public interface CommonUserService {
	ResApi<CommonUser> commonUserLogin(CommonUser common_user);
	ResApi<String> commonUserReg(CommonUser common_user);
	ResApi<String> commonUsersaveHead(MultipartFile file, int img, String preImg);
	ResApi<CommonUser> commonUserSaveInfo(CommonUser common_user);
}
