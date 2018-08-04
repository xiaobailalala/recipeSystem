package com.smxy.recipe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.smxy.recipe.entity.Common_user;
import com.smxy.recipe.service.Common_userService;
import com.smxy.recipe.utils.ResApi;

/**
 * @author zpx
 *
 */
@Controller
@RequestMapping("/common_user")
public class Common_userController {
	
	@Autowired
	Common_userService common_userService;
	
	/**
	 * @param common_user 用户实体类
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commonUserLogin",method= {RequestMethod.POST})
	public ResApi<Common_user> commonUserLogin(Common_user common_user,HttpServletRequest request){
		return common_userService.commonUserLogin(common_user);
	}
	
	/**
	 * @param common_user 用户实体类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commonUserReg",method= {RequestMethod.POST})
	public ResApi<String> commonUserReg(Common_user common_user){
		return common_userService.commonUserReg(common_user);
	}
	
	/**
	 * @param multipartFile 文件流
	 * @param img 是否有图片
	 * @param preImg 上一个图片的路径
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commonUsersaveHead",method= {RequestMethod.POST})
	public ResApi<String> commonUsersaveHead(@RequestParam("file")MultipartFile multipartFile,int img,String preImg){
		return common_userService.commonUsersaveHead(multipartFile,img,preImg);
	}
	
	/**
	 * @param common_user 用户实体类
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/commonUserSaveInfo",method= {RequestMethod.POST})
	public ResApi<Common_user> commonUserSaveInfo(Common_user common_user){
		return common_userService.commonUserSaveInfo(common_user);
	}
	
}
