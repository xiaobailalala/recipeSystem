package com.smxy.recipe;

import com.smxy.recipe.utils.ResApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class DemoApplicationTests {


    @Test
    public void contextLoads() {
        ResApi<Object> data = getData();
        System.out.println(data);
    }

    @Cacheable(value = {"testList"})
    public ResApi<Object> getData() {
        List<String> strings=new ArrayList<>();
        strings.add("qqq");
        strings.add("aaa");
        strings.add("zzz");
        ResApi<Object> resApi=new ResApi<>(200,"success","success");
        return resApi;
    }

}
