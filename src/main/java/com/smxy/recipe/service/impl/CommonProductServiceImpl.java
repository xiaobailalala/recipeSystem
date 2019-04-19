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

import com.smxy.recipe.dao.CommonAttentionDao;
import com.smxy.recipe.dao.CommonProductDao;
import com.smxy.recipe.entity.CommonProduct;
import com.smxy.recipe.service.CommonProductService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commonProductService")
public class CommonProductServiceImpl implements CommonProductService {

    private final CommonProductDao commonProductDao;
    private final RabbitTemplate rabbitTemplate;
    private final CommonAttentionDao commonAttentionDao;

    @Autowired
    public CommonProductServiceImpl(CommonProductDao commonProductDao, RabbitTemplate rabbitTemplate, CommonAttentionDao commonAttentionDao) {
        this.commonProductDao = commonProductDao;
        this.rabbitTemplate = rabbitTemplate;
        this.commonAttentionDao = commonAttentionDao;
    }

    @Override
    public ResApi<String> saveInfo(CommonProduct commonProduct) {
        commonProduct.setFContent(ToolsApi.base64Encode(commonProduct.getFContent()));
        commonProductDao.saveInfo(commonProduct);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> productIndex(Integer wid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        CommonProduct commonProduct = commonProductDao.queryInfoById(wid);
        commonProduct.setFContent(ToolsApi.base64Decode(commonProduct.getFContent()));
        rabbitTemplate.convertAndSend("recipeSystem.direct", "worksCountUpload.queue", wid);
        if (commonAttentionDao.findInfoByUidAndOidAndType(uid, commonProduct.getCommonUser().getFId(), 1) != 0) {
            map.put("isAttention", true);
        } else {
            map.put("isAttention", false);
        }
        map.put("works", commonProduct);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> randomWorks() {
        List<CommonProduct> commonProducts = commonProductDao.queryAll();
        List<CommonProduct> numWorks = getNumWorks(commonProducts);
        return ResApi.getSuccess(numWorks);
    }

    static List<CommonProduct> getNumWorks(List<CommonProduct> list) {
        List<CommonProduct> commonProductList = new ArrayList<>();
        if (list.size() > 8) {
            int[] index = ToolsApi.randomArray(0, list.size()-1, 8);
            assert index != null;
            for (int in : index) {
                commonProductList.add(list.get(in));
            }
        } else {
            commonProductList.addAll(list);
        }
        commonProductList.sort((o1, o2) -> o2.getFCount().compareTo(o1.getFCount()));
        commonProductList.forEach(item -> item.setFContent(ToolsApi.base64Decode(item.getFContent())));
        return commonProductList;
    }


}
