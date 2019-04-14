package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantProductClassify
 *
 * @author Yangyihui
 * @date 2018/11/19 0019 11:03
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductClassify implements Serializable {
    private Integer fId;
    private String fName;
    private String fCover;
}
