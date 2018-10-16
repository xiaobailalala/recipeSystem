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
 * Build File @date: 2018/9/30 7:48
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.utils.ResApi;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.util.List;

public class RedisSerializeConfig {

    /**
     * @author zpx
     * @param connectionFactory
     * @return
     * @Bean
     */
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        serializer.setObjectMapper(mapper);

        template.setValueSerializer(serializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.afterPropertiesSet();
        return template;
    }
//    List
//    @Bean
//    public RedisTemplate<Object, List> listTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, List> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<List> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(List.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    @Bean
//    public RedisCacheManager listCacheManager(RedisTemplate<Object, List> listTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(listTemplate);
//        //key多了一个前缀
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
////    ResApi
//    @Bean
//    public RedisTemplate<Object, ResApi> resApiTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, ResApi> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<ResApi> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(ResApi.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    @Bean
//    public RedisCacheManager resApiCacheManager(RedisTemplate<Object, ResApi> resApiTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(resApiTemplate);
//        //key多了一个前缀
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
////    AdminRole
//    @Bean
//    public RedisTemplate<Object, AdminRole> adminRoleTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, AdminRole> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<AdminRole> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(AdminRole.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    //CacheManagerCustomizers可以来定制缓存的一些规则
//    //@Primary //将某个缓存管理器作为默认使用的
//    @Bean
//    public RedisCacheManager adminRoleCacheManager(RedisTemplate<Object, AdminRole> adminRoleTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(adminRoleTemplate);
//        //key多了一个前缀
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
//
////    AdminUser
//    @Bean
//    public RedisTemplate<Object, AdminUser> adminUserTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, AdminUser> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<AdminUser> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(AdminUser.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    @Primary
//    @Bean
//    public RedisCacheManager adminUserCacheManager(RedisTemplate<Object, AdminUser> adminUserTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(adminUserTemplate);
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
////    AdminPermission
//    @Bean
//    public RedisTemplate<Object, AdminPermission> adminPermissionTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, AdminPermission> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<AdminPermission> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(AdminPermission.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    @Bean
//    public RedisCacheManager adminPermissionCacheManager(RedisTemplate<Object, AdminPermission> adminPermissionTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(adminPermissionTemplate);
//        //key多了一个前缀
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
////    AdminRolePermission
//    @Bean
//    public RedisTemplate<Object, AdminRolePermission> adminRolePermissionTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, AdminRolePermission> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<AdminRolePermission> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(AdminRolePermission.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    @Bean
//    public RedisCacheManager adminRolePermissionCacheManager(RedisTemplate<Object, AdminRolePermission> adminRolePermissionTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(adminRolePermissionTemplate);
//        //key多了一个前缀
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
////    AdminUserRole
//    @Bean
//    public RedisTemplate<Object, AdminUserRole> adminUserRoleTemplate(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
//        RedisTemplate<Object, AdminUserRole> template=new RedisTemplate<>();
//        template.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<AdminUserRole> employeeJackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(AdminUserRole.class);
//        template.setDefaultSerializer(employeeJackson2JsonRedisSerializer);
//        return template;
//    }
//    @Bean
//    public RedisCacheManager adminUserRoleCacheManager(RedisTemplate<Object, AdminUserRole> adminUserRoleTemplate){
//        RedisCacheManager cacheManager = new RedisCacheManager(adminUserRoleTemplate);
//        //key多了一个前缀
//        //使用前缀，默认会将CacheName作为key的前缀
//        cacheManager.setUsePrefix(true);
//        return cacheManager;
//    }
}
