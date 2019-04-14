package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Demo MerchantProductDetails
 *
 * @author Yangyihui
 * @date 2018/11/23 0023 17:08
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MerchantProductDetails implements Serializable {
    private Integer fId;
    private Integer fPid;
    private String fDetailimg;
    private String fDetailcontents;

    public MerchantProductDetails(Integer fPid, String fDetailimg, String fDetailcontents) {
        this.fPid = fPid;
        this.fDetailimg = fDetailimg;
        this.fDetailcontents = fDetailcontents;
    }
}
