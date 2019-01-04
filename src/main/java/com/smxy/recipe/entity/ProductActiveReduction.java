package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * Demo ProductActiveReduction
 *
 * @author Yangyihui
 * @date 2018/12/17 0017 23:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductActiveReduction implements Serializable {
    private Integer fId;
    private Integer fPid;
    private Integer fMid;
    private String fName;
    private String fStartTime;
    private String fEndTime;
    private String fStatus;
    private MerchantProduct merchantProduct;
    private List<ProductActiveReductionCondition> productActiveReductionConditions;
}
