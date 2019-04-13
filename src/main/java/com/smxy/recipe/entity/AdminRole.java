/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:35
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole implements Serializable {
    private Integer fId;
    private String fRolename;
    private String fIntroduction;
    private List<AdminRolePermission> adminRolePermissions;

}
