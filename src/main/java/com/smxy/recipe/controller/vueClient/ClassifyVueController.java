/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/30 10:58
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.vueClient;

import com.smxy.recipe.config.template.PathRestController;
import com.smxy.recipe.service.ClassifyOneService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@PathRestController("/vue/cla")
public class ClassifyVueController {

    private final ClassifyOneService classifyOneService;

    @Autowired
    public ClassifyVueController(ClassifyOneService classifyOneService) {
        this.classifyOneService = classifyOneService;
    }

    @GetMapping("/getAllInfo")
    public ResApi<Object> getAllInfo(){
        return classifyOneService.getAllInfo();
    }

}
