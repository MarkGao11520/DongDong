package com.zrkj.ecp.dao.shopadv;

import com.zrkj.ecp.domain.shopadv.ShopAdvPosition;
import com.zrkj.ecp.dto.shopadv.ShopAdvPositionDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopAdvPositionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopAdvPosition record);

    int insertSelective(ShopAdvPosition record);

    ShopAdvPosition selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopAdvPosition record);

    int updateByPrimaryKey(ShopAdvPosition record);

    /**
     * 获取广告位列表
     * @param shopAdvPositionDto
     * @return
     */
    List<ShopAdvPosition> selectShopAdvPositionList(@Param("dto")ShopAdvPositionDto shopAdvPositionDto);
}