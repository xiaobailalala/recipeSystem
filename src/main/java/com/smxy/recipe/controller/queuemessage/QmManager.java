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
 * Build File @date: 2018/10/8 21:54
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.controller.queuemessage;

import com.smxy.recipe.dao.RecipeDao;
import com.smxy.recipe.entity.Recipe;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class QmManager {

    @Autowired
    private RecipeDao recipeDao;

    @RabbitListener(queues = "recipeCountUpload.queue")
    public void updateRecipeCount(Integer id){
        Recipe recipe = recipeDao.getInfoById(id);
        recipe.setFCount(recipe.getFCount()+1);
        recipeDao.updateRecipeCount(recipe);
    }

}
