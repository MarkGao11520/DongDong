package com.zrkj.ecp.service.position;

import com.zrkj.ecp.domain.position.BasPosition;
import com.zrkj.ecp.dto.position.PositionDto;
import com.zrkj.ecp.dto.position.PpositionDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BasPositionService {
	/**
	 * 添加岗位
	 * @param basPosition
	 */
	public void addPosition(BasPosition basPosition);
	
  
	/**
	 * 查找部门列表
	 * @param positionDto
	 * @return
	 */
	public Map<String,Object> positionList(PositionDto positionDto);
	
	/**
	 * 跟你pid查找list
	 * @param ppositionDto
	 * @return
	 */
	public String selectPositionListByPid(Integer ppositionid, HttpServletRequest request);
	
	/**
	 * 根据pid查找dtolist
	 * @param positionid
	 * @return
	 */
    public List<PpositionDto> selectPositionDtoListByPid(Integer positionid, Integer ppositionid);
    
    /**
     * 根据主键查找
     * @param positionid
     * @return
     */
    public BasPosition selectPositionByPositionid(Integer positionid);
    
    /**
     * 更改
     * @param basPosition
     */
    public void updatePosition(BasPosition basPosition);
    
    /**
     * 删除
     * @param str
     * @return
     */
    public String deletePosition(String str);
    
    /**
     * 根据did查找posdtolist
     * @param did
     * @param posid
     * @return
     */
    public List<PpositionDto> selectPositionDtoList(Integer did, Integer posid);
    
   
}
