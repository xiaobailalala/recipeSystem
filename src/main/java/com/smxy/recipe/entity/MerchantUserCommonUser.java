package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Demo MerchantUserCommonUser
 *
 * @author Yangyihui
 * @date 2019/4/18 11:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUserCommonUser {
    private Integer fId;
    private Integer fUid;
    private Integer fMid;
    private CommonUser commonUser;
}
