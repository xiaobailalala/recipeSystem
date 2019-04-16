package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductClassifyDao;
import com.smxy.recipe.service.MerchantProductClassifyService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Demo MerchantProductClassifyService
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 10:58
 */
@Transactional(rollbackFor = Exception.class)
@Service("merchantProductClassifyService")
public class MerchantProductClassifyServiceImpl  implements MerchantProductClassifyService {

    private final MerchantProductClassifyDao merchantProductClassifyDao;

    @Autowired
    public MerchantProductClassifyServiceImpl(MerchantProductClassifyDao merchantProductClassifyDao) {
        this.merchantProductClassifyDao = merchantProductClassifyDao;
    }

    @Override
    public ResApi<Object> getAllProductClassify() {
        return ResApi.getSuccess(merchantProductClassifyDao.getAllProductClassify());
    }
}
