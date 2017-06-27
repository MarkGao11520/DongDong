package com.zrkj.ecp.dao.org.parame;

import com.zrkj.ecp.domain.org.parame.BasParam;
import com.zrkj.ecp.dto.org.parame.ParameDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasParamMapper {
    int deleteByPrimaryKey(Integer paraid);

    int insert(BasParam record);

    int insertSelective(BasParam record);

    BasParam selectByPrimaryKey(Integer paraid);

    int updateByPrimaryKeySelective(BasParam record);

    int updateByPrimaryKey(BasParam record);
    
    /**
     * 获取最大id
     * @return
     */
    Integer selectMaxAid();
    
    /**
     * 选择一级
     * @param parameDto
     * @return
     */
    List<BasParam> selectparameList(ParameDto parameDto);
    
    /**
     * 根据pid获取
     * @param paraId
     * @return
     */
    List<BasParam> selectparameListByPid(Integer paraid);
    
    /**
     * 根据id查找type
     * @param paraid
     * @return
     */
    Integer selectTypeByid(Integer paraid);
    
    /**
     * 获取总记录数
     * @param parameDto
     * @return
     */
    Integer selectparameCount(ParameDto parameDto);
    
    /**
     * 查看是否有子关联
     * @param paraid
     * @return
     */
    Integer  selectCountByPid(Integer paraid);

    /**
     * 根据cid查找学校网点
     * @param cid
     * @return
     */
    List<BasParam> selectparameListBycid(Integer cid);
    
}