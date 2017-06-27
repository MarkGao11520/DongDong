package com.zrkj.ecp.service.dept;

import com.zrkj.ecp.domain.dept.BasDept;
import com.zrkj.ecp.dto.dept.DeptDto;
import com.zrkj.ecp.dto.dept.PdeptDto;

import java.util.List;
import java.util.Map;

public interface BasDeptService {
	public BasDept testDept();

	/**
	 * 添加地区
	 * 
	 * @param basDept
	 */
	public void addDept(BasDept basDept);

	/**
	 * 查找最大ID
	 * 
	 * @return
	 */
	public int selectMaxAId();


	/**
	 * 获取地区列表
	 * 
	 * @param DeptDto
	 * @return
	 */
	public Map<String, Object> selectDeptList(DeptDto DeptDto);

	/**
	 * 根据aid获取BasDept
	 * 
	 * @param aid
	 * @return
	 */
	public BasDept selectDeptByAid(Integer aid);

	/**
	 * 更改Dept
	 * 
	 * @param basDept
	 */
	public void updateDept(BasDept basDept);
	
	/**
	 * 根据did查找deptdto
	 * @param did
	 * @return
	 */
	public List<PdeptDto>  selectDeptDtoList(Integer cid, Integer did);
	
	/**
	 * 将dtolist转换成jsontree形式
	 * @param cid
	 * @return
	 */
	public String DtoListToJsonTree(Integer cid);
	
	/**
	 * 删除部门
	 * @param str
	 * @return
	 */
	public String deleteDept(String str);
	
}
