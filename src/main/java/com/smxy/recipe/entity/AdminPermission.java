/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:31
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminPermission implements Serializable {
    private Integer fId;
    private String fPermissionname;
    private String fIntroduction;
}
