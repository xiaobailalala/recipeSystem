package com.smxy.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smxy.recipe.entity.Profession;
import com.smxy.recipe.service.ProfessionService;
import com.smxy.recipe.utils.ResApi;

/**
 * @author zpx
 *
 */
@Controller
@RequestMapping("/profession")
public class ProfessionController {
	
	@Autowired
	ProfessionService professionService;
	
	/**
	 * @return
	 */
	@ResponseBody
	@GetMapping("/getAllInfo")
	public ResApi<List<Profession>> getAllInfo(){
		return professionService.getAllInfo();
	}
}
