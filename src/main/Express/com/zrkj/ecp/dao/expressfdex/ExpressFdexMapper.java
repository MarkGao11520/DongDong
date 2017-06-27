package com.zrkj.ecp.dao.expressfdex;

import java.util.List;

import com.zrkj.ecp.domain.expressfdex.ExpressFdex;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expressfdex.ExpressFdexDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ExpressFdexMapper {
    int deleteByPrimaryKey(Integer fdid);

    int insert(ExpressFdex record);

    int insertSelective(ExpressFdex record);

    ExpressFdex selectByPrimaryKey(Integer fdid);

    int updateByPrimaryKeySelective(ExpressFdex record);

    int updateByPrimaryKey(ExpressFdex record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressFdex> selectExpressFdexList(@Param("expressDto") ExpressDto expressDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressFdexCount(@Param("expressDto") ExpressDto expressDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();

    /**
     * 查找快递公司列表(名称,id)
     * @return
     */
    List<ExpressFdexDto>  selectExpressFdexDtoList();
}