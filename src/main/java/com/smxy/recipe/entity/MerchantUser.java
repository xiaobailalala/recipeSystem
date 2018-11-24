package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Demo MerchantUser
 *
 * @author Yangyihui
 * @date 2018/11/12 0012 17:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUser implements Serializable {
    private Integer fId;
    private String fCover;
    private String fShopname;
    private String fAccount;
    private String fPassword;
    private String fBirth;
    private String fProvince;
    private String fCity;
    private String fArea;
    private Integer fCount;
    private List<AdminUserRole> adminUserRoles;
}
