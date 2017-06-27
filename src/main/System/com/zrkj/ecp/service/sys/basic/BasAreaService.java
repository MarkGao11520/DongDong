

package com.zrkj.ecp.service.sys.basic;

import com.zrkj.ecp.domain.sys.basic.BasArea;
import com.zrkj.ecp.dto.sys.basic.AreaDto;
import com.zrkj.ecp.dto.sys.basic.PAreaDto;

import java.util.List;
import java.util.Map;

public interface BasAreaService {
    public BasArea testArea();

    /**
     * 添加地区
     * @param basArea
     */
    public void addArea(BasArea basArea);

    /**
     * 查找最大ID
     * @return
     */
    public int selectMaxAId();

    /**
     * 获取父类地区
     * @return
     */
    public List<PAreaDto> selectPAreaList();
    
    /**
     * 根据Pid获取父类地区
     * @return
     */
    public List<PAreaDto> selectPAreaListByPid(Integer aid);

    /**
     * 获取地区列表
     * @param areaDto
     * @return
     */
    public Map<String,Object> selectAreaList(AreaDto areaDto);

    /**
     * 根据aid获取BasArea
     * @param aid
     * @return
     */
    public BasArea selectAreaByAid(Integer aid);
    
    /**
     * 更改Area
     * @param basArea
     */
    public void updateArea(BasArea basArea);
    
    /**
     * 根据pid查找list
     * @param basArea
     * @return
     */
    public String selectAreaByPid(Integer aid);
    
    public List<PAreaDto> selectAllPAreaListByPid(Integer aid);
    
    /**
     * 删除返回
     * @param str
     * @return
     */
    public String deleteArea(String str);
}
