package com.zrkj.ecp.dao.shopadv;

import com.zrkj.ecp.domain.shopadv.ShopAdv;

public interface ShopAdvMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopAdv record);

    int insertSelective(ShopAdv record);

    ShopAdv selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopAdv record);

    int updateByPrimaryKey(ShopAdv record);
}