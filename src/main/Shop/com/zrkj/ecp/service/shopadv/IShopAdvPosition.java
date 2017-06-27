package com.zrkj.ecp.service.shopadv;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopadv.ShopAdvPosition;
import com.zrkj.ecp.dto.shopadv.ShopAdvPositionDto;

/**
 * Created by gaowenfeng on 2017/4/12.
 */
public interface IShopAdvPosition {
    PageInfo<ShopAdvPosition> obtainShopAdvPosition(int page, int rows, ShopAdvPositionDto shopAdvPositionDto);

    boolean addShopAdvPosition(ShopAdvPosition shopAdvPosition);

    boolean updateShopAdvPosition(ShopAdvPosition shopAdvPosition);
}
