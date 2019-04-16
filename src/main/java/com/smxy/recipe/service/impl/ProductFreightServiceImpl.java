package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductFreightDao;
import com.smxy.recipe.service.ProductFreightService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Demo ProductFreightServiceImpl
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 17:04
 */
@Transactional(rollbackFor = Exception.class)
@Service("productFreightService")
public class ProductFreightServiceImpl implements ProductFreightService {

    private final MerchantProductFreightDao merchantProductFreightDao;

    @Autowired
    public ProductFreightServiceImpl(MerchantProductFreightDao merchantProductFreightDao) {
        this.merchantProductFreightDao = merchantProductFreightDao;
    }

    @Override
    public ResApi<Object> getAllProductFreight() {
        return ResApi.getSuccess(merchantProductFreightDao.getAllMerchantProductFreight());
    }
}
