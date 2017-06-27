package com.zrkj.ecp.dao.log;

import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.dto.log.LogDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BasLogMapper {
    int deleteByPrimaryKey(Integer lid);

    int insert(BasLog record);

    int insertSelective(BasLog record);

    BasLog selectByPrimaryKey(Integer lid);

    int updateByPrimaryKeySelective(BasLog record);

    int updateByPrimaryKey(BasLog record);
    
    /**
     * 查找最大id
     * @return
     */
    Integer selectMaxAid();
    
    /**
     * 获取日志列表
     * @param logDto
     * @return
     */
    List<BasLog> selectLogList(LogDto logDto);
    
    /**
     * 获取日志列表总数
     * @param logDto
     * @return
     */
    Integer selectLogCount(LogDto logDto);

    List select(@Param("sqlStr")String str);
}