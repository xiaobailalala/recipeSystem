package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Demo ProductActiveReduction
 *
 * @author Yangyihui
 * @date 2018/12/17 0017 23:01
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductActiveReduction implements Serializable {
    private Integer fId;
    private Integer fPid;
    private Integer fMid;
    private String fName;
    private String fStartTime;
    private String fEndTime;
    private Double fFullMoney;
    private Double fReduceMoney;
    private MerchantProduct merchantProduct;

}
