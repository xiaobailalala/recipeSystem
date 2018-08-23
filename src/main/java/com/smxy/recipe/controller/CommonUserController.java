package com.smxy.recipe.controller;

import com.smxy.recipe.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zpx
 *
 */
@Controller
@RequestMapping("/commonUser")
public class CommonUserController {

	@Autowired
	private CommonUserService commonUserService;
	
}
