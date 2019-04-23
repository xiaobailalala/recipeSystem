package com.smxy.recipe.dao;

import com.smxy.recipe.entity.MerchantComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Demo MerchantCommentDao
 *
 * @author Yangyihui
 * @date 2019/4/22 0022 23:28
 */
public interface MerchantCommentDao {

    Integer insertComment(@Param("fMid")Integer fMid, @Param("fPid") Integer fPid, @Param("fUid") Integer fUid, @Param("fRelease") String fRelease, @Param("fContent") String fContent, @Param("fState") Integer fState, @Param("fCover") String fCover, @Param("fReplyid")Integer fReplyid);

    List<MerchantComment> getMerchantCommentByMidAndState(@Param("fState")Integer fState, @Param("fMid")Integer fMid);

    List<MerchantComment> getAnswerMerchantCommentByMidAndState(@Param("fState")Integer fState, @Param("fMid")Integer fMid);

    List<MerchantComment> getMerchantCommentByPidAndState(@Param("fState")Integer fState, @Param("fPid")Integer fMid);

    List<MerchantComment> getAnswerMerchantCommentByPidAndState(@Param("fState")Integer fState, @Param("fPid")Integer fMid);


}
