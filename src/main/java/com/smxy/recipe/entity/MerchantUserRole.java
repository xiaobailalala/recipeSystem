package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantUserRole
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:00
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUserRole implements Serializable {
    private Integer fId;
    private Integer fMid;
    private MerchantUser merchantUser;
    private Integer fRid;
    private AdminRole adminRole;
}
