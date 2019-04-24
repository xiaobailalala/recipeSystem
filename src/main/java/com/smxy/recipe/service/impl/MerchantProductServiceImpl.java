package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.RedisUtil;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Demo MerchantProductService
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 15:19
 */
@Transactional(rollbackFor = Exception.class)
@Service("merchantProductService")
public class MerchantProductServiceImpl implements MerchantProductService {
    private static final String PRODUCT_STATE_SHELVE = "shelve";
    private static final String PRODUCTLIST_TYPE_ALL = "ALL";
    private static final String PRODUCTLIST_TYPE_ACTION = "ACTION";


    private final MerchantUserDao merchantUserDao;
    private final MerchantProductDao merchantProductDao;
    private final MerchantProductImageDao merchantProductImageDao;
    private final MerchantProductMarqueDao merchantProductMarqueDao;
    private final MerchantProductDetailsDao merchantProductDetailsDao;
    private final MerchantUserProductDao merchantUserProductDao;
    private final MerchantProductClassifyDao merchantProductClassifyDao;
    private final MerchantProductMarqueClassifyDao merchantProductMarqueClassifyDao;
    private final MerchantProductFreightDao merchantProductFreightDao;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    public MerchantProductServiceImpl(MerchantProductDao merchantProductDao,
                                      MerchantProductImageDao merchantProductImageDao,
                                      MerchantProductMarqueDao merchantProductMarqueDao,
                                      MerchantProductDetailsDao merchantProductDetailsDao,
                                      MerchantUserProductDao merchantUserProductDao,
                                      MerchantProductClassifyDao merchantProductClassifyDao,
                                      MerchantProductMarqueClassifyDao merchantProductMarqueClassifyDao,
                                      MerchantProductFreightDao merchantProductFreightDao,
                                      MerchantUserDao merchantUserDao) {
        this.merchantProductDao = merchantProductDao;
        this.merchantProductImageDao = merchantProductImageDao;
        this.merchantProductMarqueDao = merchantProductMarqueDao;
        this.merchantProductDetailsDao = merchantProductDetailsDao;
        this.merchantUserProductDao = merchantUserProductDao;
        this.merchantProductClassifyDao = merchantProductClassifyDao;
        this.merchantProductMarqueClassifyDao = merchantProductMarqueClassifyDao;
        this.merchantProductFreightDao = merchantProductFreightDao;
        this.merchantUserDao = merchantUserDao;
    }

    @Override
    public Map<String, Object> productAll() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", ResApi.getSuccess().getCode());
        map.put("msg", ResApi.getSuccess().getMsg());
        map.put("count", merchantProductDao.getAllProductShelve().size());
        map.put("item", merchantProductDao.getAllProductShelve());
        return map;
    }

    @Override
    public Map<String, Object> productAllById(Integer mId) {
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(mId);
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", ResApi.getSuccess().getCode());
        map.put("msg", ResApi.getSuccess().getMsg());
        map.put("count", merchantUser.getMerchantUserProducts().size());
        map.put("item", merchantUser.getMerchantUserProducts());
        return map;
    }

    @Override
    public Map<String, Object> productAllPage(Integer mId, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(mId);
        List<MerchantUserProduct> productList = merchantUser.getMerchantUserProducts();
        int startIndex = (page - 1) * limit;
        int endIndex = page * limit;
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", ResApi.getSuccess().getCode());
        map.put("msg", ResApi.getSuccess().getMsg());
        map.put("count", productList.size());
        if (endIndex > productList.size()) {
            map.put("item", productList.subList(startIndex, productList.size()));
        } else {
            map.put("item", productList.subList(startIndex, endIndex));
        }

        return map;
    }

    @Override
    public ResApi<String> saveProduct(MultipartFile[] productImage, String pro_title, MerchantProduct merchantProduct, String[] pro_price, String[] pro_repertory,
                                      MultipartFile[] marqueImage, String[] productDetailsContent,
                                      MultipartFile[] detailsImage, String[] marqueName, HttpServletRequest request) {
        MerchantUser merchantUser = (MerchantUser) request.getSession().getAttribute("merUser");
        merchantProduct.setFName(ToolsApi.stripXss(pro_title));
        merchantProduct.setFAddtime(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow());
        merchantProduct.setFCover(ToolsApi.multipartFileUploadFile(productImage[0], null));
        merchantProduct.setFGood(0);
        merchantProduct.setFState("下架");
        merchantProduct.setFSales(0);
        merchantProduct.setFDiscount("没有活动");
        merchantProduct.setFReduction("没有活动");
        //TODO: 商品审核状态 [待审核、审核成功、审核失败]
        merchantProduct.setFReview("待审核");
        merchantProduct.setFGrosssales((double) 0);
        if (merchantProduct.getFMarqueclaid() == null) {
            //TODO 此处的fMarqueclaid 为1 是指默认型号的id值为1
            merchantProduct.setFMarqueclaid(1);
        }
        merchantProduct.setFCategory(merchantProductClassifyDao.getProductClassifyById(Integer.parseInt(merchantProduct.getFCategory())).getFName());
        int productId = merchantProductDao.saveProductInfo(merchantProduct);
        if (productId > 0) {
            for (int i = 0; i < productImage.length; i++) {
                String filename;
                if (productImage[i] == null || productImage[i].getSize() == 0) {
                    filename = null;
                } else {
                    filename = ToolsApi.multipartFileUploadFile(productImage[i], null);
                }
                merchantProductImageDao.saveProductImage(new MerchantProductImage(merchantProduct.getFId(), filename));
            }
            if (marqueName.length > 0) {
                for (int i = 0; i < marqueName.length; i++) {
                    String filename;
                    if (marqueImage[i] == null || marqueImage[i].getSize() == 0) {
                        filename = null;
                    } else {
                        filename = ToolsApi.multipartFileUploadFile(marqueImage[i], null);
                    }
                    merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque(marqueName[i], filename, Double.parseDouble(pro_price[i]), Integer.parseInt(pro_repertory[i]), merchantProduct.getFMarqueclaid(), merchantProduct.getFId()));
                }
            } else {
                merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque("默认型号", null, Double.parseDouble(pro_price[0]), Integer.parseInt(pro_repertory[0]), merchantProduct.getFMarqueclaid(), merchantProduct.getFId()));
            }
            for (int i = 0; i < productDetailsContent.length; i++) {
                String filename;
                if (detailsImage[i] == null || detailsImage[i].getSize() == 0) {
                    filename = null;
                } else {
                    filename = ToolsApi.multipartFileUploadFile(detailsImage[i], null);
                }
                merchantProductDetailsDao.saveProductDetailsInfo(new MerchantProductDetails(merchantProduct.getFId(), filename, productDetailsContent[i]));
            }
            merchantUserProductDao.saveMerchantUserProductInfo(new MerchantUserProduct(merchantUser.getFId(), merchantProduct.getFId()));
            return ResApi.getSuccess();
        } else {
            return ResApi.getError(501, "基本信息保存出错，请重新尝试");
        }
    }

    @Override
    public ResApi<String> deleteProductBuId(Integer fid) {

        if (merchantProductDao.deleteProductByFId(fid) > 0
                && merchantProductImageDao.deleteProductImageByfPid(fid) > 0
                && merchantProductMarqueDao.deleteMarqueByFPid(fid) > 0
                && merchantProductDetailsDao.deleteProductDetailsByFPid(fid) >= 0
                && merchantUserProductDao.deleteMerchantUserProductByFPid(fid) > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<String> updateProductStatusById(Integer fId, String fState) {
        int code;
        if (fState.equals(PRODUCT_STATE_SHELVE)) {
            code = merchantProductDao.updateProductStatusById(fId, "上架");
        } else {
            code = merchantProductDao.updateProductStatusById(fId, "下架");
        }
        if (code > 0) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError(502, "操作失败！请重试");
        }
    }

    @Override
    public ResApi<String> updateProductInfo(Integer fId, MerchantProduct merchantProduct,
                                            MultipartFile[] productImage, MultipartFile[] proMarqueImg,
                                            MultipartFile[] proDetailsImg, String proTitle,
                                            String[] marqueName, String[] marquePrice,
                                            String[] marqueRepository, String[] productDetailsContent,
                                            Integer[] productImageId, Integer[] marqueId,
                                            Integer[] productDetailsId, Integer[] deleteMarqueImageFlag,
                                            Integer[] deleteDetailImageFlag, Integer[] marqueImageFlag,
                                            Integer[] detailImageFlag,
                                            HttpServletRequest request) {
        MerchantProduct merchantProductPast = merchantProductDao.getProductById(fId);
        merchantProduct.setFName(proTitle);
        merchantProduct.setFGood(merchantProductPast.getFGood());
        merchantProduct.setFReduction(merchantProductPast.getFReduction());
        merchantProduct.setFDiscount(merchantProductPast.getFDiscount());
        if (merchantProduct.getFCategory() == null) {
            merchantProduct.setFCategory(merchantProductPast.getFCategory());
        } else {
            merchantProduct.setFCategory(merchantProductClassifyDao.getProductClassifyById(Integer.parseInt(merchantProduct.getFCategory())).getFName());
        }
        merchantProduct.setFState(merchantProductPast.getFState());
        merchantProduct.setFSales(merchantProductPast.getFSales());
        if (merchantProduct.getFMarqueclaid() == null) {
            merchantProduct.setFMarqueclaid(merchantProductPast.getFMarqueclaid());
        }
        merchantProduct.setFAddtime(merchantProductPast.getFAddtime());
        merchantProduct.setFGrosssales(merchantProductPast.getFGrosssales());
        if (merchantProduct.getFFreightid() == null) {
            merchantProduct.setFFreightid(merchantProductPast.getFFreightid());
        }
        merchantProduct.setFReview(merchantProductPast.getFReview());
        //TODO: 商品主图更新操作
        if (productImageId != null && productImageId.length > 0) {
            List<Integer> compare = ToolsApi.compare(Arrays.asList(productImageId), merchantProductImageDao.getProductImageId(fId));
            for (Integer integer : compare) {
                merchantProductImageDao.deleteProductImageByFId(integer);
            }
        }
        if (productImage.length > 0) {
            for (MultipartFile aProductImage : productImage) {
                String filename;
                if (aProductImage == null || aProductImage.getSize() == 0) {
                    filename = null;
                } else {
                    filename = ToolsApi.multipartFileUploadFile(aProductImage, null);
                }
                merchantProductImageDao.saveProductImage(new MerchantProductImage(fId, filename));
            }
        }
        List<MerchantProductImage> productImages = merchantProductImageDao.getProductByfPid(fId);
        merchantProduct.setFCover(productImages.get(0).getFImg());
        int productUpdate = merchantProductDao.updateProductInfo(merchantProduct);
        if (productUpdate > 0) {
            //TODO:商品类型更新操作
            List<Integer> marqueIdToList = Arrays.asList(marqueId);
            List<Integer> marqueIdList = new ArrayList<>(marqueIdToList);
            if (marqueIdList.size() > 0) {
                List<Integer> compare = ToolsApi.compare(marqueIdList, merchantProductMarqueDao.getMarqueId(fId));
                for (Integer integer : compare) {
                    merchantProductMarqueDao.deleteMarqueByFId(integer);
                }
            }
            for (int i = 0; i < proMarqueImg.length; i++) {
                String filename;
                if (proMarqueImg[i].getSize() == 0 && marqueImageFlag[i] == 1) {
                    merchantProductMarqueDao.updateMarqueInfo(new MerchantProductMarque(marqueIdList.get(0), marqueName[i],
                            merchantProductMarqueDao.getMarqueImagePathById(marqueIdList.get(0)),
                            Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]), merchantProduct.getFMarqueclaid(), fId));
                    Integer remove = marqueIdList.remove(0);
                } else if (proMarqueImg[i].getSize() == 0 && marqueImageFlag[i] == 0) {
                    if (Arrays.asList(deleteMarqueImageFlag).get(0).equals(marqueIdList.get(0))) {
                        merchantProductMarqueDao.updateMarqueInfo(new MerchantProductMarque(marqueIdList.get(0), marqueName[i], null,
                                Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]), merchantProduct.getFMarqueclaid(), fId));
                        Integer remove = marqueIdList.remove(0);
                    } else {
                        filename = null;
                        merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque(marqueName[i], filename,
                                Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]),
                                merchantProduct.getFMarqueclaid(), fId));
                    }
                } else {
                    if (proMarqueImg[i].getSize() > 0 && marqueIdList.size() > 0) {
                        filename = ToolsApi.multipartFileUploadFile(proMarqueImg[i], null);
                        merchantProductMarqueDao.updateMarqueInfo(new MerchantProductMarque(marqueIdList.get(0), marqueName[i], filename,
                                Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]), merchantProduct.getFMarqueclaid(), fId));
                        Integer remove = marqueIdList.remove(0);
                    } else {
                        filename = ToolsApi.multipartFileUploadFile(proMarqueImg[i], null);
                        merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque(marqueName[i], filename,
                                Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]),
                                merchantProduct.getFMarqueclaid(), fId));
                    }
                }
            }
            //TODO: 商品详情更新操作
            List<Integer> detailsIdToList = Arrays.asList(productDetailsId);
            List<Integer> detailsIdList = new ArrayList<>(detailsIdToList);
            if (detailsIdList.size() > 0) {
                List<Integer> compare = ToolsApi.compare(detailsIdList, merchantProductDetailsDao.getDetailsId(fId));
                for (Integer integer : compare) {
                    merchantProductDetailsDao.deleteProductDetailsByFId(integer);
                }
            }
            for (int i = 0; i < proDetailsImg.length; i++) {
                String filename;
                if (proDetailsImg[i].getSize() == 0 && detailImageFlag[i] == 1) {
                    merchantProductDetailsDao.updateProductDetailsInfo(new MerchantProductDetails(detailsIdList.get(0), fId,
                            merchantProductDetailsDao.getDetailImagePath(detailsIdList.get(0)), productDetailsContent[i]));
                    Integer remove = detailsIdList.remove(0);
                } else if (proDetailsImg[i].getSize() == 0 && detailImageFlag[i] == 0) {
                    if (Arrays.asList(deleteDetailImageFlag).get(0).equals(detailsIdList.get(0))) {
                        merchantProductDetailsDao.updateProductDetailsInfo(new MerchantProductDetails(detailsIdList.get(0), fId,
                                null, productDetailsContent[i]));
                        Integer remove = detailsIdList.remove(0);
                    } else {
                        filename = null;
                        merchantProductDetailsDao.saveProductDetailsInfo(new MerchantProductDetails(fId, filename, productDetailsContent[i]));
                    }
                } else {
                    if (proDetailsImg[i].getSize() > 0 && detailsIdList.size() > 0) {
                        filename = ToolsApi.multipartFileUploadFile(proDetailsImg[i], null);
                        merchantProductDetailsDao.updateProductDetailsInfo(new MerchantProductDetails(detailsIdList.get(0), fId,
                                filename, productDetailsContent[i]));
                        Integer remove = detailsIdList.remove(0);
                    } else {
                        filename = ToolsApi.multipartFileUploadFile(proDetailsImg[i], null);
                        merchantProductDetailsDao.saveProductDetailsInfo(new MerchantProductDetails(fId, filename, productDetailsContent[i]));
                    }
                }
            }
            return ResApi.getSuccess();
        } else {
            return ResApi.getError(500, "商品更新失败");
        }
    }

    @Override
    public ResApi<Object> getProductById(Integer fId) {
        Map<String, Object> map = new HashMap<>(8);
        MerchantProduct merchantProduct = merchantProductDao.getProductById(fId);
        Integer fMid = merchantUserProductDao.getMerchantUserProductByPidBrief(fId).getFMid();
        map.put("merchantUser", merchantUserDao.getMerchantUserByIdBrief(fMid));
        map.put("productImage", merchantProductImageDao.getProductByfPid(fId));
        map.put("product", merchantProduct);
        map.put("marqueClassifyName", merchantProductMarqueClassifyDao.getMarqueClassifyById(merchantProduct.getFMarqueclaid()));
        map.put("productFreight", merchantProductFreightDao.getMerchantProductFreightById(merchantProduct.getFFreightid()));
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<Object> mobSaveProduct(Integer userId, MultipartFile[] productImage, MultipartFile[] marqueImage, String productName, Integer productClassifyID, String json, Integer freightID) {
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(userId);
        MerchantProduct merchantProduct = new MerchantProduct();
        merchantProduct.setFName(productName);
        merchantProduct.setFAddtime(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow());
        merchantProduct.setFCover(ToolsApi.multipartFileUploadFile(productImage[0], null));
        merchantProduct.setFGood(0);
        merchantProduct.setFState("下架");
        merchantProduct.setFSales(0);
        merchantProduct.setFDiscount("没有活动");
        merchantProduct.setFReduction("没有活动");
        merchantProduct.setFFreightid(freightID);
        //TODO: 商品审核状态 [待审核、审核成功、审核失败]
        merchantProduct.setFReview("待审核");
        merchantProduct.setFGrosssales((double) 0);
        //TODO 此处的fMarqueclaid 为1 是指默认型号的id值为1,手机应用添加商品默认都是默认型号
        merchantProduct.setFMarqueclaid(1);
        merchantProduct.setFCategory(merchantProductClassifyDao.getProductClassifyById(productClassifyID).getFName());
        int productRes = merchantProductDao.saveProductInfo(merchantProduct);
        merchantUserProductDao.saveMerchantUserProductInfo(new MerchantUserProduct(merchantUser.getFId(), merchantProduct.getFId()));
        List<Map<String, Object>> marqueList = new ArrayList<>();
        JSONArray objects = JSONArray.parseArray(json);
        for (Object arr : objects) {
            Map map = JSON.parseObject(arr.toString(), Map.class);
            Map<String, Object> tempMap = new HashMap<>(8);
            for (Object o : map.keySet()) {
                tempMap.put(o.toString(), map.get(o));
            }
            marqueList.add(tempMap);
        }
        if (productRes > 0) {
            for (int i = 0; i < productImage.length; i++) {
                String filename;
                if (productImage[i] == null || productImage[i].getSize() == 0) {
                    filename = null;
                } else {
                    filename = ToolsApi.multipartFileUploadFile(productImage[i], null);
                }
                merchantProductImageDao.saveProductImage(new MerchantProductImage(merchantProduct.getFId(), filename));
            }
            int filenumber = 0;
            for (Map<String, Object> map : marqueList) {
                String filename;
                if ((boolean) map.get("isImg")) {
                    filename = ToolsApi.multipartFileUploadFile(marqueImage[filenumber], null);
                    merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque((String) map.get("model"), filename, Double.parseDouble((String) map.get("price")), Integer.parseInt((String) map.get("inventory")), 1, merchantProduct.getFId()));
                    filenumber++;
                } else {
                    merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque((String) map.get("model"), null, Double.parseDouble((String) map.get("price")), Integer.parseInt((String) map.get("inventory")), 1, merchantProduct.getFId()));
                }
            }
            Map<String, List<MerchantProductDetails>> detailMap = (Map<String, List<MerchantProductDetails>>) redisUtil.hashMapGet("merchantProductDetails" + userId);
            List<MerchantProductDetails> productDetails = detailMap.get("merchantProductDetails");
            if (productDetails != null && productDetails.size() != 0) {
                for (MerchantProductDetails productDetail : productDetails) {
                    productDetail.setFPid(merchantProduct.getFId());
                    merchantProductDetailsDao.saveProductDetailsInfo(productDetail);
                }
            }
            redisUtil.delete("merchantProductDetails" + userId);
            return new ResApi<>(200, "success", "Pid:" + merchantProduct.getFId());
        } else {
            return new ResApi<>(500, "error", "系统出错");
        }
    }

    @Override
    public ResApi<String> mobSaveProductDetails(MultipartFile[] detailsImage, String detailsContent, Integer userId) {
        List<MerchantProductDetails> list = new ArrayList<>();
        JSONArray array = JSON.parseArray(detailsContent);
        String[] contents = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            contents[i] = array.getString(i);
        }
        if (detailsImage.length != contents.length) {
            return ResApi.getError(502, "保存失败");
        }
        for (int i = 0; i < detailsImage.length; i++) {
            String filename;
            if (detailsImage[i].getSize() == 0 || detailsImage[i] == null) {
                filename = null;
            } else {
                filename = ToolsApi.multipartFileUploadFile(detailsImage[i], null);
            }
            list.add(new MerchantProductDetails(null, filename, contents[i]));
        }
        Map<String, List<MerchantProductDetails>> listMap = new HashMap<>(8);
        listMap.put("merchantProductDetails", list);
        boolean result = redisUtil.hashMapSet("merchantProductDetails" + userId, listMap);
        if (result) {
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<List<MerchantProductDetails>> getProductDetailByPid(Integer pid) {
        if (pid == null) {
            return new ResApi<>(505, "商品id不存在", null);
        }
        List<MerchantProductDetails> detailsList = merchantProductDetailsDao.getProductDetailsByPid(pid);
        return new ResApi<>(200, "success", detailsList);
    }

    @Override
    public ResApi<String> editorProductDetailByPid(Integer pid, MultipartFile[] images, String[] details, Integer[] ids) {
        if (images.length != ids.length || details.length != ids.length) {
            return new ResApi<>(505, "保存失败，参数长度不符", null);
        }
        List<MerchantProductDetails> detailsList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            String fileName = null;
            if (images[i].getSize() == 0 || images[i] == null) {
                fileName = null;
            } else {
                fileName = ToolsApi.multipartFileUploadFile(images[i], null);
            }
            detailsList.add(new MerchantProductDetails(ids[i], pid, fileName, details[i]));
        }
        return null;
    }

    @Override
    public ResApi<Object> getProductByClaid(Integer claid, String type, JSONObject params) {
        Integer pageIndex = params.getInteger("pageIndex");
        Integer pageSize = params.getInteger("pageSize");
        int pageStart = pageSize * (pageIndex - 1);
        List<JSONObject> dataList = new ArrayList<>(8);
        JSONObject result = new JSONObject();
        MerchantProductClassify productClassify = merchantProductClassifyDao.getProductClassifyById(claid);
        List<MerchantProduct> productList = merchantProductDao.getProductByClaid(productClassify.getFName());
        if (productList == null || productList.size() == 0) {
            return ResApi.getSuccess(new ArrayList<>(8));
        }
        if (PRODUCTLIST_TYPE_ALL.equals(type)) {
            System.out.println(productList.get(0));
            dataList = productList.stream().map(this::makeProductByClaidJson).skip(pageStart).limit(pageSize).collect(Collectors.toList());
        } else if (PRODUCTLIST_TYPE_ACTION.equals(type)) {
            List<MerchantProduct> actionList = new ArrayList<>(8);
            for (MerchantProduct product : productList) {
                if (product.getProductActiveDiscount() != null || product.getProductActiveReduction() != null) {
                    actionList.add(product);
                }
            }
            dataList = actionList.stream().map(this::makeProductByClaidJson).skip(pageStart).limit(pageSize).collect(Collectors.toList());
        }
        return ResApi.getSuccess(dataList);
    }

    private JSONObject makeProductByClaidJson(MerchantProduct product) {
        JSONObject result = new JSONObject();
        List<MerchantProductMarque> marqueList = product.getMerchantProductMarques();
        result.put("productId", product.getFId());
        result.put("productImage", product.getFCover());
        result.put("productName", product.getFName());
        if (marqueList == null || marqueList.size() == 0) {
            result.put("productPriceMin", 0);
            result.put("productPriceMax", 0);
        } else {
            result.put("productPriceMin", marqueList.get(0).getFPrice());
            result.put("productPriceMax", marqueList.get(marqueList.size() - 1).getFPrice());
        }
        result.put("productSale", product.getFSales());
        result.put("productFreight", product.getFFreightid());
        return result;
    }

    @Override
    public ResApi<String> changeState(Integer id, String state) {
        merchantProductDao.updateProductReviewById(state, id);
        return ResApi.getSuccess();
    }

    @Override
    public ResApi<Object> mobIndex() {
        Map<String, Object> map = new HashMap<>(8);
        List<MerchantProduct> allProduct = merchantProductDao.getAllProductShelve();
        Collections.shuffle(allProduct);
        List<MerchantProduct> hot = new LinkedList<>(allProduct);
        map.put("allArr", allProduct);
        allProduct.sort((o1, o2) -> o2.getFAddtime().compareTo(o1.getFAddtime()));
        map.put("newArr", allProduct.subList(0, 8));
        hot.sort((o1, o2) -> o2.getFGood().compareTo(o1.getFGood()));
        map.put("hotArr", hot.subList(0, 8));
        return ResApi.getSuccess(map);
    }

    @Override
    public ResApi<String> mobUpdateProduct(Integer uId, Integer pId, MultipartFile[] productImage, Integer[] productImageId,
                                           Integer freightId,
                                           String productName, Integer productClassifyID, MultipartFile[] marqueImage,
                                           Integer[] marqueId, String[] marqueName, String[] marquePrice,
                                           String[] marqueRepository, Integer[] marqueImageFlag) {
        MerchantProduct product = merchantProductDao.getProductById(pId);
        product.setFName(productName);
        product.setFCategory(merchantProductClassifyDao.getProductClassifyById(productClassifyID).getFName());
        product.setFFreightid(freightId);
        //商品主图更新操作
        if (productImageId != null && productImageId.length > 0) {
            List<Integer> compare = ToolsApi.compare(Arrays.asList(productImageId), merchantProductImageDao.getProductImageId(pId));
            for (Integer integer : compare) {
                merchantProductImageDao.deleteProductImageByFId(integer);
            }
        }
        if (productImage.length > 0) {
            for (MultipartFile aProductImage : productImage) {
                String filename;
                if (aProductImage == null || aProductImage.getSize() == 0) {
                    filename = null;
                } else {
                    filename = ToolsApi.multipartFileUploadFile(aProductImage, null);
                }
                merchantProductImageDao.saveProductImage(new MerchantProductImage(pId, filename));
            }
        }
        List<MerchantProductImage> productImages = merchantProductImageDao.getProductByfPid(pId);
        product.setFCover(productImages.get(0).getFImg());
        int productUpdate = merchantProductDao.updateProductInfo(product);
        if (productUpdate > 0) {
            //商品类型更新操作
            List<Integer> marqueIdList = Arrays.asList(marqueId);
            marqueIdList = new ArrayList<>(marqueIdList);
            if (marqueIdList.size() > 0) {
                List<Integer> compare = ToolsApi.compare(marqueIdList, merchantProductMarqueDao.getMarqueId(pId));
                for (Integer integer : compare) {
                    merchantProductMarqueDao.deleteMarqueByFId(integer);
                }
            }
            List<MultipartFile> files = Arrays.asList(marqueImage);
            ArrayList<MultipartFile> fileArrayList = new ArrayList<>(files);
            for (int i = 0; i < marqueImageFlag.length; i++) {
                String filename;
                if (marqueIdList.contains(marqueImageFlag[i])) {
                    //型号图片存在并没有改变过
                    merchantProductMarqueDao.updateMarqueInfo(new MerchantProductMarque(marqueIdList.get(0), marqueName[i],
                            merchantProductMarqueDao.getMarqueImagePathById(marqueIdList.get(0)),
                            Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]), product.getFMarqueclaid(), pId));
                    Integer remove = marqueIdList.remove(0);
                } else {
                    //新增图片
                    if (fileArrayList.size() == 0) {
                        return ResApi.getError();
                    }
                    filename = ToolsApi.multipartFileUploadFile(fileArrayList.get(0), null);
                    merchantProductMarqueDao.saveMarqueInfo(new MerchantProductMarque(marqueName[i], filename,
                            Double.parseDouble(marquePrice[i]), Integer.parseInt(marqueRepository[i]),
                            product.getFMarqueclaid(), pId));
                    fileArrayList.remove(0);
                }
            }
            return ResApi.getSuccess();
        } else {
            return ResApi.getError();
        }
    }

    @Override
    public ResApi<Object> getFourProduct(Integer fMid) {
        List<MerchantProduct> dataList = new ArrayList<>();
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(fMid);
        List<MerchantUserProduct> merchantUserProductList = merchantUser.getMerchantUserProducts();
        List<MerchantProduct> productList = new ArrayList<>();
        for (MerchantUserProduct userProduct : merchantUserProductList) {
            productList.add(userProduct.getMerchantProduct());
        }
        dataList = productList.stream().sorted(Comparator.comparing(MerchantProduct::getFGood)).limit(productList.size() > 4 ? 4 : productList.size()).collect(Collectors.toList());
        return ResApi.getSuccess(dataList);
    }

    @Override
    public ResApi<Object> getFourProduct() {
        List<MerchantProduct> dataList = new ArrayList<>();
        List<MerchantProduct> allProduct = merchantProductDao.getAllProductShelve();
        dataList = allProduct.stream().sorted(Comparator.comparing(MerchantProduct::getFGood)).limit(allProduct.size() > 4 ? 4 : allProduct.size()).collect(Collectors.toList());
        return ResApi.getSuccess(dataList);
    }


    @Override
    public ResApi<Object> getAllActiveProduct() {
        List<MerchantProduct> allProduct = merchantProductDao.getAllProductShelve();
        List<MerchantProduct> discountProductList = allProduct.stream().filter(o -> o.getProductActiveDiscount().size() > 0 && (o.getProductActiveReduction().size() == 0 || o.getProductActiveReduction() == null)).collect(Collectors.toList());
        List<MerchantProduct> reductionProductList = allProduct.stream().filter(o -> o.getProductActiveReduction().size() > 0 && (o.getProductActiveDiscount().size() == 0 || o.getProductActiveReduction() == null)).collect(Collectors.toList());
        List<MerchantProduct> activeProductList = allProduct.stream().filter(o -> o.getProductActiveReduction().size() > 0 && o.getProductActiveDiscount().size() > 0).collect(Collectors.toList());
        JSONObject result = new JSONObject();
        result.put("discount", discountProductList);
        result.put("reduction", reductionProductList);
        result.put("active", activeProductList);
        return ResApi.getSuccess(result);
    }

    @Override
    public ResApi<Object> getSixProduct() {
        List<MerchantProduct> product = merchantProductDao.getAllProductShelve();
        product = product.stream().sorted((o1, o2) -> {
            if (o1.getFGood() > o2.getFGood()) {
                return -1;
            } else {
                return 1;
            }
        }).collect(Collectors.toList());
        product = product.subList(0, product.size() > 6 ? 6 : product.size());
        return new ResApi<>(200, "success", product);
    }

    @Override
    public ResApi<Object> getFourProductActive() {
        List<MerchantProduct> allProduct = merchantProductDao.getAllProductShelve();
        List<MerchantProduct> discountProductList = allProduct.stream().filter(o -> o.getProductActiveDiscount().size() > 0 && (o.getProductActiveReduction().size() == 0 || o.getProductActiveReduction() == null)).collect(Collectors.toList());
        List<MerchantProduct> reductionProductList = allProduct.stream().filter(o -> o.getProductActiveReduction().size() > 0 && (o.getProductActiveDiscount().size() == 0 || o.getProductActiveReduction() == null)).collect(Collectors.toList());
        JSONObject result = new JSONObject();
        result.put("discount", discountProductList.subList(0, discountProductList.size() > 2 ? 2 : discountProductList.size()));
        result.put("reduction", reductionProductList.subList(0, reductionProductList.size() > 2 ? 2 : reductionProductList.size()));
        return ResApi.getSuccess(result);
    }

    @Override
    public ResApi<Object> getAllProductByMid(Integer fMid) {
        JSONObject result = new JSONObject();
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(fMid);
        List<MerchantUserProduct> merchantUserProductList = merchantUser.getMerchantUserProducts();
        List<MerchantProduct> productList = new ArrayList<>();
        for (MerchantUserProduct userProduct : merchantUserProductList) {
            productList.add(userProduct.getMerchantProduct());
        }
        result.put("productList", productList);
        return new ResApi<>(200, "success", result );
    }

    @Override
    public Map<String, Object> getAllProduct() {
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", ResApi.getSuccess().getCode());
        map.put("msg", ResApi.getSuccess().getMsg());
        map.put("count", merchantProductDao.getAllProduct().size());
        map.put("item", merchantProductDao.getAllProduct());
        return map;
    }
}
