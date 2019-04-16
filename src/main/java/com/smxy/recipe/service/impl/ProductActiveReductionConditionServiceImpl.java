package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.ProductActiveReductionConditionDao;
import com.smxy.recipe.service.ProductActiveReductionConditionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Demo ProductActiveReductionConditionServiceImpl
 *
 * @author Yangyihui
 * @date 2018/12/21 0021 16:49
 */
@Service("productActiveReductionConditionService")
public class ProductActiveReductionConditionServiceImpl implements ProductActiveReductionConditionService {

    private final ProductActiveReductionConditionDao productActiveReductionConditionDao;

    @Autowired
    public ProductActiveReductionConditionServiceImpl(ProductActiveReductionConditionDao productActiveReductionConditionDao) {
        this.productActiveReductionConditionDao = productActiveReductionConditionDao;
    }

    @Override
    public ResApi<String> deleteProductActiveReductionConditionById(Integer fId) {
        Integer result = productActiveReductionConditionDao.deleteProductActiveReductionConditionById(fId);
        if (result > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }
}
