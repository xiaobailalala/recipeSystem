package com.smxy.recipe;

import com.smxy.recipe.utils.ResApi;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
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

    @Test
    public void testUpload() throws Exception{
        ClientGlobal.init("G:\\web\\A-items\\graduation project\\RecipeSystem\\src\\main\\resources\\tracker_server.conf");
        TrackerClient trackerClient=new TrackerClient();
        TrackerServer trackerServer=trackerClient.getConnection();
        StorageServer server=null;
        StorageClient storageClient=new StorageClient(trackerServer, server);
        String[] strings=storageClient.upload_file("C:\\Users\\Shinelon\\Desktop\\1.rar","rar",null);
        for (String string:strings){
            System.out.println(string);
        }
    }

}
