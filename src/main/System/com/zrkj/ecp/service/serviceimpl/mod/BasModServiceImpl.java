package com.zrkj.ecp.service.serviceimpl.mod;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.dao.mod.BasModMapper;
import com.zrkj.ecp.dao.mod.BasRoleModMapper;
import com.zrkj.ecp.domain.mod.BasMod;
import com.zrkj.ecp.domain.mod.BasRoleMod;
import com.zrkj.ecp.domain.mod.BasRoleModKey;
import com.zrkj.ecp.dto.mod.ModDto;
import com.zrkj.ecp.dto.mod.PModDto;
import com.zrkj.ecp.service.mod.BasModService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basModService")
public class BasModServiceImpl implements BasModService {
	@Resource
	BasModMapper basModMapper;
	@Resource
	BasRoleModMapper basRoleModMapper;

	/**
	 * 获取一级功能列表
	 */
	@Override
	public Map<String, Object> selectFirstModList(ModDto modDto) {
		// TODO Auto-generated method stub
		String name = modDto.getModname();
		if (modDto.getModname() != null) {
			modDto.setModname("%" + name + "%");
		}
		//System.out.println(JSON.toJSONString(modDto));
		modDto.setPage((modDto.getPage()-1) * modDto.getRows());
		List<BasMod> list = basModMapper.selectFirstModList(modDto);
		int size = basModMapper.selectFirstModCount(modDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", list);
		map.put("total", size);
		return map;
	}

	/**
	 * 根据pid查找并转json
	 * 
	 * @param pid
	 * @return
	 */
	@Override
	public String selectModListByPid(Integer pid) {
		// TODO Auto-generated method stub
		List<BasMod> list = basModMapper.selectModListByPid(pid);
		String html = null;
		for (BasMod basMod : list) {
			html += "<tr id=\"" + basMod.getModid() + "\" ";
			html += "pid=\"" + basMod.getPid() + "\" ";
			html += "hasChild=\"true\" >";
			html += "<td>" + basMod.getModid() + "</td>";
			html += "<td><input type=\"checkbox\" name=\"modids\" "
					+ "\"id=\"modid\" " + "value=\"" + basMod.getModid()
					+ "\" class=\"checked-focus\" />";
			html += "<td>" + basMod.getModname() + "</td>";
			html += "<td>" + basMod.getInstantiatemethod() + "</td>";
			html += "<td>" + basMod.getTypename() + "</td>";
			html += "<td>" + basMod.getExtradata() + "</td>";
			html += "<td>" + basMod.getUrl() + "</td>";
			html += "<td>" + basMod.getDisabled() + "</td>";
			html += "<td>" + basMod.getNotes() + "</td>";
			html += "<td><a href=\"updateModInput?modid="
					+ basMod.getModid()
					+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"编辑\"></a>";
			html += "<a href=\"addModInput?appid="
					+ basMod.getAppid()
					+ "&modid="
					+ basMod.getModid()
					+ "\"><input type=\"button\" class=\"btn btn-primary\" value=\"在此节点添加功能\"></a></td>";
			html += "</tr>";
		}
		return html;
	}

	/**
	 * 根据父节点查找所有pmoddto
	 * 
	 * @param modid
	 * @return
	 */
	@Override
	public List<PModDto> selectAllPModDtoBymodid(Integer modid) {
		// TODO Auto-generated method stub
		return basModMapper.selectAllPModDtoBymodid(modid);
	}

	/**
	 * 增加
	 */
	@Override
	public void addMod(BasMod basMod) {
		// TODO Auto-generated method stub
		Integer maxid = basModMapper.selectMaxAid();
		if (maxid == null) {
			basMod.setModid(1);
		} else {
			basMod.setModid(maxid + 1);
		}
		if (basMod.getPid() == null) {
			basMod.setClasslevel(1);
		} else {
			basMod.setClasslevel(basModMapper.selectClassLevelByModId(basMod
					.getPid()) + 1);
		}
		basModMapper.insertSelective(basMod);
	}

	/**
	 * 修改
	 */
	@Override
	public void updateMod(BasMod basMod) {
		// TODO Auto-generated method stub
		if (basMod.getPid() == null) {
			basMod.setClasslevel(1);
		} else {
			basMod.setClasslevel(basModMapper.selectClassLevelByModId(basMod
					.getPid()) + 1);
		}
		basModMapper.updateByPrimaryKeySelective(basMod);
	}

	/**
	 * 删除
	 */
	@Override
	public String deleteMod(String str) {
		// TODO Auto-generated method stub
		String modid[] = str.split(",");
		for (String string : modid) {
			if (basModMapper.selectCountByMRFK(Integer.parseInt(string)) > 0) {
				return "第" + string + "条记录删除失败，因为该功能正在被使用";
			}
		}
		for (String string : modid) {
			BasMod basMod = new BasMod();
			basMod.setModid(Integer.parseInt(string));
			basMod.setIsdel(1);
			basModMapper.updateByPrimaryKeySelective(basMod);
		}
		return "删除成功";
	}

	/**
	 * 根据主键查找
	 */
	@Override
	public BasMod selectModByModid(Integer modid) {
		// TODO Auto-generated method stub
		return basModMapper.selectByPrimaryKey(modid);
	}

	/**
	 * 获取功能列表dto
	 */
	@Override
	public Map<String, Object> selectPModDto(ModDto modDto,
			BasRoleMod basRoleMod) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		if (modDto.getModid() == null) {
			List<PModDto> list = basModMapper.selectFirstModDtoList(modDto);
			for (PModDto pModDto : list) {
				basRoleMod.setModid(pModDto.getModid());
				BasRoleMod basRoleMod2 = basRoleModMapper
						.selectByPrimaryKey(basRoleMod);
				if (basRoleMod2 != null && basRoleMod2.getIsdel() == 0) {
					pModDto.setChecked(true);
				}
			}
			map.put("first", list);
		} else {
			List<PModDto> list = basModMapper.selectFirstModDtoList(modDto);
			String html = "";
			for (PModDto pModDto : list) {
				basRoleMod.setModid(pModDto.getModid());
				BasRoleMod basRoleMod2 = basRoleModMapper
						.selectByPrimaryKey(basRoleMod);
				if (basRoleMod2 != null && basRoleMod2.getIsdel() == 0) {
					html += " &nbsp&nbsp&nbsp&nbsp&nbsp<input checked=\"checked\" type = \"checkbox\" name = \"checkedmodids\" value = \""
							+ pModDto.getModid()
							+ "\"><a id =\"a"
							+ pModDto.getModid()
							+ "\" href=\"javascript:test("
							+ pModDto.getModid()
							+ ","
							+ modDto.getAppid()
							+ ")\">" + pModDto.getModname() + "</a>";
				} else {
					html += " &nbsp&nbsp&nbsp&nbsp&nbsp<input type = \"checkbox\" name = \"modids\" value = \""
							+ pModDto.getModid()
							+ "\"><a id =\"a"
							+ pModDto.getModid()
							+ "\" href=\"javascript:test("
							+ pModDto.getModid()
							+ ","
							+ modDto.getAppid()
							+ ")\">" + pModDto.getModname() + "</a>";
				}

			}
			map.put("listhtml", html);
		}
		return map;
	}

	/**
	 * 添加授权表
	 */
	@Override
	public void updateModRole(BasRoleMod basRoleMod, String str,
							  String nocheckstr) {
		// TODO Auto-generated method stub
		String modstr[] = str.split(",");
		String nocheck[] = nocheckstr.split(",");
		if (str != "") {
			for (String string : modstr) {
				basRoleMod.setModid(Integer.parseInt(string));
				BasRoleModKey basRoleModKey = new BasRoleModKey();
				basRoleModKey.setAppid(basRoleMod.getAppid());
				basRoleModKey.setModid(basRoleMod.getModid());
				basRoleModKey.setPositionid(basRoleMod.getPositionid());
				BasRoleMod basRoleMod2 = basRoleModMapper
						.selectByPrimaryKey(basRoleModKey);
				if (basRoleMod2 != null) {
					if (basRoleMod2.getIsdel() == 1) {
						basRoleMod2.setIsdel(0);
						basRoleModMapper
								.updateByPrimaryKeySelective(basRoleMod2);
					}
				} else {
					basRoleModMapper.insertSelective(basRoleMod);
				}
			}
		}
		if(nocheckstr!="") {
			for (String string : nocheck) {
				basRoleMod.setModid(Integer.parseInt(string));
				basRoleMod.setIsdel(1);
				basRoleModMapper.updateByPrimaryKeySelective(basRoleMod);
			}
		}
	}

	/**
	 * 根据pid查找并转json
	 * @param pid
	 * @return
	 */
	@Override
	public String jsonModListByPid(Integer pid) {
		// TODO Auto-generated method stub
		List<BasMod> list = basModMapper.selectModListByPid(pid);
		System.out.println(JSON.toJSONString(list));
		String html = "";
		for (BasMod basMod : list) {
			    html+="<li><a href=\"<%=basePath%>"+basMod.getUrl()+"\"";
				html+="target=\"main\" class=\"open-tab\" tab-name=\"navside-collapse-1-2-1\">";
				html+="<img src=\"../crm/img/message.png\" class=\"icon-width\" /> <span class=\"nav-label\">用户管理</span></a></li>";
		}
		return html;
	}

	/**
	 * 根据appid获取功能
	 */
	@Override
	public List<BasMod> obtainModListByAppId(Integer appid, Integer uid) {
		// TODO Auto-generated method stub
		return basModMapper.selectModListByAppidAndUid(appid,uid);
	}

}
