package com.zrkj.ecp.controller.shopgood;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsClass;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsClassDto;
import com.zrkj.ecp.service.shopgoods.IShopGoodsClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/4/14.
 */
@Controller
@Scope("prototype")
@RequestMapping("shopGoodsClassController")
public class ShopGoodsClassController {
    @Autowired
    IShopGoodsClassService iShopGoodsClassService;

    /**
     * 获取顶级分类列表
     * @param page
     * @param rows
     * @param shopGoodsClassDto
     * @return
     */
    @RequestMapping(value = "jsonTopShopGoodsClassList",produces = {MediaType.APPLICATION_JSON_VALUE},method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<ShopGoodsClass> jsonTopShopGoodsClassList(int page, int rows, ShopGoodsClassDto shopGoodsClassDto){
        return iShopGoodsClassService.obtainTopShopGoodsClassList(page,rows,shopGoodsClassDto);
    }

    /**
     * 根据父级id获取分类列表
     * @param shopGoodsClassDto
     * @return
     */
    @RequestMapping("jsonShopGoodsClassListByPid")
    @ResponseBody
    public List<ShopGoodsClass> jsonShopGoodsClassListByPid(ShopGoodsClassDto shopGoodsClassDto){
        return iShopGoodsClassService.obtainShopGoodsClassListByPid(shopGoodsClassDto);
    }

    /**
     * 商品分类界面
     * @return
     */
    @RequestMapping("shopGoodsClassList")
    public String shopGoodsClassList(){
        return "Shop/shopgoods/shopGoodsClassList";
    }

    /**
     * 添加商品分类
     * @param shopGoodsClass
     * @return
     */
    @RequestMapping("addGoodsClass")
    @ResponseBody
    public String addGoodsClass(ShopGoodsClass shopGoodsClass){

        if(iShopGoodsClassService.addShopGoods(shopGoodsClass)){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 上传头像
     * @param id
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("uploadImage")
    @ResponseBody
    public String uploadImage(Long id, MultipartFile file, HttpServletRequest request){
        Map<String,String> map = iShopGoodsClassService.uploadPhoto("goodsClassImage",request,file);
        if((String)map.get("isSuccess")=="true"){
            ShopGoodsClass shopGoodsClass = new ShopGoodsClass();
            shopGoodsClass.setGcPic((String)map.get("url"));
            shopGoodsClass.setId(id);
            if(iShopGoodsClassService.updateShopGoods(shopGoodsClass)){
                return "1";
            }else{
                return "-1";
            }
        }else{
            return "-1";
        }
    }

    /**
     * 编辑商品分类
     * @param shopGoodsClass
     * @return
     */
    @RequestMapping("updateGoodsClass")
    @ResponseBody
    public String updateGoodsClass(ShopGoodsClass shopGoodsClass){
        if(iShopGoodsClassService.updateShopGoods(shopGoodsClass)){
            return "1";
        }else{
            return "0";
        }
    }

    @RequestMapping("shopGoodsLeftTree")
    @ResponseBody
    public List<Map<String,String>> shopGoodsLeftTree(String id){
        return iShopGoodsClassService.obtainClassTree(id);
    }
}
