/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * <p>
 * code is far away from bug with the animal protecting
 * <p>
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/11/14 15:05
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.entity;

import com.smxy.recipe.entity.tools.ArticleRefer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer fId;
    private String fName;
    private String fContent;
    private Integer fUid;
    private CommonUser commonUser;
    private String fCover;
    private Integer fGood;
    private Integer fCount;
    private Integer fCollect;
    private String fType;
    private String fRelease;
    private Integer fTopic;
    private List<ArticleComment> articleComments;
    private Integer commentCount;
    private String fReferPeople;
    private List<ArticleRefer> referPeople;
    private String fReferArticle;
    private List<ArticleRefer> referArticle;
    private String fReferRecipe;
    private List<ArticleRefer> referRecipe;
}
