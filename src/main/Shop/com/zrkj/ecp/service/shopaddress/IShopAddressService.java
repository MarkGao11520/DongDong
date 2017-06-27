package com.zrkj.ecp.service.shopaddress;

import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.domain.shopaddress.ShopAddress;
import com.zrkj.ecp.dto.shopaddress.ShopAddressDto;

/**
 * Created by gaowenfeng on 2017/4/12.
 */
public interface IShopAddressService {
    PageInfo<ShopAddress> obtainShopAddressList(int page, int rows, ShopAddressDto shopAddressDto);
}
