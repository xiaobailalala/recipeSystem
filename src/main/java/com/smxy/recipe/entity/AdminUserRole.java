/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/7 12:16
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminUserRole implements Serializable {
    private Integer fId;
    private Integer fUid;
    private AdminUser adminUser;
    private Integer fRid;
    private AdminRole adminRole;
}
