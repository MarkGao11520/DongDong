package com.zrkj.ecp.service.shopgoods;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopgoods.ShopGoods;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsWithBLOBs;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsDto;

/**
 * Created by gaowenfeng on 2017/4/16.
 */
public interface IShopGoodsService {
    /**
     * 获取商品列表
     * @param shopGoodsDto
     * @return
     */
    PageInfo<ShopGoodsWithBLOBs> obtianShopGoodsList(int page, int rows, ShopGoodsDto shopGoodsDto);

    /**
     * 添加商品
     * @param shopGoods
     * @return
     */
    boolean addShopGoods(ShopGoodsWithBLOBs shopGoods);

    /**
     * 编辑商品
     */
    boolean updateShopGoods(ShopGoodsWithBLOBs shopGoods);
}
