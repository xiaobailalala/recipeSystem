package com.smxy.recipe.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
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
        map.put("count", merchantProductDao.getAllProduct().size());
        map.put("item", merchantProductDao.getAllProduct());
        return map;
    }

    @Override
    public Map<String, Object> productAllById(Integer mId) {
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(mId);
        Map<String, Object> map = new HashMap<>(8);
        map.put("code", ResApi.getSuccess().getCode());
        map.put("msg", ResApi.getSuccess().getMsg());
        map.put("count", merchantUser.getMerchantProducts().size());
        map.put("item", merchantUser.getMerchantProducts());
        return map;
    }

    @Override
    public Map<String, Object> productAllPage(Integer mId, HttpServletRequest request) {
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        MerchantUser merchantUser = merchantUserDao.getMerchantUserById(mId);
        List<MerchantProduct> productList = merchantUser.getMerchantProducts();
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
        merchantProduct.setFName(pro_title);
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
        List<MerchantProductImage> productImages = merchantProductImageDao.getProductByfPid(fId);
        merchantProduct.setFCover(productImages.get(0).getFImg());
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
        int productUpdate = merchantProductDao.updateProductInfo(merchantProduct);
        if (productUpdate > 0) {
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
    public ResApi<String> mobSaveProductDetails(MultipartFile[] detailsImage, String[] detailsContent, Integer userId) {
        List<MerchantProductDetails> list = new ArrayList<>();
        if (detailsImage.length != detailsContent.length) {
            return ResApi.getError(502, "保存失败");
        }
        for (int i = 0; i < detailsImage.length; i++) {
            String filename;
            if (detailsImage[i].getSize() == 0 || detailsImage[i] == null) {
                filename = null;
            } else {
                filename = ToolsApi.multipartFileUploadFile(detailsImage[i], null);
            }
            list.add(new MerchantProductDetails(null, filename, detailsContent[i]));
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
    public ResApi<String> changeState(Integer id, String state) {
        merchantProductDao.updateProductReviewById(state, id);
        return ResApi.getSuccess();
    }
}
