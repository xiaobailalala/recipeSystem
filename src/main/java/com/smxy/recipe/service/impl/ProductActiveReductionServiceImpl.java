package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.dao.ProductActiveReductionDao;
import com.smxy.recipe.entity.MerchantProduct;
import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.service.ProductActiveReductionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo ProductActiveReductionServiceImpl
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 17:07
 */
@Service("productActiveReductionService")
public class ProductActiveReductionServiceImpl implements ProductActiveReductionService {
    @Autowired
    private ProductActiveReductionDao productActiveReductionDao;

    @Autowired
    private MerchantProductDao merchantProductDao;

    @Override
    public ResApi<String> insertProductActiveReduction(ProductActiveReduction productActiveReduction, Double[] fullMoney, Double[] reduceMoney, Integer fPid, Integer fMid) {
        int result = 0;
        MerchantProduct productById = merchantProductDao.getProductById(fPid);
        ProductActiveReduction[] productActiveReductions;
        if (fullMoney.length > 0 && reduceMoney.length > 0) {
            productActiveReductions = new ProductActiveReduction[fullMoney.length];
            for (int i = 0; i < productActiveReductions.length; i++) {
                productActiveReductions[i] = new ProductActiveReduction();
                productActiveReductions[i].setFMid(fMid);
                productActiveReductions[i].setFPid(fPid);
                productActiveReductions[i].setFName(productActiveReduction.getFName());
                productActiveReductions[i].setFStartTime(productActiveReduction.getFStartTime());
                productActiveReductions[i].setFEndTime(productActiveReduction.getFEndTime());
                productActiveReductions[i].setFFullMoney(fullMoney[i]);
                productActiveReductions[i].setFReduceMoney(reduceMoney[i]);
                Integer res = productActiveReductionDao.insertProductActiveReduction(productActiveReductions[i]);
                if (res > 0) {
                    result ++;
                }
            }
        } else {
            return ResApi.getError();
        }
        if (result == productActiveReductions.length) {
            Map<String, Object> map = new HashMap<>(8);
            map.put("fId", fPid);
            map.put("fActive", "满减优惠");
            Integer res = merchantProductDao.updateProductActiveReduction(map);
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
