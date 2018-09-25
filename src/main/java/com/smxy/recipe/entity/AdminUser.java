/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/8/5 22:29
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
public class AdminUser implements Serializable {
    private Integer fId;
    private String fAccount;
    private String fUsername;
    private String fPassword;
    private String fHead;
    private String fEmail;
    private Integer fSex;
    private List<AdminUserRole> adminUserRoles;
}
