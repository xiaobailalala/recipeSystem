package com.smxy.recipe.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Demo MerchantComment
 *
 * @author Yangyihui
 * @date 2019/4/22 0022 23:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MerchantComment {
    private Integer fId;
    private Integer fPid;
    private Integer fUid;
    private String fRelease;
    private String fContent;
    private Integer fState;
    private String fCover;
    private Integer fReplyid;
    private MerchantProduct merchantProduct;
    private CommonUser commonUser;

    public MerchantComment(Integer fPid, Integer fUid, String fRelease, String fContent, Integer fState, String fCover, Integer fReplyid) {
        this.fPid = fPid;
        this.fUid = fUid;
        this.fRelease = fRelease;
        this.fContent = fContent;
        this.fState = fState;
        this.fCover = fCover;
        this.fReplyid = fReplyid;
    }
}
