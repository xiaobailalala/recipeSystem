package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Demo MerchantOrder
 *
 * @author Yangyihui
 * @date 2019/4/6 0006 17:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantOrder {
    private Integer fId;
    private String fOrderNumber;
    private String fUserName;
    private Integer fProductId;
    private String fUserAccount;
    private String fAddress;
    private Integer fState;
    private Integer fType;
    private Integer fMerUserId;
    private MerchantProduct merchantProduct;
}
