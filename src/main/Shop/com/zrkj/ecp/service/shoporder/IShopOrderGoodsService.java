package com.zrkj.ecp.service.shoporder;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shoporder.ShopOrderGoods;
import com.zrkj.ecp.dto.shoporder.ShopOrderGoodsDto;

/**
 * Created by gaowenfeng on 2017/4/18.
 */
public interface IShopOrderGoodsService {
    /**
     * 获取订单商品列表
     *
     * @param shopOrderGoodsDto
     * @return
     */
    PageInfo<ShopOrderGoods> obtianShopOrderGoodsList(int page, int rows, ShopOrderGoodsDto shopOrderGoodsDto);

    /**
     * 添加订单商品
     *
     * @return
     */
    boolean addShopOrderGoods(ShopOrderGoods shopOrderGoods);

    /**
     * 编辑订单商品
     */
    boolean updateShopOrderGoods(ShopOrderGoods shopOrderGoods);
}
