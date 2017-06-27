package com.zrkj.ecp.dao.sys.basic;

import com.zrkj.ecp.domain.sys.basic.BasArea;
import com.zrkj.ecp.dto.sys.basic.AreaDto;
import com.zrkj.ecp.dto.sys.basic.PAreaDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasAreaMapper {
    int deleteByPrimaryKey(Integer aid);

    int insert(BasArea record);

    int insertSelective(BasArea record);

    BasArea selectByPrimaryKey(Integer aid);

    int updateByPrimaryKeySelective(BasArea record);

    int updateByPrimaryKey(BasArea record);

    /**
     * 查找最大id
     * @return
     */
    int selectMaxAid();

    /**
     * 获取父类地区
     * @return
     */
    List<PAreaDto> selectPareaList();
    
    /**
     * 根据pid获取父类地区
     * @return
     */
    List<PAreaDto> selectPareaListByPid(Integer aid);

    /**
     * 获取地区列表
     * @param areaDto
     * @return
     */
    List<BasArea> selectAreaList(AreaDto areaDto);

    /**
     * 获取总记录数
     * @return
     */
    Integer selectAreaCount(AreaDto areaDto);
    
    /**
     * 根据id获取名称
     * @param aid
     * @return
     */
    String selectAnameByaid(Integer aid);
    
    /**
     * 根据pid查找list
     * @param aid
     * @return
     */
    List<BasArea> selectAreaListbypid(Integer aid);
    
    /**
     * 根据pid查找list
     * @param aid
     * @return
     */
    List<PAreaDto>  selectAllPareaListByPid(Integer aid);
    
    /**
     * 查找是否有子id
     * @param aid
     * @return
     */
    Integer selectCountByPid(Integer aid);
    
    Integer selectCountByFK(Integer aid);
    
    /**
     * 根据pid获取地区类型
     * @param pid
     * @return
     */
    Integer  selectZoneTypeByPid(Integer pid);
    
}