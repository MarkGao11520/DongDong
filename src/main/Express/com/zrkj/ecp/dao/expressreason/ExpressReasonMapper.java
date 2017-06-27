package com.zrkj.ecp.dao.expressreason;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expressreason.ExpressReason;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface ExpressReasonMapper {
    int deleteByPrimaryKey(Integer reasonid);

    int insert(ExpressReason record);

    int insertSelective(ExpressReason record);

    ExpressReason selectByPrimaryKey(Integer reasonid);

    int updateByPrimaryKeySelective(ExpressReason record);

    int updateByPrimaryKey(ExpressReason record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressReason> selectExpressReasonList(@Param("expressDto") ExpressDto expressDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressReasonCount(@Param("expressDto") ExpressDto expressDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}