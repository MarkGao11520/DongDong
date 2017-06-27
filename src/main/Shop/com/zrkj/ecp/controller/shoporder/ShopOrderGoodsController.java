package com.zrkj.ecp.controller.shoporder;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shoporder.ShopOrderGoods;
import com.zrkj.ecp.dto.shoporder.ShopOrderGoodsDto;
import com.zrkj.ecp.service.shoporder.IShopOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by gaowenfeng on 2017/4/18.
 */
@Controller
@RequestMapping("shopOrderGoodsController")
@Scope("prototype")
public class ShopOrderGoodsController {
    @Autowired
    IShopOrderGoodsService iShopOrderGoodsService;

    @RequestMapping("jsonShopOrderGoodsList")
    @ResponseBody
    public PageInfo<ShopOrderGoods> jsonShopOrderGoodsList(int page, int rows, ShopOrderGoodsDto shopOrderGoodsDto){
        return iShopOrderGoodsService.obtianShopOrderGoodsList(page,rows,shopOrderGoodsDto);
    }

    /**
     * 订单商品界面
     * @return
     */
    @RequestMapping("shopOrderPage")
    public ModelAndView shopOrderPage(ModelAndView mv, Integer orderId){
        mv.addObject("orderId",orderId);
        mv.setViewName("Shop/shoporder/shopOrderGoodsList");
        return mv;
    }
}
