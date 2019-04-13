package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantProductImage
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:07
 */
@Data
@Accessors(chain = true)
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
