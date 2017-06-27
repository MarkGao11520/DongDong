package com.zrkj.ecp.dao.shopgoods;

import com.zrkj.ecp.domain.shopgoods.ShopGoodsClass;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsClassDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopGoodsClassMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopGoodsClass record);

    int insertSelective(ShopGoodsClass record);

    ShopGoodsClass selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopGoodsClass record);

    int updateByPrimaryKey(ShopGoodsClass record);

    /**
     * 获取顶级商品分类列表
     * @param shopGoodsClassDto
     * @return
     */
    List<ShopGoodsClass> selectTopShopGoodsClassList(@Param("dto")ShopGoodsClassDto shopGoodsClassDto);

    /**
     * 根据父级id获取商品类别
     * @param shopGoodsClassDto
     * @return
     */
    List<ShopGoodsClass> selectShopGoodsClassListByPid(@Param("dto")ShopGoodsClassDto shopGoodsClassDto);

    /**
     * 根据gcid获取分类名称
     * @param gcId
     * @return
     */
    String selectGcNameByGcId(Integer gcId);
}