package com.zrkj.ecp.service.serviceimpl.shoporder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.dao.shoporder.ShopOrderMapper;
import com.zrkj.ecp.domain.shoporder.ShopOrderWithBLOBs;
import com.zrkj.ecp.dto.shoporder.ShopOrderDto;
import com.zrkj.ecp.service.shoporder.IShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by gaowenfeng on 2017/4/18.
 */
@Service("iShopOrderService")
@Scope("prototype")
public class ShopOrderServiceImpl implements IShopOrderService{
    @Autowired
    ShopOrderMapper shopOrderMapper;

    /**
     * 获取列表
     * @param page
     * @param rows
     * @param shopOrderDto
     * @return
     */
    @Override
    public PageInfo<ShopOrderWithBLOBs> obtianShopOrderList(int page, int rows, ShopOrderDto shopOrderDto) {
        PageHelper.startPage(page,rows);
        dtoHandler(shopOrderDto);
//        System.out.println(shopOrderDto);
        List<ShopOrderWithBLOBs> resultList = shopOrderMapper.selectShopOrderList(shopOrderDto);
        if(resultHandler(resultList)){
            return new PageInfo<>(resultList);
        }else {
            return new PageInfo<>(Collections.<ShopOrderWithBLOBs>emptyList());
        }
    }

    /**
     * 添加订单
     * @param shopOrder
     * @return
     */
    @Override
    public boolean addShopOrder(ShopOrderWithBLOBs shopOrder) {
        try {
            shopOrder.setCreateTime(new Date().getTime());
            if(shopOrderMapper.insertSelective(shopOrder)>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 编辑订单
     * @return
     */
    @Override
    public boolean updateShopOrder(ShopOrderWithBLOBs shopOrder) {
        try {
            if(shopOrderMapper.updateByPrimaryKeySelective(shopOrder)>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断返回值是否为空
     * @param resultList
     * @return
     */
    private boolean resultHandler(List<ShopOrderWithBLOBs> resultList){
        if(null != resultList && resultList.size()>0){
            for(ShopOrderWithBLOBs shopOrderWithBLOBs:resultList){
                if(shopOrderWithBLOBs.getCreateTime()!=null){
                    shopOrderWithBLOBs.setCreate(dataLongToString(shopOrderWithBLOBs.getCreateTime()));
                }
                if(shopOrderWithBLOBs.getFinnshedTime()!=null){
                    shopOrderWithBLOBs.setFinished(dataLongToString(shopOrderWithBLOBs.getFinnshedTime()));
                }
                if(shopOrderWithBLOBs.getBalanceTime()!=null){
                    shopOrderWithBLOBs.setBalance(dataLongToString(shopOrderWithBLOBs.getBalanceTime()));
                }
                if(shopOrderWithBLOBs.getPaymentTime()!=null){
                    shopOrderWithBLOBs.setPayment(dataLongToString(shopOrderWithBLOBs.getPaymentTime()));
                }
                if(shopOrderWithBLOBs.getShippingTime()!=null){
                    shopOrderWithBLOBs.setShipping(dataLongToString(shopOrderWithBLOBs.getShippingTime()));
                }
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询条件模糊化
     */
    private void dtoHandler(ShopOrderDto ShopOrderDto){
        if(ShopOrderDto.getOrderSn()!=null){
            ShopOrderDto.setOrderSn("%"+ShopOrderDto.getOrderSn()+"%");
        }else{
            ShopOrderDto.setOrderSn("%%");
        }
    }

    private String dataLongToString(Long time){
        Date dt=new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(dt);
    }
}
