package com.smxy.recipe.entity;

import java.io.Serializable;

/**
 * Demo MerchantUserRole
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:00
 */
public class MerchantUserRole implements Serializable {
    private Integer fId;
    private Integer fMid;
    private MerchantUser merchantUser;
    private Integer fRid;
    private AdminRole adminRole;
}
