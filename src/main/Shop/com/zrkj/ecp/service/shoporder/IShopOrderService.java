package com.zrkj.ecp.service.shoporder;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shoporder.ShopOrder;
import com.zrkj.ecp.domain.shoporder.ShopOrderWithBLOBs;
import com.zrkj.ecp.dto.shoporder.ShopOrderDto;

/**
 * Created by gaowenfeng on 2017/4/18.
 */
public interface IShopOrderService {
    /**
     * 获取订单列表
     *
     * @param shopOrderDto
     * @return
     */
    PageInfo<ShopOrderWithBLOBs> obtianShopOrderList(int page, int rows, ShopOrderDto shopOrderDto);

    /**
     * 添加订单
     *
     * @return
     */
    boolean addShopOrder(ShopOrderWithBLOBs shopOrder);

    /**
     * 编辑订单
     */
    boolean updateShopOrder(ShopOrderWithBLOBs shopOrder);
}
