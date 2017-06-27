package com.zrkj.ecp.service.serviceimpl.shopaddress;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.dao.shopaddress.ShopAddressMapper;
import com.zrkj.ecp.domain.shopaddress.ShopAddress;
import com.zrkj.ecp.dto.shopaddress.ShopAddressDto;
import com.zrkj.ecp.service.shopaddress.IShopAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/4/12.
 */
@Service("iShopAddressService")
@Scope("prototype")
public class ShopAddressService implements IShopAddressService{
    @Autowired
    ShopAddressMapper shopAddressMapper;

    /**
     * 获取商城地址
     * @param page
     * @param rows
     * @param shopAddressDto
     * @return
     */
    @Override
    public PageInfo<ShopAddress> obtainShopAddressList(int page, int rows, ShopAddressDto shopAddressDto) {
        PageHelper.startPage(page,rows);
        dtoHandler(shopAddressDto);
        List<ShopAddress> resultList = shopAddressMapper.selectShopAddressList(shopAddressDto);
        if(resultHandler(resultList)){
            return new PageInfo<>(resultList);
        }else{
            return new PageInfo<>(Collections.<ShopAddress>emptyList());
        }
    }

    /**
     * 返回值验证
     * @param resultList
     * @return
     */
    private boolean resultHandler(List<ShopAddress> resultList){
        if(null != resultList && resultList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询条件模糊化
     * @param shopAddressDto
     */
    private void dtoHandler(ShopAddressDto shopAddressDto){
        if(shopAddressDto.getTrueName()==null){
            shopAddressDto.setTrueName("%%");
        }else{
            shopAddressDto.setTrueName("%"+shopAddressDto.getTrueName()+"%");
        }
    }


//    private void resultSort(byte sort, int page, int rows) {
//        if (sort == 0) {
//            PageHelper.startPage(page, rows, "go.priority desc");
//        } else if (sort == 1) {
//            PageHelper.startPage(page, rows, "go.sales desc");
//        } else if (sort == 2) {
//            PageHelper.startPage(page, rows, "g.prime_cost");
//        }
//    }

}
