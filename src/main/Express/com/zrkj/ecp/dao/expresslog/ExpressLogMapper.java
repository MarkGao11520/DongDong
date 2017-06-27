package com.zrkj.ecp.dao.expresslog;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface ExpressLogMapper {
    int deleteByPrimaryKey(Integer logid);

    int insert(ExpressLog record);

    int insertSelective(ExpressLog record);

    ExpressLog selectByPrimaryKey(Integer logid);

    int updateByPrimaryKeySelective(ExpressLog record);

    int updateByPrimaryKey(ExpressLog record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressLog> selectExpressLogList(@Param("expressDto") ExpressDto expressDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressLogCount(@Param("expressDto") ExpressDto expressDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}