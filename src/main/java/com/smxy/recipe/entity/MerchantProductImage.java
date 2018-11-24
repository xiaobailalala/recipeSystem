package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Demo MerchantProductImage
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:07
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductImage implements Serializable {
    private Integer fId;
    private Integer fPid;
    private String fImg;
}
