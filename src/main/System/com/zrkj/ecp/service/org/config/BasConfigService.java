package com.zrkj.ecp.service.org.config;

import com.zrkj.ecp.domain.org.config.BasConfig;
import com.zrkj.ecp.dto.org.config.ConfigDto;

import java.util.Map;

public interface BasConfigService {
	/**
	 * 获取列表
	 * @param configDto
	 * @return
	 */
	Map<String,Object>  selectConfigList(ConfigDto configDto);
	
	/**
	 * 添加
	 * @param basConfig
	 */
	void addConfig(BasConfig basConfig);
	
	/**
	 * 编辑
	 * @param basConfig
	 */
	void updateConfig(BasConfig basConfig);
	
	/**
	 * 修改
	 * @param cofif
	 * @return
	 */
	BasConfig selectConfigByCofId(Integer cofif);
	
	/**
	 * 删除
	 * @param str
	 * @return
	 */
	String deleteConfig(String str);
}
