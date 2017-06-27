package com.zrkj.ecp.service.org.parame;

import com.zrkj.ecp.domain.org.parame.BasParam;
import com.zrkj.ecp.dto.org.parame.ParameDto;

import java.util.List;
import java.util.Map;

public interface BasParameService {
	/**
	 * 获取一级列表
	 * @param parameDto
	 * @return
	 */
	public Map<String,Object> selectFirstParameList(ParameDto parameDto);
	
	/**
	 * 根据paraid获取列表
	 * @param paraid
	 * @return
	 */
	public String selectParamListById(Integer paraid);
	
	/**
	 * 添加
	 * @param basParam
	 */
	public void addParame(BasParam basParam);
	
	/**
	 * 编辑
	 * @param basParam
	 */
	public void updateParame(BasParam basParam);
	
	/**
	 * 根据主键查找
	 * @param paraid
	 */
	public BasParam seleteParameByid(Integer paraid);
	
	/**
	 * 删除
	 * @param basParam
	 * @return
	 */
	public String deleteParame(String str);

	/**
	 * 根据cid查找学校网点
	 */
	public List<BasParam> obtainParameListByCid(Integer cid);

}
