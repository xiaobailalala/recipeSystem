package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantProductMarque
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:11
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductMarque implements Serializable {
    private Integer fId;
    private String fMarquename;
    private String fMarqueimage;
    private Double fPrice;
    private Integer fRepository;
    private Integer fMarqueclaid;
    private Integer fPid;

    public MerchantProductMarque(String fMarquename, String fMarqueimage, Double fPrice, Integer fRepository, Integer fMarqueclaid, Integer fPid) {
        this.fMarquename = fMarquename;
        this.fMarqueimage = fMarqueimage;
        this.fPrice = fPrice;
        this.fRepository = fRepository;
        this.fMarqueclaid = fMarqueclaid;
        this.fPid = fPid;
    }


}
