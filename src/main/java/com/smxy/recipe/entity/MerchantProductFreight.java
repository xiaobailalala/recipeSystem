package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Demo MerchantProductFreight
 *
 * @author Yangyihui
 * @date 2018/11/22 0022 16:55
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductFreight implements Serializable {
    private Integer fId;
    private String fFreightname;
}
