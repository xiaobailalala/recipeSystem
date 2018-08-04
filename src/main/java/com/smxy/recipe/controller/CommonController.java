package com.smxy.recipe.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smxy.recipe.utils.CodeApi;

/**
 * @author zpx
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {
	
	/**
	 * 测试method
	 * @param num
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCode",method= {RequestMethod.POST})
	public Map<String, Object> getCode(String num){
		Random random = new Random();
		String code="";
		for (int i=0;i<6;i++) {
		    code+=random.nextInt(10);
		}
		CodeApi.getRequest2(num,code);
		Map<String, Object> map=new HashMap<>();
		map.put("code", code);
		return map;
	}
	
}
