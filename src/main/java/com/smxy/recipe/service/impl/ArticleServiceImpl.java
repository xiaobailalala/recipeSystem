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
 * Build File @date: 2018/11/14 16:14
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.Article;
import com.smxy.recipe.entity.Collect;
import com.smxy.recipe.entity.Recipe;
import com.smxy.recipe.entity.tools.ArticleRefer;
import com.smxy.recipe.service.ArticleService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private final ArticleDao articleDao;
    private final RabbitTemplate rabbitTemplate;
    private final ArticleGreatDao articleGreatDao;
    private final CollectDao collectDao;
    private final CommonAttentionDao commonAttentionDao;
    private final CommonUserDao commonUserDao;
    private final RecipeDao recipeDao;

    @Autowired
    public ArticleServiceImpl(ArticleDao articleDao, RabbitTemplate rabbitTemplate, ArticleGreatDao articleGreatDao, CollectDao collectDao, CommonAttentionDao commonAttentionDao,
                              CommonUserDao commonUserDao,RecipeDao recipeDao) {
        this.articleDao = articleDao;
        this.rabbitTemplate = rabbitTemplate;
        this.articleGreatDao = articleGreatDao;
        this.collectDao = collectDao;
        this.commonAttentionDao = commonAttentionDao;
        this.commonUserDao = commonUserDao;
        this.recipeDao = recipeDao;
    }

    @Override
    public ResApi<String> saveInfo(Article article, Integer[] peopleArr, Integer[] articleArr, Integer[] recipeArr) {
        article.setFContent(ToolsApi.base64Encode(article.getFContent()));
        article.setFName(ToolsApi.base64Encode(article.getFName()));
        AtomicReference<String> referPeople = new AtomicReference<>("");
        AtomicReference<String> referArticle = new AtomicReference<>("");
        AtomicReference<String> referRecipe = new AtomicReference<>("");
        Arrays.asList(peopleArr).forEach(item -> referPeople.updateAndGet(v -> v + item + "-"));
        Arrays.asList(articleArr).forEach(item -> referArticle.updateAndGet(v -> v + item + "-"));
        Arrays.asList(recipeArr).forEach(item -> referRecipe.updateAndGet(v -> v + item + "-"));
        article.setFReferPeople(referPeople.toString().length() != 0 ? referPeople.toString().substring(0, referPeople.toString().length() - 1) : "-1");
        article.setFReferArticle(referArticle.toString().length() != 0 ? referArticle.toString().substring(0, referArticle.toString().length() - 1) : "-1");
        article.setFReferRecipe(referRecipe.toString().length() != 0 ? referRecipe.toString().substring(0, referRecipe.toString().length() - 1) : "-1");
        articleDao.saveInfo(article);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> uploadCover(MultipartFile multipartFile) {
        String filePath = ToolsApi.multipartFileUploadFile(multipartFile, null);
        return ResApi.getSuccess(filePath);
//        return ResApi.getSuccess("this is a image path");
    }

    @Override
    public ResApi<Object> articleIndex(Integer aid, Integer uid) {
        Map<String, Object> map = new HashMap<>(8);
        Article article = articleDao.findInfoById(aid);
        article.setFName(ToolsApi.base64Decode(article.getFName()));
        article.setFContent(ToolsApi.base64Decode(article.getFContent()));
        rabbitTemplate.convertAndSend("recipeSystem.direct", "articleCountUpload.queue", aid);
        if (article.getArticleComments().size() > 3) {
            article.setArticleComments(article.getArticleComments().subList(0, 3));
        }
        article.getArticleComments().forEach(item -> {
            item.setFGood(item.getArticleCommentGreats().size());
            item.setFContent(ToolsApi.base64Decode(item.getFContent()));
        });
        article.getArticleComments().sort((o1, o2) -> o2.getFGood().compareTo(o1.getFGood()));
        if (!uid.equals(-1) && articleGreatDao.getByAidAndUid(aid, uid).size() != 0) {
            map.put("isGreat", true);
        } else {
            map.put("isGreat", false);
        }
        if (!uid.equals(-1) && collectDao.findByAllBrief(new Collect(uid, aid, 2)).size() != 0) {
            map.put("isCollect", true);
        } else {
            map.put("isCollect", false);
        }
        if (commonAttentionDao.findInfoByUidAndOidAndType(uid, article.getCommonUser().getFId(), 1) != 0) {
            map.put("isAttention", true);
        } else {
            map.put("isAttention", false);
        }
        String nullData = "-1";
        if (!nullData.equals(article.getFReferPeople())) {
            List<ArticleRefer> articleRefersForPeople = new LinkedList<>();
            Arrays.asList(article.getFReferPeople().split("-")).forEach(item ->
                    articleRefersForPeople.add(
                            new ArticleRefer(Integer.valueOf(item), commonUserDao.getUsernameById(Integer.valueOf(item)))
                    ));
            article.setReferPeople(articleRefersForPeople);
        }
        if (!nullData.equals(article.getFReferRecipe())) {
            List<ArticleRefer> articleRefersForArticle = new LinkedList<>();
            Arrays.asList(article.getFReferArticle().split("-")).forEach(item ->
                    articleRefersForArticle.add(
                            new ArticleRefer(Integer.valueOf(item), ToolsApi.base64Decode(articleDao.findNameById(Integer.valueOf(item))))
                    ));
            article.setReferArticle(articleRefersForArticle);
        }
        if (!nullData.equals(article.getFReferRecipe())) {
            List<ArticleRefer> articleRefersForRecipe = new LinkedList<>();
            Arrays.asList(article.getFReferRecipe().split("-")).forEach(item ->
                    articleRefersForRecipe.add(
                            new ArticleRefer(Integer.valueOf(item), recipeDao.getNameById(Integer.valueOf(item)))
                    ));
            article.setReferRecipe(articleRefersForRecipe);
        }
        map.put("article", article);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> greatOperation(Integer open, Integer aid, Integer uid) {
        if (open.equals(1)) {
            articleGreatDao.saveInfo(aid, uid);
        } else {
            articleGreatDao.deleteInfo(aid, uid);
        }
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<String> collectArticle(Integer open, Collect collect) {
        if (open.equals(1)) {
            collectDao.saveInfo(collect);
        } else {
            collectDao.deleteInfo(collect);
        }
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> articleListIndex() {
        Map<String, List<Article>> map = new HashMap<>(8);
        List<Article> articleList = articleDao.findAllInfo(),
                compareList = new ArrayList<>(articleList),
                bannerList;
        int maxLen = 8;
        if (articleList.size() > maxLen) {
            bannerList = articleList.subList(0, 8);
        } else {
            bannerList = articleList.subList(0, articleList.size());
        }
        bannerList.forEach(item -> item.setFName(ToolsApi.base64Decode(item.getFName())));
        map.put("bannerList", bannerList);
        compareList.sort((o1, o2) -> o2.getFCollect().compareTo(o1.getFCollect()));
        compareList.forEach(item -> item.setFName(ToolsApi.base64Decode(item.getFName())));
        compareList.forEach(item -> item.setFContent(ToolsApi.base64Decode(item.getFContent())));
        map.put("dataList", compareList);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> articleForTopic(Integer classify) {
        Map<String, Object> map = new HashMap<>(8);
        List<Article> articles = articleDao.findInfoByTopicBrief(classify);
        articles.forEach(item -> item.setFName(ToolsApi.base64Decode(item.getFName())));
        map.put("articleList", articles);
        articles = articles.stream().collect(
                Collectors.collectingAndThen(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(Article::getFUid))), ArrayList::new));
        map.put("peopleList", articles);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> handpickList() {
        List<Article> articleList = articleDao.findAllInfo();
        articleList.forEach(item -> item.setFName(ToolsApi.base64Decode(item.getFName())));
        articleList.sort((o1, o2) -> {
            if (o2.getFCount().compareTo(o1.getFCount()) > 0) {
                return 1;
            } else if (o2.getFCount().compareTo(o1.getFCount()) == 0) {
                return o2.getFCollect().compareTo(o1.getFCollect());
            } else {
                return -1;
            }
        });
        return ResApi.getSuccess(articleList);
    }

    @Override
    public ResApi<Object> getInfoAndRecipeList() {
        List<Article> articleList = articleDao.findAllInfo();
        List<Recipe> recipeList = recipeDao.getAllInfo();
        Map<String, Object> map = new HashMap<>(8);
        map.put("articleList", articleList);
        map.put("recipeList", recipeList);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> getAllInfoByPage(Integer index) {
        List<Article> articles = new LinkedList<>();
        List<Article> data = articleDao.findAllInfo();
        if (index * 10 <= data.size()) {
            articles = data.subList((index - 1) * 10, index * 10);
        } else if ((index - 1) * 10 <= data.size()) {
            articles = data.subList((index - 1) * 10, data.size());
        }
        for (Article item : articles) {
            item.setFName(ToolsApi.base64Decode(item.getFName())).
                    setFContent(ToolsApi.base64Decode(item.getFContent()));
        }
        return ResApi.getSuccess(articles);
    }

    @Override
    public ResApi<Object> getArticleByUid(Integer fUid) {
        List<Article> infoByUidBrief = articleDao.findInfoByUidBrief(fUid);
        for (Article item : infoByUidBrief) {
            item.setFContent(ToolsApi.base64Decode(item.getFContent())).setFName(ToolsApi.base64Decode(item.getFName()));
        }
        return ResApi.getSuccess(infoByUidBrief);
    }

    @Override
    public ResApi<Object> referArticle() {
        return ResApi.getSuccess(articleDao.findAllInfoBrief());
    }

}
