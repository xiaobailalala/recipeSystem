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

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole implements Serializable {
    private Integer fId;
    private String fRolename;
    private String fIntroduction;
    private List<AdminRolePermission> adminRolePermissions;

}
