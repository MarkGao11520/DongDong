package com.zrkj.ecp.service.serviceimpl.shopgoods;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.dao.shopgoods.ShopGoodsClassMapper;
import com.zrkj.ecp.dao.shopgoods.ShopGoodsMapper;
import com.zrkj.ecp.domain.shopgoods.ShopGoods;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsClass;
import com.zrkj.ecp.domain.shopgoods.ShopGoodsWithBLOBs;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsClassDto;
import com.zrkj.ecp.dto.shopgoods.ShopGoodsDto;
import com.zrkj.ecp.service.shopgoods.IShopGoodsService;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by gaowenfeng on 2017/4/16.
 */
@Service("iShopGoodsService")
@Scope("prototype")
public class ShopGoodsServiceImpl implements IShopGoodsService{
    @Autowired
    ShopGoodsMapper shopGoodsMapper;
    @Autowired
    ShopGoodsClassMapper shopGoodsClassMapper;

    /**
     * 获取列表
     * @param page
     * @param rows
     * @param shopGoodsDto
     * @return
     */
    @Override
    public PageInfo<ShopGoodsWithBLOBs> obtianShopGoodsList(int page,int rows,ShopGoodsDto shopGoodsDto) {
        PageHelper.startPage(page,rows);
        dtoHandler(shopGoodsDto);
        List<ShopGoodsWithBLOBs> resultList = shopGoodsMapper.selectShopGoodsListByGcId(shopGoodsDto);
        if(resultHandler(resultList)){
            return new PageInfo<>(resultList);
        }else {
            return new PageInfo<>(Collections.<ShopGoodsWithBLOBs>emptyList());
        }
    }

    /**
     * 添加商品
     * @param shopGoods
     * @return
     */
    @Override
    public boolean addShopGoods(ShopGoodsWithBLOBs shopGoods) {
        try {
            shopGoods.setGcName(shopGoodsClassMapper.selectGcNameByGcId(Integer.parseInt(shopGoods.getGcId().toString())));
            shopGoods.setCreateTime(new Date().getTime());
            if(shopGoodsMapper.insertSelective(shopGoods)>0){
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
     * 编辑商品
     * @param shopGoods
     * @return
     */
    @Override
    public boolean updateShopGoods(ShopGoodsWithBLOBs shopGoods) {
        try {

            shopGoods.setUpdateTime(new Date().getTime());
            if(shopGoodsMapper.updateByPrimaryKeySelective(shopGoods)>0){
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
    private boolean resultHandler(List<ShopGoodsWithBLOBs> resultList){
        if(null != resultList && resultList.size()>0){
            for(ShopGoodsWithBLOBs shopGoodsWithBLOBs:resultList){
                if(shopGoodsWithBLOBs.getCreateTime()!=null){
                    shopGoodsWithBLOBs.setCreate(dataLongToString(shopGoodsWithBLOBs.getCreateTime()));
                }
                if(shopGoodsWithBLOBs.getUpdateTime()!=null){
                    shopGoodsWithBLOBs.setUpdate(dataLongToString(shopGoodsWithBLOBs.getUpdateTime()));
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
    private void dtoHandler(ShopGoodsDto shopGoodsDto){
        if(shopGoodsDto.getGoodsName()!=null){
            shopGoodsDto.setGoodsName("%"+shopGoodsDto.getGoodsName()+"%");
        }else{
            shopGoodsDto.setGoodsName("%%");
        }
    }

    private String dataLongToString(Long time){
        Date dt=new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        return sdf.format(dt);
    }
}
