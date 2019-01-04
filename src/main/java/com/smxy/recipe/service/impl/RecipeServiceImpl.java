/**
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 *
 * @Package:
 * @author: zpx
 * Build File @date: 2018/9/4 22:34
 * @Description TODO
 * @version 1.0
 */
package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.entity.Process;
import com.smxy.recipe.entity.tools.RecipeClassifyList;
import com.smxy.recipe.service.RecipeService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Transactional(rollbackFor = Exception.class)
@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    private TipsDao tipsDao;
    private ClassifyOneDao classifyOneDao;
    private RecipeClassifyDao recipeClassifyDao;
    private RecipeDao recipeDao;
    private RecipeTipsDao recipeTipsDao;
    private RecipeMaterialDao recipeMaterialDao;
    private ProcessDao processDao;
    private ClassifyTwoDao classifyTwoDao;
    private ClassifyDao classifyDao;
    private RabbitTemplate rabbitTemplate;
    private MaterialDao materialDao;
    private CollectDao collectDao;
    private ArticleDao articleDao;
    private CommonAttentionDao commonAttentionDao;

    @Autowired
    public RecipeServiceImpl(TipsDao tipsDao, ClassifyOneDao classifyOneDao, RecipeClassifyDao recipeClassifyDao, RecipeDao recipeDao,
                             RecipeTipsDao recipeTipsDao, RecipeMaterialDao recipeMaterialDao,ProcessDao processDao, ClassifyTwoDao classifyTwoDao,
                             ClassifyDao classifyDao, RabbitTemplate rabbitTemplate, MaterialDao materialDao, CollectDao collectDao, ArticleDao articleDao,
                             CommonAttentionDao commonAttentionDao) {
        this.tipsDao = tipsDao;
        this.classifyOneDao = classifyOneDao;
        this.recipeClassifyDao = recipeClassifyDao;
        this.recipeDao = recipeDao;
        this.recipeTipsDao = recipeTipsDao;
        this.recipeMaterialDao = recipeMaterialDao;
        this.processDao = processDao;
        this.classifyTwoDao = classifyTwoDao;
        this.classifyDao = classifyDao;
        this.rabbitTemplate = rabbitTemplate;
        this.materialDao = materialDao;
        this.collectDao = collectDao;
        this.articleDao = articleDao;
        this.commonAttentionDao = commonAttentionDao;
    }

    @Override
    public ResApi<Object> getAddData() {
        Map<String, Object> map = new HashMap<>(8);
        List<Tips> tips = tipsDao.getAllInfo();
        List<Tips> tipsData = new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
        for (int item : Objects.requireNonNull(arr)) {
            tipsData.add(tips.get(item));
        }
        map.put("tips", tipsData);
        map.put("clas", classifyOneDao.getAllInfo());
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> saveInfo(MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                                   Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                   String[] stepContent, String[] stepTime) {
        recipe.setFUid(0);
        recipe.setFRelease(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow().substring(0, 5));
        recipe.setFCover(ToolsApi.multipartFileUploadFile(file, null));
        int saveRecipe = recipeDao.saveInfo(recipe);
        if (saveRecipe > 0) {
            for (Integer item : tipArr) {
                recipeTipsDao.saveInfo(new RecipeTips(recipe.getFId(), item));
            }
            for (Integer item : twoArr) {
                recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getFId(), item, 0));
            }
            for (Integer item : threeArr) {
                recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getFId(), 0, item));
            }
            for (int i = 0; i < materialId.length; i++) {
                recipeMaterialDao.saveInfo(new RecipeMaterial(recipe.getFId(), materialId[i], materialNumber[i], materialName[i]));
            }
            for (int i = 0; i < stepContent.length; i++) {
                String fileName;
                if (processImg[i] == null || processImg[i].getSize() == 0) {
                    fileName = null;
                } else {
                    fileName = ToolsApi.multipartFileUploadFile(processImg[i], null);
                }
                processDao.saveInfo(new Process(recipe.getFId(), stepContent[i], stepTime[i], fileName));
            }
            return ResApi.getSuccess();
        } else {
            return ResApi.getError(501, "基本信息保存出错，请重新尝试");
        }
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return ResApi.getSuccess(recipeDao.getAllInfoBre());
    }

    @Override
    public ResApi<Object> getDetailInfo(Collect collect) {
        Map<String, Object> map = new HashMap<>(8);
        Recipe recipe = recipeDao.getInfoById(collect.getFRid());
        map.put("item", recipe);
        if (collect.getFUid().equals(0)) {
            map.put("isCollect", false);
            map.put("isAttention", false);
        } else {
            if (collectDao.findByAllBrief(collect).size() != 0) {
                map.put("isCollect", true);
            } else {
                map.put("isCollect", false);
            }
            if (commonAttentionDao.findInfoByUidAndOidAndType(collect.getFUid(), recipe.getCommonUser().getFId(), 1) != 0) {
                map.put("isAttention", true);
            } else {
                map.put("isAttention", false);
            }
        }
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> getDetailInfo(Integer id) {
        return ResApi.getSuccess(recipeDao.getInfoById(id));
    }

    @Override
    public ResApi<String> deleteInfo(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        ToolsApi.multipartFileDeleteFile(recipe.getFCover());
        recipe.getProcesses().forEach(item -> {
            if (item.getFCover() != null) {
                ToolsApi.multipartFileDeleteFile(item.getFCover());
            }
            if (item.getFVoice() != null) {
                ToolsApi.multipartFileDeleteFile(item.getFVoice());
            }
        });
        processDao.deleteInfoByRid(id);
        recipeClassifyDao.deleteInfoByRid(id);
        recipeMaterialDao.deleteInfoByRid(id);
        recipeTipsDao.deleteInfoByRid(id);
        recipeDao.deleteInfoById(id);
        processDao.deleteInfoByRid(id);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> getOneInfo(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        Map<String, Object> map = new HashMap<>(8);
        List<Tips> tips = tipsDao.getAllInfo();
        List<Tips> tipsData = new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
        assert arr != null;
        for (int item : arr) {
            tipsData.add(tips.get(item));
        }
        map.put("item", recipe);
        map.put("tips", tipsData);
        List<RecipeClassifyList> recipeClassifyLists = new ArrayList<>();
        List<ClassifyOne> classifyOnes = classifyOneDao.getAllInfo();
        recipe.getRecipeClassifies().forEach(item -> {
            List<ClassifyTwo> classifyTwos;
            List<Classify> classifies;
            Integer one, two, three;
            if (item.getFTwoId() == 0) {
                classifyTwos = classifyTwoDao.getInfoByOid(item.getClassify().
                        getClassifyTwo().getClassifyOne().getFId());
                classifies = classifyDao.getInfoByTid(item.getClassify().
                        getClassifyTwo().getFId());
                one = item.getClassify().getClassifyTwo().getClassifyOne().getFId();
                two = item.getClassify().getClassifyTwo().getFId();
                three = item.getClassify().getFId();
            } else {
                classifyTwos = classifyTwoDao.getInfoByOid(item.getClassifyTwo().getClassifyOne().getFId());
                classifies = classifyDao.getInfoByTid(item.getClassifyTwo().getFId());
                one = item.getClassifyTwo().getClassifyOne().getFId();
                two = item.getClassifyTwo().getFId();
                three = 0;
            }
            recipeClassifyLists.add(new RecipeClassifyList(classifyOnes, classifyTwos, classifies, one, two, three));
        });
        map.put("clas", recipeClassifyLists);
        map.put("materials", recipeMaterialDao.getInfoByRid(recipe.getFId()));
        map.put("processes", processDao.getInfoByRid(recipe.getFId()));
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> updateInfo(Integer id, MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr,
                                     Integer[] threeArr, Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                     String[] stepContent, String[] stepTime, Integer[] stepPreid, HttpServletRequest request) {
        recipe.setFId(id);
        if (file.getSize() != 0) {
            ToolsApi.multipartFileDeleteFile(recipe.getFCover());
            recipe.setFCover(ToolsApi.multipartFileUploadFile(file, null));
        }
        recipeClassifyDao.deleteInfoByRid(id);
        for (Integer item : twoArr) {
            recipeClassifyDao.saveInfo(new RecipeClassify(id, item, 0));
        }
        for (Integer item : threeArr) {
            recipeClassifyDao.saveInfo(new RecipeClassify(id, 0, item));
        }
        recipeTipsDao.deleteInfoByRid(id);
        for (Integer item : tipArr) {
            recipeTipsDao.saveInfo(new RecipeTips(id, item));
        }
        recipeMaterialDao.deleteInfoByRid(id);
        for (int i = 0; i < materialId.length; i++) {
            recipeMaterialDao.saveInfo(new RecipeMaterial(id, materialId[i], materialNumber[i], materialName[i]));
        }
        List<Process> hasProcesses = new ArrayList<>();
        processDao.getInfoByRid(id).forEach(item -> {
            if (Arrays.asList(stepPreid).contains(item.getFId())) {
                hasProcesses.add(item);
            } else {
                if (item.getFCover() != null) {
                    ToolsApi.multipartFileDeleteFile(item.getFCover());
                }
                if (item.getFVoice() != null) {
                    ToolsApi.multipartFileDeleteFile(item.getFVoice());
                }
            }
        });
        processDao.deleteInfoByRid(id);
        for (int i = 0; i < stepTime.length; i++) {
            String fileName, customFilename = null;
            int flag = 0;
            for (Process hasProcess : hasProcesses) {
                if (stepPreid[i].equals(hasProcess.getFId())) {
                    flag++;
                    customFilename = hasProcess.getFCover();
                    break;
                }
            }
            if (flag == 0) {
                if (processImg[i] == null || processImg[i].getSize() == 0) {
                    fileName = null;
                } else {
                    fileName = ToolsApi.multipartFileUploadFile(processImg[i], null);
                }
            } else {
                fileName = customFilename;
            }
            processDao.saveInfo(new Process(id, stepContent[i], stepTime[i], fileName));
        }
        recipeDao.updateInfoById(recipe);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> getDataByClaId(Integer twoid, Integer threeid) {
        List<RecipeClassify> recipeClassifies;
        Map<String, List<RecipeClassify>> map = new HashMap<>(8);
        if (twoid == 0) {
            recipeClassifies = recipeClassifyDao.getInfoByThreeIdForRecipe(threeid);
        } else {
            recipeClassifies = recipeClassifyDao.getInfoByTwoIdForRecipe(twoid);
            recipeClassifies.addAll(recipeClassifyDao.getInfoByThreeIdNextForRecipe(twoid));
            recipeClassifies = recipeClassifies.stream().collect(
                    Collectors.collectingAndThen(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(RecipeClassify::getFRid))), ArrayList::new)
            );
        }
        recipeClassifies.sort((o1, o2) -> o2.getRecipe().getFCount().compareTo(o1.getRecipe().getFCount()));
        map.put("counts", recipeClassifies);
        recipeClassifies.sort((o1, o2) -> o2.getFId().compareTo(o1.getFId()));
        map.put("news", recipeClassifies);
        recipeClassifies.sort((o1, o2) -> o2.getRecipe().getFGood().compareTo(o1.getRecipe().getFGood()));
        map.put("goods", recipeClassifies);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> updateRecipeCount(Integer id) {
        rabbitTemplate.convertAndSend("recipeSystem.direct", "recipeCountUpload.queue", id);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> uploadProcessCover(MultipartFile multipartFile, String index) {
        String filePath = ToolsApi.multipartFileUploadFile(multipartFile, null);
        return ResApi.getSuccess(filePath);
    }

    @Override
    public ResApi<String> uploadRecipeInfo(MultipartFile multipartFile, Recipe recipe, String jsonArr) {
        recipe.setFCover(ToolsApi.multipartFileUploadFile(multipartFile, null));
        JSONObject json = JSONObject.parseObject(jsonArr);
        recipeDao.saveInfo(recipe);
        JSONArray tipIdArr = json.getJSONArray("tipIdArr"),
                twoArr = json.getJSONArray("twoArr"),
                threeArr = json.getJSONArray("threeArr"),
                materialNumArr = json.getJSONArray("materialNumArr"),
                materialNameArr = json.getJSONArray("materialNameArr"),
                processCover = json.getJSONArray("processCover"),
                processContent = json.getJSONArray("processContent"),
                processTime = json.getJSONArray("processTime");
        for (Object aTipIdArr : tipIdArr) {
            recipeTipsDao.saveInfo(new RecipeTips(recipe.getFId(), Integer.parseInt(aTipIdArr.toString())));
        }
        for (int i = 0; i < twoArr.size(); i++) {
            recipeClassifyDao.saveInfo(new RecipeClassify(recipe.getFId(), Integer.parseInt(twoArr.get(i).toString()), Integer.parseInt(threeArr.get(i).toString())));
        }
        for (int i = 0; i < materialNumArr.size(); i++) {
            Integer mid = 0;
            Material material = materialDao.getInfoByName(materialNameArr.get(i).toString());
            if (material != null) {
                mid = material.getFId();
            }
            recipeMaterialDao.saveInfo(new RecipeMaterial(recipe.getFId(), mid, materialNumArr.get(i).toString(), materialNameArr.get(i).toString()));
        }
        for (int i = 0; i < processCover.size(); i++) {
            processDao.saveInfo(new Process(recipe.getFId(), processContent.get(i).toString(), processTime.get(i).toString(), processCover.get(i).toString()));
        }
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<String> saveRecipeCollection(Collect collect) {
        if (collectDao.saveInfo(collect) > 0) {
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<String> deleteRecipeCollect(Collect collect) {
        if (collectDao.deleteInfo(collect) > 0) {
            return ResApi.getSuccess();
        }
        return ResApi.getError();
    }

    @Override
    public ResApi<Object> clientIndexData() {
        Map<String, Object> map = new HashMap<>(8);
        List<Recipe> recipeList = recipeDao.getAllInfoBre();
        List<Article> articleList = articleDao.findAllInfo();
        articleList.forEach(item -> item.setFName(ToolsApi.base64Decode(item.getFName())));
        map.put("randomRecipe", randomRecipe(recipeList));
        map.put("articleList", articleList);
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> randomRecipe() {
        List<Recipe> recipeList = recipeDao.getAllInfoBre();
        return ResApi.getSuccess(randomRecipe(recipeList));
    }

    @Override
    public ResApi<Object> handpickList() {
        List<Recipe> recipeList = recipeDao.getAllInfoBre();
        recipeList.sort((o1, o2) -> {
            if (o2.getFCount().compareTo(o1.getFCount()) > 0) {
                return 1;
            } else if (o2.getFCount().compareTo(o1.getFCount()) == 0) {
                return o2.getFGood().compareTo(o1.getFGood());
            } else {
                return -1;
            }
        });
        return ResApi.getSuccess(recipeList);
    }

    @Override
    public ResApi<Object> getDataByMid(Integer mid) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("material", materialDao.getInfoById(mid));
        map.put("dataList", recipeMaterialDao.getInfoByMid(mid));
        return ResApi.getSuccess(map);
    }

    private List<Recipe> randomRecipe(List<Recipe> recipeList){
        int[] index = ToolsApi.randomArray(0, recipeList.size()-1, 8);
        List<Recipe> recipes = new ArrayList<>();
        assert index != null;
        for (int in : index) {
            recipes.add(recipeList.get(in));
        }
        recipes.sort((o1, o2) -> o2.getFCount().compareTo(o1.getFCount()));
        return recipes;
    }

}
