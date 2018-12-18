package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.dao.ProductActiveDiscountDao;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.service.ProductActiveDiscountService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo ProductActiveDiscountServiceImpl
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 16:12
 */
@Service("productActiveDiscountService")
public class ProductActiveDiscountServiceImpl implements ProductActiveDiscountService {

    @Autowired
    private ProductActiveDiscountDao productActiveDiscountDao;
    @Autowired
    private MerchantProductDao merchantProductDao;

    @Override
    public ResApi<String> insertProductActiveDiscount(ProductActiveDiscount productActiveDiscount, Integer fPid, Integer fMid) {
        productActiveDiscount.setFPid(fPid);
        productActiveDiscount.setFMid(fMid);
        if (productActiveDiscount.getFNumber() == null) {
            productActiveDiscount.setFNumber(9999);
        }
        Integer result = productActiveDiscountDao.insertProductActiveDiscount(productActiveDiscount);
        if (result > 0) {
            Map<String, Object> map = new HashMap<>(8);
            map.put("fId", fPid);
            map.put("fActive", "限时折扣");
            Integer res = merchantProductDao.updateProductActiveDiscount(map);
            if (res > 0) {
                return ResApi.getSuccess();
            } else {
                return ResApi.getError();
            }
        } else {
            return ResApi.getError();
        }
    }
}
