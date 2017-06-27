package com.zrkj.ecp.controller.shoporder;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shoporder.ShopOrderWithBLOBs;
import com.zrkj.ecp.dto.shoporder.ShopOrderDto;
import com.zrkj.ecp.service.shoporder.IShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by gaowenfeng on 2017/4/18.
 */
@Controller
@RequestMapping("shopOrderController")
@Scope("prototype")
public class ShopOrderController {
    @Autowired
    IShopOrderService iShopOrderService;

    /**
     * 获取订单列表
     * @param page
     * @param rows
     * @param shopOrderDto
     * @return
     */
    @RequestMapping("jsonShopOrderList")
    @ResponseBody
    public PageInfo<ShopOrderWithBLOBs> jsonShopOrderList(int page, int rows, ShopOrderDto shopOrderDto){
        return iShopOrderService.obtianShopOrderList(page,rows,shopOrderDto);
    }

    /**
     * 订单界面
     * @return
     */
    @RequestMapping("shopOrderPage")
    public String shopOrderPage(){
        return "Shop/shoporder/shopOrderList";
    }
}
