package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;


/**
 * Demo ProductActiveDiscount
 *
 * @author Yangyihui
 * @date 2018/12/17 0017 22:57
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductActiveDiscount implements Serializable {
    private Integer fId;
    private Integer fPid;
    private Integer fMid;
    private String fName;
    private String fStartTime;
    private String fEndTime;
    private Double fDiscount;
    private Integer fNumber;
    private String fStatus;
    private MerchantProduct merchantProduct;
}
