package com.zrkj.ecp.service.serviceimpl.shopadv;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zrkj.ecp.dao.shopadv.ShopAdvPositionMapper;
import com.zrkj.ecp.domain.shopadv.ShopAdvPosition;
import com.zrkj.ecp.dto.shopadv.ShopAdvPositionDto;
import com.zrkj.ecp.service.shopadv.IShopAdvPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/4/12.
 */
@Service("iShopAdvPosition")
@Scope("prototype")
public class ShopAdvPositionImpl implements IShopAdvPosition{
    @Autowired
    ShopAdvPositionMapper shopAdvPositionMapper;

    /**
     * 获取广告位列表
     * @param page
     * @param rows
     * @param shopAdvPositionDto
     * @return
     */
    @Override
    public PageInfo<ShopAdvPosition> obtainShopAdvPosition(int page, int rows, ShopAdvPositionDto shopAdvPositionDto) {
        PageHelper.startPage(page,rows);
        dtoHandler(shopAdvPositionDto);
        List<ShopAdvPosition> resultList = shopAdvPositionMapper.selectShopAdvPositionList(shopAdvPositionDto);
        if(resultHandler(resultList)){
            return new PageInfo<>(resultList);
        }
        return new PageInfo<>(Collections.<ShopAdvPosition>emptyList());
    }

    /**
     * 添加广告位
     * @param shopAdvPosition
     * @return
     */
    @Override
    public boolean addShopAdvPosition(ShopAdvPosition shopAdvPosition) {
        try {
            if(shopAdvPositionMapper.insertSelective(shopAdvPosition)>0) {
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
     * 编辑广告位
     * @param shopAdvPosition
     * @return
     */
    @Override
    public boolean updateShopAdvPosition(ShopAdvPosition shopAdvPosition) {
        try {
            if(shopAdvPositionMapper.updateByPrimaryKeySelective(shopAdvPosition)>0) {
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
    private boolean resultHandler(List<ShopAdvPosition> resultList){
        if(null != resultList && resultList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 查询条件模糊化
     * @param shopAdvPositionDto
     */
    private void dtoHandler(ShopAdvPositionDto shopAdvPositionDto){
        if(shopAdvPositionDto.getApName()!=null){
            shopAdvPositionDto.setApName("%"+shopAdvPositionDto.getApName()+"%");
        }else{
            shopAdvPositionDto.setApName("%%");
        }
    }
}
