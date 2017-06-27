package com.zrkj.ecp.dao.expressfdex;

import java.util.List;

import com.zrkj.ecp.domain.expressfdex.ExpressSendFee;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expressfdex.ExpressSendFeeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpressSendFeeMapper {
    int deleteByPrimaryKey(Integer sendid);

    int insert(ExpressSendFee record);

    int insertSelective(ExpressSendFee record);

    ExpressSendFee selectByPrimaryKey(Integer sendid);

    int updateByPrimaryKeySelective(ExpressSendFee record);

    int updateByPrimaryKey(ExpressSendFee record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressSendFee> selectExpressSendFeeList(@Param("expressDto") ExpressDto expressDto,@Param("expressSendFeeDto") ExpressSendFeeDto expressSendFeeDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressSendFeeCount(@Param("expressDto") ExpressDto expressDto,@Param("expressSendFeeDto") ExpressSendFeeDto expressSendFeeDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}