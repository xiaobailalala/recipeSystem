/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/25 0:35
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

//@Component(value = "YmlConfig")
@ConfigurationProperties
@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class YmlConfig {
    private Map<String,String> mapProps = new HashMap<>();
}