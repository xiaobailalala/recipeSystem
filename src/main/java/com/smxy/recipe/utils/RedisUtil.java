package com.smxy.recipe.utils;

import com.smxy.recipe.entity.MerchantProductMarque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Demo RedisUtil
 *
 * @author Yangyihui
 * @date 2018/12/30 0030 23:35
 */
@Component
public class RedisUtil {

    private static RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /**
     * 功能描述: 向缓存中存入Map
     * @param key 键值
     * @param map 缓存值
     * @return : boolean
     * @author : yangyihui
     * @date : 2018/12/30 0030 23:43
     */
    public static boolean hashMapSet(String key, Map<Object, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return  true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 功能描述: 获取hashkey对应的所有值
     * @param key 键值
     * @return : java.util.Map<java.lang.Object,java.lang.Object>
     * @author : yangyihui
     * @date : 2018/12/30 0030 23:38
     */
    public static Map<Object, Object> hashMapGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 功能描述: 存入list
     * @param key 键值
     * @param value 存入值
     * @return : boolean
     * @author : yangyihui
     * @date : 2019/1/1 0001 11:41
     */
    public static boolean listSet(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, value);
            //设置60秒过期
            expire(key, 60);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 功能描述: 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束 0 到 -1代表所有值
     * @return : java.util.List<java.lang.Object>
     * @author : yangyihui
     * @date : 2019/1/1 0001 11:45
     */
    public static List<Object> listGet(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 功能描述: 设置缓存的过期时间
     * @param key 键值
     * @param seconds 秒
     * @return : void
     * @author : yangyihui
     * @date : 2019/4/15 20:27
     */
    public static void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        redisTemplate.expire(key, Long.valueOf(seconds+""), TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     */
    public static boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        List<MerchantProductMarque> merchantProductMarqueList = new ArrayList<>(8);
        MerchantProductMarque merchantProductMarque;
        for (int i = 0; i < 5; i++) {
            merchantProductMarque = new MerchantProductMarque("haha", null, 19.8, 10, 4, 3);
            merchantProductMarqueList.add(merchantProductMarque);
        }
        boolean mer1 = RedisUtil.listSet("mer", merchantProductMarqueList);
        System.out.println(mer1);
        List<Object> mer = RedisUtil.listGet("mer", 0, -1);
        for (Object o : mer) {
            System.out.println(o);
        }
    }
}
