package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Demo MerchantProduct
 *
 * @author Yangyihui
 * @date 2018/11/18 0018 09:17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProduct implements Serializable {
    private Integer fId;
    private String fName;
    private String fCover;
    private Integer fGood;
    private String fCategory;
    private String fState;
    private Integer fSales;
    private Integer fMarqueclaid;
    private String fAddtime;
    private Double fGrosssales;
    private Integer fFreightid;
    private String fReview;
    private String fDiscount;
    private String fReduction;
    private List<MerchantProductMarque> merchantProductMarques;
    private List<MerchantProductDetails> merchantProductDetails;

}
