package com.zrkj.ecp.dao.expresstracing;

import java.util.List;

import com.zrkj.ecp.dto.expresstracing.ExpressTracingDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface ExpressTracingMapper {
    int deleteByPrimaryKey(Integer tracid);

    int insert(ExpressTracing record);

    int insertSelective(ExpressTracing record);

    ExpressTracing selectByPrimaryKey(Integer tracid);

    int updateByPrimaryKeySelective(ExpressTracing record);

    int updateByPrimaryKey(ExpressTracing record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressTracing> selectExpressTracingList(@Param("expressDto") ExpressDto expressDto, @Param("expressTracingDto") ExpressTracingDto expressTracingDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressTracingCount(@Param("expressDto") ExpressDto expressDto,@Param("expressTracingDto") ExpressTracingDto expressTracingDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}