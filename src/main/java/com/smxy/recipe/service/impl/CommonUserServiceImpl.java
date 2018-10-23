/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.serviceImpl 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:33:14 
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.utils.api.CodeApi;
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
	public ResApi<CommonUser> commonUserLogin(CommonUser commonUser) {
		// TODO Auto-generated method stub
		ResApi<CommonUser> resApi;
		if (commonUserDao.isUser(commonUser)>0) {
			commonUser.setFPassword(ToolsApi.entryptBySaltMd5(commonUser.getFPassword(), commonUser.getFAccount()));
			commonUser=commonUserDao.isLogin(commonUser);
			if (commonUser!=null) {
				resApi=new ResApi<>(200,"登录成功",commonUser);
			}else {
				resApi=new ResApi<>(401,"密码不正确",null);
			}
		}else {
			resApi=new ResApi<>(400,"用户不存在，请先注册",null);
		}
		return resApi;
	}

	@Override
	public ResApi<String> commonUserReg(CommonUser commonUser) {
		// TODO Auto-generated method stub
		ResApi<String> resApi;
		if (commonUserDao.isUser(commonUser)>0) {
			resApi=new ResApi<>(402,"该用户已存在。",null);
		}else {
			commonUser.setFPassword(ToolsApi.entryptBySaltMd5(commonUser.getFPassword(), commonUser.getFAccount()));
			if (commonUserDao.saveUser(commonUser)>0) {
				commonUser.setFUsername("膳客"+commonUser.getFId());
				if (commonUserDao.updateUserInfo(commonUser)>0) {
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
			String name = ToolsApi.multipartFileUploadFile(file, null);
			return new ResApi<>(200,"success",name);
		}else {
			return new ResApi<>(400,"头像上传失败",null);
		}
	}

	@Override
	public ResApi<CommonUser> commonUserSaveInfo(CommonUser commonUser) {
		// TODO Auto-generated method stub
		int res=commonUserDao.updateUserInfo(commonUser);
		if (res>0) {
			return new ResApi<>(200,"保存成功",commonUser);
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
		Map<String, Object> map = new HashMap(8);
		map.put("item", commonUserDao.getInfoByIdBrief(id));
		return new ResApi<>(200, "success", map);
	}

	@Override
	public ResApi<Object> updateCommonUserPwd(CommonUser commonUser) {
		CodeApi.getRequest(CodeApi.RESET_PWD, commonUser.getFAccount(), null);
		String newPwd = ToolsApi.entryptBySaltMd5("UcJAxskwvJxTNery", commonUser.getFAccount());
		commonUserDao.updatePwdByAccount(commonUser);
		return new ResApi<>(200, "success", "success");
	}
}
