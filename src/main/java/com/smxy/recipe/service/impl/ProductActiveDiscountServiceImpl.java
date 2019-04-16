package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.MerchantProductDao;
import com.smxy.recipe.dao.ProductActiveDiscountDao;
import com.smxy.recipe.entity.ProductActiveDiscount;
import com.smxy.recipe.service.ProductActiveDiscountService;
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
 * Demo ProductActiveDiscountServiceImpl
 *
 * @author Yangyihui
 * @date 2018/12/18 0018 16:12
 */
@Service("productActiveDiscountService")
public class ProductActiveDiscountServiceImpl implements ProductActiveDiscountService {

    private final ProductActiveDiscountDao productActiveDiscountDao;
    private final MerchantProductDao merchantProductDao;

    @Autowired
    public ProductActiveDiscountServiceImpl(ProductActiveDiscountDao productActiveDiscountDao, MerchantProductDao merchantProductDao) {
        this.productActiveDiscountDao = productActiveDiscountDao;
        this.merchantProductDao = merchantProductDao;
    }

    @Override
    public ResApi<String> insertProductActiveDiscount(ProductActiveDiscount productActiveDiscount, Integer fPid, Integer fMid) {
        productActiveDiscount.setFPid(fPid);
        productActiveDiscount.setFMid(fMid);
        if (productActiveDiscount.getFNumber() == null) {
            productActiveDiscount.setFNumber(9999);
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date startDate = dateFormat.parse(productActiveDiscount.getFStartTime());
            if (startDate.getTime() > System.currentTimeMillis()) {
                productActiveDiscount.setFStatus("未开始");
            } else {
                productActiveDiscount.setFStatus("进行中");
            }
        } catch (ParseException e) {
            e.printStackTrace();
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

    @Override
    public Map<String, Object> getProductActiveDiscountListByMid(Integer fMid, HttpServletRequest httpServletRequest) {
        int page = Integer.parseInt(httpServletRequest.getParameter("page"));
        int limit = Integer.parseInt(httpServletRequest.getParameter("limit"));
        int startIndex = (page - 1) * limit;
        int endIndex = page * limit;
        Map<String, Object> forSqlMap = new HashMap<>(8);
        Map<String, Object> resultMap = new HashMap<>(8);
        forSqlMap.put("fMid", fMid);
        List<ProductActiveDiscount> productActiveDiscountList = productActiveDiscountDao.selectAllDiscountProductByMid(forSqlMap);
        if (endIndex > productActiveDiscountList.size()) {
            resultMap.put("data", productActiveDiscountList.subList(startIndex, productActiveDiscountList.size()));
        } else {
            resultMap.put("data", productActiveDiscountList.subList(startIndex, endIndex));
        }
        resultMap.put("count", productActiveDiscountList.size());
        resultMap.put("code", ResApi.getSuccess().getCode());
        resultMap.put("msg", ResApi.getSuccess().getMsg());
        return resultMap;
    }

    @Override
    public ResApi<String> editorProductActiveDiscountById(ProductActiveDiscount productActiveDiscount, Integer fId) {
        productActiveDiscount.setFId(fId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date startDate = dateFormat.parse(productActiveDiscount.getFStartTime());
            if (startDate.getTime() > System.currentTimeMillis()) {
                productActiveDiscount.setFStatus("未开始");
            } else {
                productActiveDiscount.setFStatus("进行中");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer result = productActiveDiscountDao.updateProductActiveDiscountById(productActiveDiscount);
        if (result > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> getProductActiveDiscountById(Integer fId) {
        return ResApi.getSuccess(productActiveDiscountDao.selectProductActiveDiscountById(fId));
    }

    @Override
    public ResApi<String> deleteProductActiveDiscountById(Integer fId) {
        ProductActiveDiscount productActiveDiscount = productActiveDiscountDao.selectProductActiveDiscountById(fId);
        Integer result = productActiveDiscountDao.deleteProductActiveDiscountById(fId);
        Map<String, Object> map = new HashMap<>(8);
        map.put("fId", productActiveDiscount.getFPid());
        map.put("fActive", "没有活动");
        Integer result1 = merchantProductDao.updateProductActiveDiscount(map);
        if (result > 0 && result1 > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }
}
