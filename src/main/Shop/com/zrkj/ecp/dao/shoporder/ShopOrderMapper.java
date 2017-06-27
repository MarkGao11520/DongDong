package com.zrkj.ecp.dao.shoporder;

import com.zrkj.ecp.domain.shoporder.ShopOrder;
import com.zrkj.ecp.domain.shoporder.ShopOrderWithBLOBs;
import com.zrkj.ecp.dto.shoporder.ShopOrderDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopOrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrderWithBLOBs record);

    int insertSelective(ShopOrderWithBLOBs record);

    ShopOrderWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopOrderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ShopOrderWithBLOBs record);

    int updateByPrimaryKey(ShopOrder record);

    /**
     * 获取订单列表
     * @param shopOrderDto
     * @return
     */
    List<ShopOrderWithBLOBs> selectShopOrderList(@Param("dto")ShopOrderDto shopOrderDto);
}