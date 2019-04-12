package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Demo MerchantUserProduct
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 10:52
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantUserProduct implements Serializable {
    private Integer fId;
    private Integer fMid;
    private MerchantUser merchantUser;
    private Integer fPid;
    private MerchantProduct merchantProduct;

    public MerchantUserProduct(Integer fMid, Integer fPid) {
        this.fMid = fMid;
        this.fPid = fPid;
    }
}
