package com.smxy.recipe;

import com.smxy.recipe.config.ThymeleafConfig;
import com.smxy.recipe.utils.YmlConfigUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.smxy.recipe.dao")
@EnableAsync
@EnableRabbit
@EnableCaching
@SpringBootApplication
//@Import({YmlConfigUtil.class})
public class RecipeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSystemApplication.class, args);
    }

}
