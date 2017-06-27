package com.zrkj.ecp.dao.shopaddress;

import com.zrkj.ecp.domain.shopaddress.ShopAddress;
import com.zrkj.ecp.dto.shopaddress.ShopAddressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShopAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopAddress record);

    int insertSelective(ShopAddress record);

    ShopAddress selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopAddress record);

    int updateByPrimaryKey(ShopAddress record);

    /**
     * 获取商城地址列表
     * @param shopAddressDto
     * @return
     */
    List<ShopAddress> selectShopAddressList(@Param("shopAddressDto") ShopAddressDto shopAddressDto);
}