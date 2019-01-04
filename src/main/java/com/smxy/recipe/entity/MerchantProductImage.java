package com.smxy.recipe.entity;

import lombok.*;

import java.io.Serializable;

/**
 * Demo MerchantProductImage
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductImage implements Serializable {
    private Integer fId;
    private Integer fPid;
    private String fImg;

    public MerchantProductImage(Integer fPid, String fImg) {
        this.fPid = fPid;
        this.fImg = fImg;
    }
}
