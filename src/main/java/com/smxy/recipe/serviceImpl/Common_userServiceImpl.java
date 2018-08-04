/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.serviceImpl 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:33:14 
 */
package com.smxy.recipe.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smxy.recipe.dao.Common_userDao;
import com.smxy.recipe.entity.Common_user;
import com.smxy.recipe.service.Common_userService;
import com.smxy.recipe.utils.FilePath;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;

/**
 * @author zpx
 *
 */
@Service("common_userService")
public class Common_userServiceImpl implements Common_userService {
	
	@Autowired
	Common_userDao common_userDao;

	@Override
	public ResApi<Common_user> commonUserLogin(Common_user common_user) {
		// TODO Auto-generated method stub
		ResApi<Common_user> resApi=new ResApi<>(500,"系统出现未知错误",null);
		if (common_userDao.isUser(common_user)>0) {
			common_user.setF_password(ToolsApi.toMD5(common_user.getF_password()));
			common_user=common_userDao.isLogin(common_user);
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
	public ResApi<String> commonUserReg(Common_user common_user) {
		// TODO Auto-generated method stub
		ResApi<String> resApi=new ResApi<>(500,"系统出现未知错误",null);
		if (common_userDao.isUser(common_user)>0) {
			resApi=new ResApi<>(402,"该用户已存在。",null);
		}else {
			common_user.setF_password(ToolsApi.toMD5(common_user.getF_password()));
			if (common_userDao.saveUser(common_user)>0) {
				common_user.setF_username("膳客"+common_user.getF_id());
				if (common_userDao.updateUserInfo(common_user)>0) {
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
		String name=ToolsApi.reName(file.getOriginalFilename());
		boolean uploadRes=ToolsApi.FileUpload(file, ToolsApi.imgLimit(ToolsApi.suffixName(file.getOriginalFilename())), FilePath.RealPathMkdir(FilePath.commonUserHeadPath),name,img,preImg);
		if (uploadRes) {
			return new ResApi<>(200,"success",FilePath.commonUserHeadPath+name);
		}else {
			return new ResApi<>(400,"头像上传失败",null);
		}
	}

	@Override
	public ResApi<Common_user> commonUserSaveInfo(Common_user common_user) {
		// TODO Auto-generated method stub
		int res=common_userDao.updateUserInfo(common_user);
		if (res>0) {
			return new ResApi<>(200,"保存成功",common_user);
		}else {
			return new ResApi<>(400,"保存失败",null);
		}
	}
}
