package com.zrkj.ecp.dao.position;

import com.zrkj.ecp.domain.position.BasPosition;
import com.zrkj.ecp.dto.position.PositionDto;
import com.zrkj.ecp.dto.position.PpositionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasPositionMapper {
    int deleteByPrimaryKey(Integer positionid);

    int insert(BasPosition record);

    int insertSelective(BasPosition record);

    BasPosition selectByPrimaryKey(Integer positionid);

    int updateByPrimaryKeySelective(BasPosition record);

    int updateByPrimaryKey(BasPosition record);
    
    /**
     * 最大id
     * @return
     */
    int selectMaxid();
    
    /**
     * 获取岗位列表
     * @param positionDto
     * @return
     */
    List<BasPosition> selectPositionList(PositionDto positionDto);
    
    /**
     * 获取记录总数
     * @param positionDto
     * @return
     */
    Integer selectPositionCount(PositionDto positionDto);
    
    /**
     * 根据父id查找list
     * @param ppositionid
     * @return
     */
    List<BasPosition> selectPositionListByPid(Integer ppositionid);
    
    /**
     * 根据父id查找listdto
     * @param ppositionid
     * @return
     */
    List<PpositionDto>  selectPpositionDtoListByPid(Integer ppositionid);
    
    /**
     * 根据did查了dtolist
     * @param ppositionid
     * @return
     */
    List<PpositionDto> selectPositionDtoListByDid(Integer did);
    
    /**
     * 根据id查找分级结构
     * @param positionid
     * @return
     */
    Integer selectPositionOrderById(Integer positionid);
    
    /**
     * 根据id查找是否有子关联
     * @param positionid
     * @return
     */
    Integer selectCountByPid(Integer positionid);
    Integer selectCountByMRFK(Integer positionid);
}