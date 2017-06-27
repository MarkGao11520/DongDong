package com.zrkj.ecp.service.serviceimpl.sys.basic;

import com.zrkj.ecp.dao.sys.basic.BasAreaMapper;
import com.zrkj.ecp.domain.sys.basic.BasArea;
import com.zrkj.ecp.dto.sys.basic.AreaDto;
import com.zrkj.ecp.dto.sys.basic.PAreaDto;
import com.zrkj.ecp.service.sys.basic.BasAreaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basAreaService")
public class BasAreaServiceImpl implements BasAreaService {
	@Resource
	BasAreaMapper basAreaMapper;

	/**
	 * 测试
	 * 
	 * @return
	 */
	@Override
	public BasArea testArea() {
		// TODO Auto-generated method stub
		return basAreaMapper.selectByPrimaryKey(1);
	}

	/**
	 * 添加地区
	 * 
	 * @param basArea
	 */
	@Override
	public void addArea(BasArea basArea) {
		Integer maxid = basAreaMapper.selectMaxAid();
		if (maxid == null) {
			basArea.setAid(1);
		} else {
			basArea.setAid(maxid + 1);
		}
		
		basAreaMapper.insertSelective(basArea);
	}

	/**
	 * 查找最大Id
	 * 
	 * @return
	 */
	@Override
	public int selectMaxAId() {
		return basAreaMapper.selectMaxAid();
	}

	/**
	 * 获取父类地区
	 * 
	 * @return
	 */
	@Override
	public List<PAreaDto> selectPAreaList() {
		return basAreaMapper.selectPareaList();
	}

	/**
	 * 地区列表
	 * 
	 * @param areaDto
	 * @return
	 */
	@Override
	public Map<String, Object> selectAreaList(AreaDto areaDto) {
		String name = areaDto.getAname();
		if (areaDto.getAname() != null) {
			areaDto.setAname("%" + name + "%");
		}
		areaDto.setPage((areaDto.getPage()-1) * areaDto.getRows());
		List<BasArea> areaList = basAreaMapper.selectAreaList(areaDto);
		int size = basAreaMapper.selectAreaCount(areaDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", areaList);
		map.put("total", size);
		return map;
	}

	/**
	 * 根据aid获取BasArea
	 * 
	 * @param aid
	 * @return
	 */
	@Override
	public BasArea selectAreaByAid(Integer aid) {
		return basAreaMapper.selectByPrimaryKey(aid);
	}

	/**
	 * 修改
	 */
	@Override
	public void updateArea(BasArea basArea) {
		// TODO Auto-generated method stub
		if (basArea.getPid() != null &&basArea.getPid()!=1) {
			basArea.setZonetype((basAreaMapper.selectZoneTypeByPid(basArea
					.getPid())).toString());
		}
		basAreaMapper.updateByPrimaryKeySelective(basArea);
	}

	/**
	 * 获取父节点的所有子节点，并拼接成html
	 */
	@Override
	public String selectAreaByPid(Integer aid) {
		// TODO Auto-generated method stub
		List<BasArea> areas = basAreaMapper.selectAreaListbypid(aid);
		String html = null;
		for (BasArea basArea : areas) {
			html += "<tr id=\"" + basArea.getAid() + "\" ";
			html += "pid=\"" + basArea.getPid() + "\" ";
			html += "hasChild=\"true\" >";
			html += "<td>" + basArea.getAid() + "</td>";
			html += "<td><input type=\"checkbox\" name=\"aids\" "
					+ "\"id=\"aid\" " + "value=\"" + basArea.getAid()
					+ "\" class=\"checked-focus\" />";
			html += "<td>" + basArea.getAname() + "</td>";
			html += "<td>" + basArea.getCode() + "</td>";
			switch (Integer.parseInt(basArea.getZonetype())) {
			case 1:
				html += "<td>省</td>";
				break;
			case 2:
				html += "<td>市</td>";
				break;
			case 3:
				html += "<td>县</td>";
				break;
			default:
				break;
			}
			if (Integer.parseInt(basArea.getZonetype()) == 2) {
				html += "<td><a href=\"updateAreaInput?aid="
						+ basArea.getAid()
						+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"编辑\"></a>";
				html += "&nbsp<a href=\"addAreaInput?zonetype="
						+ 3
						+ "&aid="
						+ basArea.getAid()
						+ "&aname="
						+ basArea.getAname()
						+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"在此节点添加\"></a></td>";
			} else {
				html += "<td><a href=\"updateAreaInput?aid="
						+ basArea.getAid()
						+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"编辑\"></a></td>";
			}
			html += "</tr>";
		}
		return html;
	}

	/**
	 * 查找dtolist
	 */
	@Override
	public List<PAreaDto> selectPAreaListByPid(Integer aid) {
		// TODO Auto-generated method stub
		return basAreaMapper.selectPareaListByPid(aid);
	}

	/**
	 * 根据p节点查找该支所有节点
	 */

	@Override
	public List<PAreaDto> selectAllPAreaListByPid(Integer aid) {
		// TODO Auto-generated method stub
		return basAreaMapper.selectAllPareaListByPid(aid);
	}

	/**
	 * 删除
	 */
	@Override
	public String deleteArea(String str) {
		// TODO Auto-generated method stub
		String aid[] = str.split(",");
		String result;
		for (String string : aid) {
			if (basAreaMapper.selectCountByPid(Integer.parseInt(string)) > 0
					|| basAreaMapper.selectCountByFK(Integer.parseInt(string)) > 0) {
				result = "第" + string + "条删除失败，因为其正在被其他表格关联";
				System.out.println("error");
				return result;
			}
		}
		for (String string : aid) {
			BasArea basArea = new BasArea();
			basArea.setAid(Integer.parseInt(string));
			basArea.setIsdel(1);
			basAreaMapper.updateByPrimaryKeySelective(basArea);
		}
		return "删除成功";
	}

}
