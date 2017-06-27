package com.zrkj.ecp.service.expresssignphoto;

import java.util.Map;

import com.zrkj.ecp.domain.expresssignphoto.ExpressSignPhoto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

public interface IExpressSignPhoto {
	/**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssSignPhotoList(ExpressDto expressDto);

    /**
     * 唯一查询
     * @param SignPhotoId
     * @return
     */
    public ExpressSignPhoto obtainExpressSignPhotoById(Integer SignPhotoId);

    /**
     * 增加
     * @param expressSignPhoto
     * @return
     */
    public boolean addExpressSignPhoto(ExpressSignPhoto expressSignPhoto);

    /**
     * 修改
     * @param expressSignPhoto
     * @return
     */
    public boolean updateExpressSignPhoto(ExpressSignPhoto expressSignPhoto);

    /**
     * 删除
     * @param SignPhotoIdStr
     * @return
     */
    public boolean deleteExpressSignPhoto(String SignPhotoIdStr);

}
