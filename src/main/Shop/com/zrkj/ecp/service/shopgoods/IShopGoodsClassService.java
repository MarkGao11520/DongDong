package com.zrkj.ecp.service.shopgoods;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsClass;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsClassDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/4/14.
 */
public interface IShopGoodsClassService{

    /**
     * 获取顶级商品分类列表
     * @param page
     * @param rows
     * @param shopGoodsClassDto
     * @return
     */
    PageInfo<ShopGoodsClass> obtainTopShopGoodsClassList(int page, int rows, ShopGoodsClassDto shopGoodsClassDto);

    /**
     * 根据父id获取列表
     * @param shopGoodsClassDto
     * @return
     */
    List<ShopGoodsClass> obtainShopGoodsClassListByPid(ShopGoodsClassDto shopGoodsClassDto);

    /**
     * 添加商品分类
     * @param shopGoodsClass
     * @return
     */
    boolean addShopGoods(ShopGoodsClass shopGoodsClass);

    /**
     * 编辑商品分类
     * @param shopGoodsClass
     * @return
     */
    boolean updateShopGoods(ShopGoodsClass shopGoodsClass);


    /**
     * 上传图片
     * @param request
     * @param multipartFile
     * @return
     */
    Map<String,String> uploadPhoto(String path,HttpServletRequest request, MultipartFile multipartFile);

    /**
     * 获取树级分类
     * @param id
     * @return
     */
    List<Map<String, String>> obtainClassTree(String id);
}
