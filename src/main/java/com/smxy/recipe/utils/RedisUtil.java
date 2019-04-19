package com.smxy.recipe.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 功能描述: 向缓存中存入Map
     * @param key 键值
     * @param map 缓存值
     * @return : boolean
     * @author : yangyihui
     * @date : 2018/12/30 0030 23:43
     */
    public boolean hashMapSet(String key, Map<?, ?> map) {
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
    public  Map<?, ?> hashMapGet(String key) {
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
    public boolean listSet(String key, Object value) {
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
    public  List<Object> listGet(String key, long start, long end) {
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
    public  void expire(String key, int seconds) {
        if (seconds <= 0) {
            return;
        }
        redisTemplate.expire(key, Long.valueOf(seconds+""), TimeUnit.SECONDS);
    }

    /**
     * 删除缓存
     */
    public  boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
