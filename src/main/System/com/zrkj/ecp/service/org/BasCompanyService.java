package com.zrkj.ecp.service.org;

import com.zrkj.ecp.domain.org.BasCompanyWithBLOBs;
import com.zrkj.ecp.domain.org.parame.BasParam;
import com.zrkj.ecp.dto.org.CompDto;
import com.zrkj.ecp.dto.org.PCompanyDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BasCompanyService {
	
	
	/**
	 * 根据pid获取父机构节点信息
	 * @return
	 */
	public List<PCompanyDto> selectCompanyByPid(Integer pid);
	
	/**
	 * 获取父机构节点信息
	 * @return
	 */
	public List<PCompanyDto> selectCompany(Integer cid);
	
	/**
	 * 新增公司
	 * @param basCompany
	 */
	public void addCompany(BasCompanyWithBLOBs bCompanyWithBLOBs);
	
	/**
	 * 机构列表
	 * @param compDto
	 * @return
	 */
	public Map<String,Object> selectCompanylist(CompDto compDto);
	
	/**
	 * 根据pid获取机构列表
	 * @param aid
	 * @return
	 */
	public String selecCompanylistBypid(Integer aid, HttpServletRequest request);
	
	/**
	 * 更改机构显示
	 * @param cid
	 * @return
	 */
	public BasCompanyWithBLOBs selectCompanyByCid(Integer cid);
	
	/**
	 * 更改机构
	 * @param basCompanyWithBLOBs
	 */
	public void updateCompany(BasCompanyWithBLOBs basCompanyWithBLOBs);
	
	/**
	 * 讲dtolist转换成json
	 * @param id
	 * @return
	 */
	public String jsonCompanyDtoList(String id);
	
	/**
	 * 查找全部
	 * @param cid
	 * @return
	 */
	public List<PCompanyDto> selectAllCompanyListBycid(Integer cid);
	
	/**
	 * 删除
	 * @param str
	 * @return
	 */
	public String deleteCompany(String str);

	/**
	 * 获取学校列表
	 * @return
     */
	public List<PCompanyDto> obtainSchoolList();



}
