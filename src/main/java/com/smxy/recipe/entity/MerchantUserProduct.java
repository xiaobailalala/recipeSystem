package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Demo MerchantUserProduct
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 10:52
 */
@Getter
@Setter
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
