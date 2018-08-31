package com.smxy.recipe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@MapperScan("com.smxy.recipe.dao")
@EnableAsync
@EnableCaching
@SpringBootApplication
public class RecipeSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSystemApplication.class, args);
    }

}
