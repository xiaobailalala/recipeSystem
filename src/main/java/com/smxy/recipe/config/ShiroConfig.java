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

import com.smxy.recipe.filter.XformAuthenticationFilter;
import com.smxy.recipe.realm.AdminShiroRealm;
import com.smxy.recipe.realm.MerchantShiroRealm;
import com.smxy.recipe.realm.UserModularRealmAuthenticator;
import com.smxy.recipe.resolver.MyExceptionResolver;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shirFilter(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = new LinkedHashMap<>(8);
        XformAuthenticationFilter xFormAuthenticationFilterAdmin = new XformAuthenticationFilter();
        xFormAuthenticationFilterAdmin.setUsernameParam("fAccount");
        xFormAuthenticationFilterAdmin.setPasswordParam("fPassword");
        xFormAuthenticationFilterAdmin.setLoginUrl("/manage/adm/adLogin");
        filters.put("authc", xFormAuthenticationFilterAdmin);
        XformAuthenticationFilter xFormAuthenticationFilterMerchant = new XformAuthenticationFilter();
        xFormAuthenticationFilterMerchant.setUsernameParam("fAccount");
        xFormAuthenticationFilterMerchant.setPasswordParam("fPassword");
        xFormAuthenticationFilterMerchant.setLoginUrl("/merchant/merchantUser/login");
        filters.put("merchant", xFormAuthenticationFilterMerchant);
        shiroFilterFactoryBean.setFilters(filters);
        shiroFilterFactoryBean.setUnauthorizedUrl("/common/error/unauthorized");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/src/**", "anon");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/common/**", "anon");
        filterChainDefinitionMap.put("/manage/adm/adlogin", "anon");
        filterChainDefinitionMap.put("/mob/**", "anon");
        filterChainDefinitionMap.put("/druid/**", "anon");
        filterChainDefinitionMap.put("/sensorData/**", "anon");
        filterChainDefinitionMap.put("/endpoint-websocket-wxClient", "anon");
        filterChainDefinitionMap.put("/merchant/**", "merchant");
        filterChainDefinitionMap.put("/manage/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HandlerExceptionResolver solver() {
        HandlerExceptionResolver handlerExceptionResolver = new MyExceptionResolver();
        return handlerExceptionResolver;
    }

    /**
     * 配置Shiro生命周期处理器
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 自动创建代理类，若不添加，Shiro的注解可能不会生效。
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
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
    public AdminShiroRealm adminShiroRealm() {
        AdminShiroRealm adminShiroRealm = new AdminShiroRealm();
        adminShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return adminShiroRealm;
    }

    @Bean
    public MerchantShiroRealm merchantShiroRealm() {
        MerchantShiroRealm merchantShiroRealm = new MerchantShiroRealm();
        merchantShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return merchantShiroRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        Collection<Realm> realms = new ArrayList<>();
        realms.add(adminShiroRealm());
        realms.add(merchantShiroRealm());
//        securityManager.setRealm(adminShiroRealm());
        securityManager.setRealms(realms);
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator() {
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean
    public CookieRememberMeManager cookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        byte[] cipherKey = Base64.decode("Y1JxNSPXVwMkyvES/kJGeQ==");
        cookieRememberMeManager.setCipherKey(cipherKey);
        cookieRememberMeManager.setCookie(simpleCookie());
        return cookieRememberMeManager;
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(86400);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }
}