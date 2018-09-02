/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/6 18:06
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.config;

import com.smxy.recipe.filter.XFormAuthenticationFilter;
import com.smxy.recipe.realm.AdminShiroRealm;
import com.smxy.recipe.resolver.MyExceptionResolver;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//    @Value("${server.session.timeout}")
//    private String serverSessionTimeout;

    /**
     * 配置拦截器
     *
     * 定义拦截URL权限，优先级从上到下
     * 1). anon  : 匿名访问，无需登录
     * 2). authc : 登录后才能访问
     * 3). logout: 登出
     * 4). roles : 角色过滤器
     *
     * URL 匹配风格
     * 1). ?：匹配一个字符，如 /admin? 将匹配 /admin1，但不匹配 /admin 或 /admin/；
     * 2). *：匹配零个或多个字符串，如 /admin* 将匹配 /admin 或/admin123，但不匹配 /admin/1；
     * 2). **：匹配路径中的零个或多个路径，如 /admin/** 将匹配 /admin/a 或 /admin/a/b
     *
     * 配置身份验证成功，失败的跳转路径
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>();
        XFormAuthenticationFilter xFormAuthenticationFilter=new XFormAuthenticationFilter();
        xFormAuthenticationFilter.setPasswordParam("fPassword");
        xFormAuthenticationFilter.setUsernameParam("fAccount");
        shiroFilterFactoryBean.setLoginUrl("/adm/adLogin");		// 登录的路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/error/unauthorized");
        filters.put("authc",xFormAuthenticationFilter);
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put("/static/**", "anon");	// 静态资源匿名访问
//        filterChainDefinitionMap.put("/employees/login", "anon");// 登录匿名访问
//        filterChainDefinitionMap.put("/logout", "logout");	// 用户退出，只需配置logout即可实现该功能
        filterChainDefinitionMap.put("/src/**","anon");
        filterChainDefinitionMap.put("/assets/**","anon");
        filterChainDefinitionMap.put("/common/**","anon");
        filterChainDefinitionMap.put("/adm/adlogin","anon");
        filterChainDefinitionMap.put("/mob/**","anon");
        filterChainDefinitionMap.put("/druid/**","anon");
        filterChainDefinitionMap.put("/getDht11Data","anon");
        filterChainDefinitionMap.put("/**", "authc");		// 其他路径均需要身份认证，一般位于最下面，优先级最低
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
//        formAuthenticationFilter.setUsernameParam("fAccount");
//        formAuthenticationFilter.setPasswordParam("fPassword");
        return shiroFilterFactoryBean;
    }

    @Bean
    public HandlerExceptionResolver solver(){
        HandlerExceptionResolver handlerExceptionResolver=new MyExceptionResolver();
        return handlerExceptionResolver;
    }

//    @Bean
//    public FilterRegistrationBean shiroSessionFilterRegistrationBean() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new ShiroSessionFilter());
//        filterRegistrationBean.setOrder(FilterRegistrationBean.LOWEST_PRECEDENCE);
//        filterRegistrationBean.setEnabled(true);
//        filterRegistrationBean.addUrlPatterns("/*");
//        Map<String, String> initParameters = Maps.newHashMap();
//        initParameters.put("serverSessionTimeout", serverSessionTimeout);
//        initParameters.put("excludes", "/src/*,/assets/*,/adm/adLogin,/adm/adlogin");
//        filterRegistrationBean.setInitParameters(initParameters);
//        return filterRegistrationBean;
//    }

    /**
     * 配置Shiro生命周期处理器
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }
    /**
     * 自动创建代理类，若不添加，Shiro的注解可能不会生效。
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
    /**
     * 配置加密匹配，使用MD5的方式，进行1024次加密
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }
    /**
     * 自定义Realm，可以多个
     */
    @Bean
    public AdminShiroRealm myShiroRealm() {
        AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
        adminShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminShiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager=new CookieRememberMeManager();
//        KeyGenerator keygen = null;
//        try {
//            keygen = KeyGenerator.getInstance("AES");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        SecretKey deskey = keygen.generateKey();
//        System.out.println(Base64.encodeToString(deskey.getEncoded()));
        byte[] cipherKey = Base64.decode("Y1JxNSPXVwMkyvES/kJGeQ==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        cookieRememberMeManager.setCookie(simpleCookie());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie simpleCookie(){
        SimpleCookie simpleCookie=new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(86400);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }
}