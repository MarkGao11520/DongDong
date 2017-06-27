package com.zrkj.ecp.service.serviceimpl.shoporder;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.dao.shoporder.ShopOrderGoodsMapper;
import com.zrkj.ecp.domain.shoporder.ShopOrderGoods;
import com.zrkj.ecp.dto.shoporder.ShopOrderGoodsDto;
import com.zrkj.ecp.service.shoporder.IShopOrderGoodsService;
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
@Service("iShopOrderGoodsService")
@Scope("prototype")
public class ShopOrderGoodsServiceImpl implements IShopOrderGoodsService{
    @Autowired
    ShopOrderGoodsMapper shopOrderGoodsMapper;

    /**
     * 获取列表
     * @param page
     * @param rows
     * @param shopOrderGoodsDto
     * @return
     */
    @Override
    public PageInfo<ShopOrderGoods> obtianShopOrderGoodsList(int page, int rows, ShopOrderGoodsDto shopOrderGoodsDto) {
        PageHelper.startPage(page,rows);
        dtoHandler(shopOrderGoodsDto);
//       System.out.println(shopOrderGoodsDto);
        List<ShopOrderGoods> resultList = shopOrderGoodsMapper.selectShopOrderGoodsList(shopOrderGoodsDto);
        if(resultHandler(resultList)){
            return new PageInfo<>(resultList);
        }else {
            return new PageInfo<>(Collections.<ShopOrderGoods>emptyList());
        }
    }

    /**
     * 添加订单
     * @param shopOrderGoods
     * @return
     */
    @Override
    public boolean addShopOrderGoods(ShopOrderGoods shopOrderGoods) {
        try {
            if(shopOrderGoodsMapper.insertSelective(shopOrderGoods)>0){
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
    public boolean updateShopOrderGoods(ShopOrderGoods shopOrderGoods) {
        try {
            if(shopOrderGoodsMapper.updateByPrimaryKeySelective(shopOrderGoods)>0){
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
    private boolean resultHandler(List<ShopOrderGoods> resultList){
        if(null != resultList && resultList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询条件模糊化
     */
    private void dtoHandler(ShopOrderGoodsDto shopOrderGoodsDto){
        if(shopOrderGoodsDto.getGoodsName()!=null){
            shopOrderGoodsDto.setGoodsName("%"+shopOrderGoodsDto.getGoodsName()+"%");
        }else{
            shopOrderGoodsDto.setGoodsName("%%");
        }
    }

    private String dataLongToString(Long time){
        Date dt=new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(dt);
    }
}
