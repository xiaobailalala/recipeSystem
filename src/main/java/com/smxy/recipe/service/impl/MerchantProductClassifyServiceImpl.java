package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductClassifyDao;
import com.smxy.recipe.service.MerchantProductClassifyService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo MerchantProductClassifyService
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 10:58
 */
@Service("merchantProductClassifyService")
public class MerchantProductClassifyServiceImpl  implements MerchantProductClassifyService {
    @Autowired
    private MerchantProductClassifyDao merchantProductClassifyDao;

    @Override
    public ResApi<Object> getAllProductClassify() {
        return ResApi.getSuccess(merchantProductClassifyDao.getAllProductClassify());
    }
}
