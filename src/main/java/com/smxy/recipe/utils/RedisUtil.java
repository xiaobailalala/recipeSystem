package com.smxy.recipe.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Demo RedisUtil
 *
 * @author Yangyihui
 * @date 2018/12/30 0030 23:35
 */
@Component
public class RedisUtil {

    private static RedisTemplate redisTemplate;

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
}
