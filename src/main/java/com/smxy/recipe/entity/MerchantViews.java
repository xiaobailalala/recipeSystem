package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Demo MerchantViews
 *
 * @author Yangyihui
 * @date 2019/4/21 0021 17:39
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantViews {
    private Integer fId;
    private Integer fCount;
    private String fTime;
    private Integer fMid;

    public MerchantViews(Integer fCount, String fTime, Integer fMid) {
        this.fCount = fCount;
        this.fTime = fTime;
        this.fMid = fMid;
    }
}
