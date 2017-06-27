package com.zrkj.ecp.dao.expresssignphoto;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expresssignphoto.ExpressSignPhoto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface ExpressSignPhotoMapper {
    int deleteByPrimaryKey(Integer phid);

    int insert(ExpressSignPhoto record);

    int insertSelective(ExpressSignPhoto record);

    ExpressSignPhoto selectByPrimaryKey(Integer phid);

    int updateByPrimaryKeySelective(ExpressSignPhoto record);

    int updateByPrimaryKey(ExpressSignPhoto record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressSignPhoto> selectExpressSignPhotoList(@Param("expressDto") ExpressDto expressDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressSignPhotoCount(@Param("expressDto") ExpressDto expressDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}