package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.service.MerchantProductClassifyService;
import com.smxy.recipe.utils.ResApi;
import org.apache.ibatis.annotations.Arg;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo MerchantProductClassifyController
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 11:01
 */
@PathController("/merchant/merchantProductClassify")
public class MerchantProductClassifyController {
}
