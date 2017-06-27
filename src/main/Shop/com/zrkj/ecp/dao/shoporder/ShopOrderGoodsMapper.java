package com.zrkj.ecp.dao.shoporder;

import com.zrkj.ecp.domain.shoporder.ShopOrderGoods;
import com.zrkj.ecp.dto.shoporder.ShopOrderGoodsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopOrderGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopOrderGoods record);

    int insertSelective(ShopOrderGoods record);

    ShopOrderGoods selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopOrderGoods record);

    int updateByPrimaryKey(ShopOrderGoods record);

    /**
     * 获取订单商品列表
     * @param shopOrderGoodsDto
     * @return
     */
    List<ShopOrderGoods> selectShopOrderGoodsList(@Param("dto") ShopOrderGoodsDto shopOrderGoodsDto);
}