package com.smxy.recipe.controller;

import com.smxy.recipe.config.template.PathController;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zpx
 */
@PathController("/common")
public class CommonController {

    @ResponseBody
    @PostMapping("/test")
    public Map<String, Object> test(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("result", ToolsApi.multipartFileUploadFile(multipartFile, null));
        return map;
    }

}
