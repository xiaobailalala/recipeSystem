package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Demo MerchantProduct
 *
 * @author Yangyihui
 * @date 2018/11/18 0018 09:17
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
    private List<MerchantProductMarque> merchantProductMarques;
    private List<MerchantProductDetails> merchantProductDetails;

}
