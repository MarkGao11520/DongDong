package com.zrkj.ecp.dao.shopgoods;

import com.zrkj.ecp.domain.shopgoods.ShopGoods;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsWithBLOBs;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopGoodsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopGoodsWithBLOBs record);

    int insertSelective(ShopGoodsWithBLOBs record);

    ShopGoodsWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ShopGoodsWithBLOBs record);

    int updateByPrimaryKey(ShopGoods record);

    /**
     * 获取商品列表
     * @param shopGoodsDto
     * @return
     */
    List<ShopGoodsWithBLOBs> selectShopGoodsListByGcId(@Param("dto") ShopGoodsDto shopGoodsDto);


}