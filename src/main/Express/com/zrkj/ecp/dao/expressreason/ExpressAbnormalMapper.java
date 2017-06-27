package com.zrkj.ecp.dao.expressreason;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expressreason.ExpressAbnormal;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface ExpressAbnormalMapper {
    int deleteByPrimaryKey(Integer billid);

    int insert(ExpressAbnormal record);

    int insertSelective(ExpressAbnormal record);

    ExpressAbnormal selectByPrimaryKey(Integer billid);

    int updateByPrimaryKeySelective(ExpressAbnormal record);

    int updateByPrimaryKey(ExpressAbnormal record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressAbnormal> selectExpressAbnormalList(@Param("expressDto") ExpressDto expressDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressAbnormalCount(@Param("expressDto") ExpressDto expressDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}