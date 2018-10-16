/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/3 12:24
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity.sensor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dht11Data {
    private String rh;
    private String tmp;
}
