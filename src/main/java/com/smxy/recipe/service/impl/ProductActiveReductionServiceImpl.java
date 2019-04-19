package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.dao.ProductActiveReductionConditionDao;
import com.smxy.recipe.dao.ProductActiveReductionDao;
import com.smxy.recipe.entity.ProductActiveReduction;
import com.smxy.recipe.entity.ProductActiveReductionCondition;
import com.smxy.recipe.service.ProductActiveReductionService;
import com.smxy.recipe.utils.ResApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Demo ProductActiveReductionServiceImpl
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 17:07
 */
@Service("productActiveReductionService")
public class ProductActiveReductionServiceImpl implements ProductActiveReductionService {

    private final ProductActiveReductionDao productActiveReductionDao;
    private final ProductActiveReductionConditionDao productActiveReductionConditionDao;
    private final MerchantProductDao merchantProductDao;

    @Autowired
    public ProductActiveReductionServiceImpl(ProductActiveReductionDao productActiveReductionDao, ProductActiveReductionConditionDao productActiveReductionConditionDao, MerchantProductDao merchantProductDao) {
        this.productActiveReductionDao = productActiveReductionDao;
        this.productActiveReductionConditionDao = productActiveReductionConditionDao;
        this.merchantProductDao = merchantProductDao;
    }

    @Override
    public ResApi<String> insertProductActiveReduction(ProductActiveReduction productActiveReduction, Double[] fullMoney, Double[] reduceMoney, Integer fPid, Integer fMid) {
        int result = 0;
        ProductActiveReductionCondition[] productActiveReductionConditions;
        if (fullMoney.length > 0 && reduceMoney.length > 0) {
            productActiveReductionConditions = new ProductActiveReductionCondition[fullMoney.length];
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date startDate = dateFormat.parse(productActiveReduction.getFStartTime());
                if (startDate.getTime() > System.currentTimeMillis()) {
                    productActiveReduction.setFStatus("未开始");
                } else {
                    productActiveReduction.setFStatus("进行中");
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            productActiveReduction.setFPid(fPid);
            productActiveReduction.setFMid(fMid);
            int forActiveResult = productActiveReductionDao.insertProductActiveReduction(productActiveReduction);
            if (forActiveResult > 0) {
                for (int i = 0; i < productActiveReductionConditions.length; i++) {
                    productActiveReductionConditions[i] = new ProductActiveReductionCondition();
                    productActiveReductionConditions[i].setFAid(productActiveReduction.getFId());
                    productActiveReductionConditions[i].setFFullMoney(fullMoney[i]);
                    productActiveReductionConditions[i].setFReduceMoney(reduceMoney[i]);
                    Integer res = productActiveReductionConditionDao.insertProductActiveReductionCondition(productActiveReductionConditions[i]);
                    if (res > 0) {
                        result ++;
                    }
                }
            } else {
                return ResApi.getError(504, "储存活动信息失败");
            }
        } else {
            return ResApi.getError(505, "活动信息没有活动选项");
        }
        if (result == productActiveReductionConditions.length) {
            Map<String, Object> map = new HashMap<>(8);
            map.put("fId", fPid);
            map.put("fActive", "满减优惠");
            Integer res = merchantProductDao.updateProductActiveReduction(map);
            if (res > 0) {
                return ResApi.getSuccess();
            } else {
                return ResApi.getError(506,"更新商品信息失败");
            }
        } else {
            return ResApi.getError(507,"系统出错");
        }
    }

    @Override
    public Map<String, Object> getProductActiveReductionListByMid(Integer fMid, HttpServletRequest httpServletRequest) {
        int page = Integer.parseInt(httpServletRequest.getParameter("page"));
        int limit = Integer.parseInt(httpServletRequest.getParameter("limit"));
        int startIndex = (page - 1) * limit;
        int endIndex = page * limit;
        Map<String, Object> resultMap = new HashMap<>(8);
        List<ProductActiveReduction> productActiveReductions = productActiveReductionDao.selectProductActiveReductionByMid(fMid);
        if (endIndex > productActiveReductions.size()) {
            resultMap.put("data",productActiveReductions.subList(startIndex, productActiveReductions.size()));
        } else {
            resultMap.put("data", productActiveReductions.subList(startIndex, endIndex));
        }
        resultMap.put("count", productActiveReductions.size());
        resultMap.put("code", ResApi.getSuccess().getCode());
        resultMap.put("msg", ResApi.getSuccess().getMsg());
        return resultMap;
    }

    @Override
    public ResApi<Object> getProductActiveDiscountById(Integer fId) {
        return ResApi.getSuccess(productActiveReductionDao.selectProductActiveReductionById(fId));
    }

    @Override
    public ResApi<String> deleteProductActiveReductionById(Integer fId) {
        ProductActiveReduction productActiveReduction = productActiveReductionDao.selectProductActiveReductionById(fId);
        Integer result = productActiveReductionDao.deleteProductActiveReduction(fId);
        Map<String, Object> map = new HashMap<>(8);
        map.put("fId", productActiveReduction.getFPid());
        map.put("fActive", "没有活动");
        Integer result1 = merchantProductDao.updateProductActiveDiscount(map);
        if (result > 0 && result1 > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> editorProductActiveReductionById(Integer fId, ProductActiveReduction productActiveReduction, Integer[] conditionId, Double[] newFullMoney, Double[] newReduceMoney, Double[] fullMoney, Double[] reduceMoney) {
        Integer addResult = 0;
        Integer editResult = 0;
        ProductActiveReductionCondition[] newProductActiveReduction = new ProductActiveReductionCondition[newFullMoney.length];
        ProductActiveReductionCondition[] editProductActiveReduction = new ProductActiveReductionCondition[fullMoney.length];
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date startDate = dateFormat.parse(productActiveReduction.getFStartTime());
            if (startDate.getTime() > System.currentTimeMillis()) {
                productActiveReduction.setFStatus("未开始");
            } else {
                productActiveReduction.setFStatus("进行中");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        productActiveReduction.setFId(fId);
        Integer forActiveResult = productActiveReductionDao.updateProductActiveReductionById(productActiveReduction);
        if (forActiveResult > 0) {
            for (int i = 0; i < newProductActiveReduction.length; i++) {
                newProductActiveReduction[i] = new ProductActiveReductionCondition();
                newProductActiveReduction[i].setFAid(fId);
                newProductActiveReduction[i].setFFullMoney(newFullMoney[i]);
                newProductActiveReduction[i].setFReduceMoney(newReduceMoney[i]);
                int res = productActiveReductionConditionDao.insertProductActiveReductionCondition(newProductActiveReduction[i]);
                if (res > 0) {
                    addResult ++;
                }
            }

            for (int i = 0; i < editProductActiveReduction.length; i++) {
                editProductActiveReduction[i] = new ProductActiveReductionCondition();
                editProductActiveReduction[i].setFId(conditionId[i]);
                editProductActiveReduction[i].setFFullMoney(fullMoney[i]);
                editProductActiveReduction[i].setFReduceMoney(reduceMoney[i]);
                Integer res = productActiveReductionConditionDao.updateProductActiveReductionConditionById(editProductActiveReduction[i]);
                if (res > 0) {
                    editResult ++;
                }
            }
        } else {
            return ResApi.getError(504, "储存活动信息失败");
        }
        if (addResult == newFullMoney.length && editResult == fullMoney.length) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }
}

