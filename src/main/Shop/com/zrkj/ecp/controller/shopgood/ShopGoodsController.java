package com.zrkj.ecp.controller.shopgood;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsClass;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsWithBLOBs;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsDto;
import com.zrkj.ecp.service.shopgoods.IShopGoodsClassService;
import com.zrkj.ecp.service.shopgoods.IShopGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.jvm.hotspot.debugger.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/4/16.
 */
@Controller
@Scope("prototype")
@RequestMapping("shopGoodsController")
public class ShopGoodsController {
    @Autowired
    IShopGoodsService iShopGoodsService;
    @Autowired
    IShopGoodsClassService iShopGoodsClassService;

    @RequestMapping("/shopGoodsMain")
    public String shopGoodsMain(){
        return "Shop/shopgoods/shopGoodsMain";
    }

    @RequestMapping("/shopGoodsLeft")
    public String shopGoodsLeft(){
        return "Shop/shopgoods/shopGoodsLeft";
    }

    @RequestMapping("/shopGoodsRight")
    public ModelAndView shopGoodsRight(ModelAndView mv,Integer gcid){
        mv.setViewName("Shop/shopgoods/shopGoodsRight");
        mv.addObject("gcid",gcid);
        return mv;
    }

    @RequestMapping("/jsonShopGoodsList")
    @ResponseBody
    public PageInfo<ShopGoodsWithBLOBs> jsonShopGoodsList(int page,int rows,ShopGoodsDto shopGoodsDto){
        return iShopGoodsService.obtianShopGoodsList(page,rows,shopGoodsDto);
    }

    @RequestMapping("/addShopGoods")
    @ResponseBody
    public String addShopGoods(ShopGoodsWithBLOBs shopGoodsWithBLOBs){
        if(iShopGoodsService.addShopGoods(shopGoodsWithBLOBs)){
            return "1";
        }else{
            return "0";
        }
    }

    @RequestMapping("/updateShopGoods")
    @ResponseBody
    public String updateShopGoods(ShopGoodsWithBLOBs shopGoodsWithBLOBs){
        if(iShopGoodsService.updateShopGoods(shopGoodsWithBLOBs)){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 上传图片
     * @param id
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("uploadImage")
    @ResponseBody
    public String uploadImage(Long id, MultipartFile file, HttpServletRequest request){
        Map<String,String> map = iShopGoodsClassService.uploadPhoto("goodsImage",request,file);
        if((String)map.get("isSuccess")=="true"){
            ShopGoodsWithBLOBs shopGoodsWithBLOBs = new ShopGoodsWithBLOBs();
            shopGoodsWithBLOBs.setGoodsImage((String)map.get("url"));
            shopGoodsWithBLOBs.setId(id);
            if(iShopGoodsService.updateShopGoods(shopGoodsWithBLOBs)){
                return "1";
            }else{
                return "-1";
            }
        }else{
            return "-1";
        }
    }




}
