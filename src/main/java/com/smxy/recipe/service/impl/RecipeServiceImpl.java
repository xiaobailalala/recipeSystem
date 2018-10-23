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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service("recipeService")
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private TipsDao tipsDao;
    @Autowired
    private ClassifyOneDao classifyOneDao;
    @Autowired
    private RecipeClassifyDao recipeClassifyDao;
    @Autowired
    private RecipeDao recipeDao;
    @Autowired
    private RecipeTipsDao recipeTipsDao;
    @Autowired
    private RecipeMaterialDao recipeMaterialDao;
    @Autowired
    private ProcessDao processDao;
    @Autowired
    private ClassifyTwoDao classifyTwoDao;
    @Autowired
    private ClassifyDao classifyDao;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public ResApi<Object> getAddData() {
        Map<String, Object> map = new HashMap<>(8);
        List<Tips> tips = tipsDao.getAllInfo();
        List<Tips> tipsData = new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
        for (int item : arr) {
            tipsData.add(tips.get(item));
        }
        map.put("tips", tipsData);
        map.put("clas", classifyOneDao.getAllInfo());
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> saveInfo(MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr, Integer[] threeArr,
                                   Integer[] tipArr, String[] materialNumber, Integer[] materialId, String[] materialName,
                                   String[] stepContent, String[] stepTime, HttpServletRequest request) {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("aduser");
        recipe.setFUid(0);
        recipe.setFRelease(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow());
        recipe.setFCover(ToolsApi.multipartFileUploadFile(file, null));
        int saveRecipe = recipeDao.saveInfo(recipe);
        ResApi<Object> resApi = new ResApi<>(500, "系统出错", "error");
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
            resApi = new ResApi<>(200, "success", "success");
        } else {
            resApi = new ResApi<>(501, "基本信息保存出错，请重新尝试", "failed");
        }
        return resApi;
    }

    @Override
    public ResApi<Object> getAllInfo() {
        return new ResApi<>(200, "success", recipeDao.getAllInfoBre());
    }

    @Override
    public ResApi<Object> getDetailInfo(Integer id) {
        return new ResApi<>(200, "success", recipeDao.getInfoById(id));
    }

    @Override
    public ResApi<Object> deleteInfo(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        ToolsApi.multipartFileDeleteFile(recipe.getFCover());
        recipe.getProcesses().forEach(item -> {
            if (item.getFCover()!=null){
                ToolsApi.multipartFileDeleteFile(item.getFCover());
            }
            if (item.getFVoice()!=null){
                ToolsApi.multipartFileDeleteFile(item.getFVoice());
            }
        });
        processDao.deleteInfoByRid(id);
        recipeClassifyDao.deleteInfoByRid(id);
        recipeMaterialDao.deleteInfoByRid(id);
        recipeTipsDao.deleteInfoByRid(id);
        recipeDao.deleteInfoById(id);
        processDao.deleteInfoByRid(id);
        return new ResApi<>(200, "success", "success");
    }

    @Override
    public ResApi<Object> getOneInfo(Integer id) {
        Recipe recipe = recipeDao.getInfoById(id);
        Map<String, Object> map = new HashMap<>(8);
        List<Tips> tips = tipsDao.getAllInfo();
        List<Tips> tipsData = new ArrayList<>();
        int[] arr = ToolsApi.randomArray(0, tips.size() - 1, 10);
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
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> updateInfo(Integer id, MultipartFile file, MultipartFile[] processImg, Recipe recipe, Integer[] twoArr,
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
                if (item.getFCover()!=null){
                    ToolsApi.multipartFileDeleteFile(item.getFCover());
                }
                if (item.getFVoice()!=null){
                    ToolsApi.multipartFileDeleteFile(item.getFVoice());
                }
            }
        });
        processDao.deleteInfoByRid(id);
        for (int i = 0; i < stepTime.length; i++) {
            String fileName, customFilename=null;
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
        return new ResApi<>(200, "success", "success");
    }

    @Override
    public ResApi<Object> getDataByClaId(Integer twoid, Integer threeid) {
        List<RecipeClassify> recipeClassifies;
        Map<String, List<RecipeClassify>> map = new HashMap<>(8);
        if (twoid==0){
            recipeClassifies = recipeClassifyDao.getInfoByThreeIdForRecipe(threeid);
        }else{
            recipeClassifies = recipeClassifyDao.getInfoByTwoIdForRecipe(twoid);
            recipeClassifies.addAll(recipeClassifyDao.getInfoByThreeIdNextForRecipe(twoid));
            recipeClassifies = recipeClassifies.stream().collect(
                    Collectors.collectingAndThen(Collectors.toCollection(
                            () -> new TreeSet<>(Comparator.comparing(o -> o.getFRid()))), ArrayList::new)
            );
        }
        Collections.sort(recipeClassifies, new Comparator<RecipeClassify>() {
            @Override
            public int compare(RecipeClassify o1, RecipeClassify o2) {
                return o2.getRecipe().getFCount().compareTo(o1.getRecipe().getFCount());
            }
        });
        map.put("counts", recipeClassifies);
        Collections.sort(recipeClassifies, new Comparator<RecipeClassify>() {
            @Override
            public int compare(RecipeClassify o1, RecipeClassify o2) {
                return o2.getFId().compareTo(o1.getFId());
            }
        });
        map.put("news", recipeClassifies);
        Collections.sort(recipeClassifies, new Comparator<RecipeClassify>() {
            @Override
            public int compare(RecipeClassify o1, RecipeClassify o2) {
                return o2.getRecipe().getFGood().compareTo(o1.getRecipe().getFGood());
            }
        });
        map.put("goods", recipeClassifies);
        return new ResApi<>(200, "success", map);
    }

    @Override
    public ResApi<Object> updateRecipeCount(Integer id) {
        rabbitTemplate.convertAndSend("recipeSystem.direct", "recipeCountUpload.queue", id);
        return new ResApi<>(200, "success", "success");
    }

}
