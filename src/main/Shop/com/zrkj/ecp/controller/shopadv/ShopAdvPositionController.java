package com.zrkj.ecp.controller.shopadv;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopadv.ShopAdvPosition;
import com.zrkj.ecp.dto.shopadv.ShopAdvPositionDto;
import com.zrkj.ecp.service.shopadv.IShopAdvPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gaowenfeng on 2017/4/12.
 */
@Controller
@Scope("prototype")
@RequestMapping("shopAdvPositionController")
public class ShopAdvPositionController {
    @Autowired
    IShopAdvPosition iShopAdvPosition;

    /**
     * 获取广告位列表
     * **/
    @RequestMapping("jsonShopAdvPositionList")
    @ResponseBody
    public PageInfo<ShopAdvPosition> jsonShopAdbPositionList(int page, int rows, ShopAdvPositionDto shopAdvPositionDto){
        return iShopAdvPosition.obtainShopAdvPosition(page,rows,shopAdvPositionDto);
    }

    /**
     * 返回广告位页面
     * **/
    @RequestMapping("shopAdvPositionPage")
    public String shopAdvPositionPage(){
        return "Shop/shopadv/shopAdvPositionList";
    }

    /**
     * 添加广告位
     * **/
    @RequestMapping("addAdvPositionPage")
    @ResponseBody
    public String addAdvPositionPage(ShopAdvPosition shopAdvPosition){
        if(iShopAdvPosition.addShopAdvPosition(shopAdvPosition)){
            return "1";
        }else{
            return "0";
        }
    }

    /**
     * 编辑广告位
     * **/
    @RequestMapping("updateAdvPositionPage")
    @ResponseBody
    public String updateAdvPositionPage(ShopAdvPosition shopAdvPosition){
        if(iShopAdvPosition.updateShopAdvPosition(shopAdvPosition)){
            return "1";
        }else{
            return "0";
        }
    }
}
