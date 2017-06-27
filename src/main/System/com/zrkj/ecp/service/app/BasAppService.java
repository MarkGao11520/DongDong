package com.zrkj.ecp.service.app;

import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.dto.app.AppDto;
import com.zrkj.ecp.dto.app.PMappDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BasAppService {
	/**
	 * 添加应用
	 * @param basApp
	 */
	public void addApp(BasApp basApp);
	
	/**
	 * 查找
	 * @param appDto
	 * @return
	 */
	public Map<String,Object> appList(AppDto appDto);
	
	/**
	 * 根据主键查找
	 * @param appid
	 * @return
	 */
	public BasApp selectAppByAppId(Integer appid);
	
	/**
	 * 修改
	 * @param basApp
	 */
	public void updateApp(BasApp basApp);
	
	/**
	 * 获取pmdto总记录
	 * @return
	 */
	public List<PMappDto> selectPMappDtoList();
	
	/**
	 * 删除
	 * @param str
	 * @return
	 */
	public String deleteApp(String str);

	/**
	 * 根据uid获取applist
	 * @param uid
	 * @return
     */
	public List<BasApp> obtainAppListByUid(Integer uid);

	public Map<String,String> uploadAPK(HttpServletRequest request, MultipartFile multipartFile, Integer appid);

}
