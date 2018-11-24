package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductMarqueClassifyDao;
import com.smxy.recipe.service.ProductMarqueClassifyService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo ProductMarqueClassifyServiceImpl
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 11:35
 */
@Service("productMarqueClassifyService")
public class ProductMarqueClassifyServiceImpl implements ProductMarqueClassifyService {
    @Autowired
    private MerchantProductMarqueClassifyDao merchantProductMarqueClassifyDao;

    @Override
    public ResApi<Object> getAllMarqueClassify() {
        return ResApi.getSuccess(merchantProductMarqueClassifyDao.getAllMarqueClassify());
    }
}
