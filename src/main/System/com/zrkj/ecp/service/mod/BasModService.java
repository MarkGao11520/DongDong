package com.zrkj.ecp.service.mod;

import com.zrkj.ecp.domain.mod.BasMod;
import com.zrkj.ecp.domain.mod.BasRoleMod;
import com.zrkj.ecp.dto.mod.ModDto;
import com.zrkj.ecp.dto.mod.PModDto;

import java.util.List;
import java.util.Map;

public interface BasModService {
	
	/**
	 * 获取一级功能列表
	 * @param modDto
	 * @return
	 */
	Map<String, Object> selectFirstModList(ModDto modDto);
	
	/**
	 * 根据appid获取功能
	 * @param appid
	 * @return
	 */
	List<BasMod> obtainModListByAppId(Integer appid, Integer uid);
	
	/**
	 * 根据pid查找并转json
	 * @param pid
	 * @return
	 */
	String selectModListByPid(Integer pid);
	
	/**
	 * 根据pid查找并转json
	 * @param pid
	 * @return
	 */
	String jsonModListByPid(Integer pid);
	
	/**
	 * 根据父节点查找所有pmoddto
	 * @param modid
	 * @return
	 */
	List<PModDto> selectAllPModDtoBymodid(Integer modid);
	
	/**
	 * 增加功能
	 * @param basMod
	 */
	void addMod(BasMod basMod);
	
	/**
	 * 修改功能
	 * @param basMod
	 */
	void updateMod(BasMod basMod);
	
	/**
	 * 删除功能
	 * @param str
	 */
	String deleteMod(String str);
	
	/**
	 * 根据主键查找
	 * @param modid
	 * @return
	 */
	BasMod selectModByModid(Integer modid);
	
	/**
	 * 获取pmoddto
	 * @return
	 */
	Map<String, Object> selectPModDto(ModDto modDto, BasRoleMod basRoleMod);
	
	/**
	 * 授权表插入
	 */
	void updateModRole(BasRoleMod basRoleMod, String str, String nocheckstr);
	
	

}
