package com.smxy.recipe.service.impl;

import com.smxy.recipe.dao.*;
import com.smxy.recipe.entity.*;
import com.smxy.recipe.service.MerchantProductService;
import com.smxy.recipe.utils.ResApi;
import com.smxy.recipe.utils.ToolsApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Demo MerchantProductService
 *
 * @author Yangyihui
 * @date 2018/11/20 0020 15:19
 */
@Service("merchantProductService")
public class MerchantProductServiceImpl implements MerchantProductService {
    private static final String PRODUCT_STATE_SHELVE = "shelve";
    private static final String PRODUCT_STATE_UNSHELVE = "unshelve";

    @Autowired
    private MerchantProductDao merchantProductDao;
    @Autowired
    private MerchantProductImageDao merchantProductImageDao;
    @Autowired
    private MerchantProductMarqueDao merchantProductMarqueDao;
    @Autowired
    private MerchantProductDetailsDao merchantProductDetailsDao;
    @Autowired
    private MerchantUserProductDao merchantUserProductDao;
    @Autowired
    private MerchantProductClassifyDao merchantProductClassifyDao;

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
    public ResApi<String> saveProduct(MultipartFile[] productImage, String pro_title, MerchantProduct merchantProduct, String[] pro_price, String[] pro_repertory,
                                      MultipartFile[] marqueImage, String[] productDetailsContent,
                                      MultipartFile[] detailsImage, String[] marqueName, HttpServletRequest request) {
        MerchantUser merchantUser = (MerchantUser) request.getSession().getAttribute("merUser");
        merchantProduct.setFName(pro_title);
        merchantProduct.setFAddtime(ToolsApi.getDateToDay() + " " + ToolsApi.getTimeNow());
        merchantProduct.setFCover(ToolsApi.multipartFileUploadFile(productImage[0], null));
        merchantProduct.setFState("上架");
        merchantProduct.setFSales(0);
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
}
