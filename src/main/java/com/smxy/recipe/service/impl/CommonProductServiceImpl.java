/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2019/4/13 20:02
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.entity.CommonProduct;
import com.smxy.recipe.service.CommonProductService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.stereotype.Service;

@Service("commonProductService")
public class CommonProductServiceImpl implements CommonProductService {

    @Override
    public ResApi<String> saveInfo(CommonProduct commonProduct) {
        commonProduct.setFContent(ToolsApi.base64Encode(commonProduct.getFContent()));

        return ResApi.getSuccess();
    }

}
