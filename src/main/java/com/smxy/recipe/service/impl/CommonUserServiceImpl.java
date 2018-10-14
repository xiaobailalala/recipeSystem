/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.serviceImpl 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:33:14 
 */
package com.smxy.recipe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smxy.recipe.dao.CommonUserDao;
import com.smxy.recipe.entity.CommonUser;
import com.smxy.recipe.service.CommonUserService;
import com.smxy.recipe.utils.FilePath;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zpx
 *
 */
@Service("commonUserService")
public class CommonUserServiceImpl implements CommonUserService {
	
	@Autowired
	private CommonUserDao commonUserDao;

	@Override
	public ResApi<CommonUser> commonUserLogin(CommonUser common_user) {
		// TODO Auto-generated method stub
		ResApi<CommonUser> resApi;
		if (commonUserDao.isUser(common_user)>0) {
			common_user.setFPassword(ToolsApi.toMD5(common_user.getFPassword()));
			common_user=commonUserDao.isLogin(common_user);
			if (common_user!=null) {
				resApi=new ResApi<>(200,"登录成功",common_user);
			}else {
				resApi=new ResApi<>(401,"密码不正确",null);
			}
		}else {
			resApi=new ResApi<>(400,"用户不存在，请先注册",null);
		}
		return resApi;
	}

	@Override
	public ResApi<String> commonUserReg(CommonUser common_user) {
		// TODO Auto-generated method stub
		ResApi<String> resApi;
		if (commonUserDao.isUser(common_user)>0) {
			resApi=new ResApi<>(402,"该用户已存在。",null);
		}else {
			common_user.setFPassword(ToolsApi.toMD5(common_user.getFPassword()));
			if (commonUserDao.saveUser(common_user)>0) {
				common_user.setFUsername("膳客"+common_user.getFId());
				if (commonUserDao.updateUserInfo(common_user)>0) {
					resApi=new ResApi<>(200,"注册成功",null);
				}else {
					resApi=new ResApi<>(401,"生成用户名失败。",null);
				}
			}else {
				resApi=new ResApi<>(400,"保存失败，请重试。",null);
			}
		}
		return resApi;
	}

	@Override
	public ResApi<String> commonUsersaveHead(MultipartFile file,int img,String preImg) {
		// TODO Auto-generated method stub
		if (ToolsApi.imgLimit(ToolsApi.suffixName(file.getOriginalFilename()))) {
			String name = ToolsApi.multipartFile_upload_file(file, null);
			return new ResApi<>(200,"success",name);
		}else {
			return new ResApi<>(400,"头像上传失败",null);
		}
	}

	@Override
	public ResApi<CommonUser> commonUserSaveInfo(CommonUser common_user) {
		// TODO Auto-generated method stub
		int res=commonUserDao.updateUserInfo(common_user);
		if (res>0) {
			return new ResApi<>(200,"保存成功",common_user);
		}else {
			return new ResApi<>(400,"保存失败",null);
		}
	}

	@Override
	public ResApi<Object> getAllInfo() {
		return new ResApi<>(200, "success", commonUserDao.getInfoAll());
	}

	@Override
	public ResApi<Object> getOneById(Integer id) {
		Map<String, Object> map = new HashMap();
		map.put("item", commonUserDao.getInfoByIdBrief(id));
		return new ResApi<>(200, "success", map);
	}
}
