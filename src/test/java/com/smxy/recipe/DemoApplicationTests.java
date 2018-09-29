package com.smxy.recipe;

import com.smxy.recipe.config.ThymeleafConfig;
import com.smxy.recipe.entity.ToolsEntity.RecipeClassifyList;
import com.smxy.recipe.service.MaterialService;
import com.smxy.recipe.utils.FastDFSClient;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import com.sun.org.apache.xpath.internal.SourceTree;
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

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableCaching
public class DemoApplicationTests {

    @Autowired
    MaterialService materialService;

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

    @Test
    public void readData(){
            // read file content from file
        try {
            File f = new File("C:\\Users\\Shinelon\\Desktop\\data.txt");
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(f), "gbk");
                BufferedReader reader = new BufferedReader(read);
                String line;
                String res="";
                while ((line = reader.readLine()) != null) {
                    res += line;
                }
                read.close();
                String[] strArr = res.split(" ");
                for (String item:strArr){
                    materialService.saveInfo(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void jdk8Test(){
        Arrays.asList(1,2,3).forEach((item)->{
            System.out.println(item);
        });
    }

    @Test
    public void deleteFile(){
        String[] str = {"wKgBbFuiDaGAa9aMAAAwAFwjfLU604.jpg",
                "wKgBbFuiDaGAaoOFAAAgADa6N-A129.jpg","wKgBbFuiDaGAb2R-AAA8AMMBC-Y702.jpg",
                "wKgBbFuiDaGAbN7LAAA8AF6zobQ097.jpg","wKgBbFuiDaGABrKRAABIADxB_ZM514.jpg",
                "wKgBbFuiDaGAedDPAAAkAHc9gHw810.jpg","wKgBbFuiDaGAEjv8AABEAMzySTk745.jpg",
        "wKgBbFuiDaGAEO7KAAA0ALHHnHE231.jpg","wKgBbFuiDaGAI2P3AAAsAAJUxrs317.jpg","wKgBbFuiDaGAIL5bAABIAL2d7kA582.jpg",
        "wKgBbFuiDaGAIwYsAAAoAMTWsv8924.jpg","wKgBbFuiDaGAJ8_oAABAAK4Vr50100.jpg","wKgBbFuiDaGAOSwTAAAwAEHXg5Y076.jpg",
        "wKgBbFuiDaGAUiLIAAAsABnXwv0520.jpg","wKgBbFuiDaGAUN7kAABEAFRseXI908.jpg","wKgBbFuiCY-AOnMTAAGQAFt_MSQ022.jpg",
        "wKgBbFuiCZGABKjTAAA8AMMBC-Y474.jpg","wKgBbFuiCZGAcTO9AAAkAHc9gHw169.jpg","wKgBbFuiCZGAcUV6AAA0ALHHnHE394.jpg",
        "wKgBbFuiCZGACWx7AAAoAMTWsv8841.jpg","wKgBbFuiCZGAeuXfAABEAMzySTk868.jpg","wKgBbFuiCZGAfqyNAAA8AF6zobQ703.jpg",
        "wKgBbFuiCZGALHEEAAAgADa6N-A561.jpg","wKgBbFuiCZGANgVxAABIADxB_ZM284.jpg","wKgBbFuiCZGANISVAAAsAAJUxrs945.jpg",
        "wKgBbFuiCZGAP04HAABEAFRseXI789.jpg","wKgBbFuiCZGAQ-oiAABAAK4Vr50752.jpg","wKgBbFuiCZGARke-AAAwAFwjfLU998.jpg",
        "wKgBbFuiCZGAUqnaAAAwAEHXg5Y454.jpg","wKgBbFuiCZGAYjepAAAsABnXwv0110.jpg","wKgBbFuiCZKAB4ZRAABQAGSLdJc305.jpg",
        "wKgBbFuiCZKAb7A6AABMAOzsyF8138.jpg","wKgBbFuiCZKAc86iAABIAL2d7kA246.jpg","wKgBbFuiCZKACXenAABMAHiJMOA271.jpg",
        "wKgBbFuiCZKAd5_oAABMAOzsyF8474.jpg","wKgBbFuiCZKAeIvLAABMAPT2Y_Q250.jpg","wKgBbFuiCZKAI_14AABQANu6R2A127.jpg",
        "wKgBbFuiCZKAK_O7AAAwADoJQqM048.jpg","wKgBbFuiCZKAMBq6AABIAJaV0WQ420.jpg","wKgBbFuiCZKAMc5FAABAACuMNt4108.jpg",
        "wKgBbFuiCZKAQS6sAABQAFN0wVg858.jpg","wKgBbFuiCZKAS6NzAABIAOf6Wrg547.jpg","wKgBbFuiCZKATLR5AABMAEFLOl8613.jpg",
        "wKgBbFuiCZKAU21TAAA8ALBT7V8769.jpg","wKgBbFuiCZKAZpJXAAAwAI8YTOM568.jpg"};
        try {
            for (int i = 0; i < str.length; i++){
                FastDFSClient.delete_file("group1/M00/00/00/"+str[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testToolsAPI(){
        String s = Integer.toHexString(100);
        System.out.println(s);
    }

}
