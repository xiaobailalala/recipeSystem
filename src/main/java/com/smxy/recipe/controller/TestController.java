/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/3 15:46
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller;

import com.smxy.recipe.utils.FilePath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("test")
    public String test(){
        FilePath.RealPathMkdir("123");
        return "success";
    }
}
