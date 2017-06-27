package com.zrkj.ecp.service.serviceimpl.org;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.dao.org.BasCompanyMapper;
import com.zrkj.ecp.dao.sys.basic.BasAreaMapper;
import com.zrkj.ecp.domain.org.BasCompanyWithBLOBs;
import com.zrkj.ecp.dto.org.CompDto;
import com.zrkj.ecp.dto.org.PCompanyDto;
import com.zrkj.ecp.service.org.BasCompanyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basCompanyService")
public class BasCompanyServiceImpl implements BasCompanyService {
	@Resource
	private BasAreaMapper basAreaMapper;
	@Resource
	private BasCompanyMapper basCompanyMapper;

	/**
	 * 获取父机构节点
	 */
	@Override
	public List<PCompanyDto> selectCompany(Integer cid) {
		// TODO Auto-generated method stub
		return basCompanyMapper.selectPCompanyList(cid);
	}

	/**
	 * 新增机构
	 */
	@Override
	public void addCompany(BasCompanyWithBLOBs basCompanyWithBLOBs) {
		// TODO Auto-generated method stub
		Integer maxid = basCompanyMapper.selectMaxAid();
		if (maxid == null) {
			basCompanyWithBLOBs.setCid(1);
		} else {
			basCompanyWithBLOBs.setCid(maxid + 1);
		}

		basCompanyMapper.insertSelective(basCompanyWithBLOBs);
	}

	/**
	 * 机构列表
	 */
	@Override
	public Map<String, Object> selectCompanylist(CompDto compDto) {
		// TODO Auto-generated method stub
		String name = compDto.getCname();
		if (compDto.getCname() != null) {
			compDto.setCname("%" + name + "%");
		}
		compDto.setPage((compDto.getPage()-1 )* compDto.getRows());
		List<BasCompanyWithBLOBs> companyList = basCompanyMapper.selectCompanyList(compDto);
		int size = basCompanyMapper.selectCompanyCount(compDto) ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rows", companyList);
		map.put("total", size);
		return map;
	}

	/**
	 * 更改机构显示
	 */
	@Override
	public BasCompanyWithBLOBs selectCompanyByCid(Integer cid) {
		// TODO Auto-generated method stub
		return basCompanyMapper.selectByPrimaryKey(cid);
	}

	/**
	 * 更改机构
	 */
	@Override
	public void updateCompany(BasCompanyWithBLOBs basCompanyWithBLOBs) {
		// TODO Auto-generated method stub
		basCompanyMapper.updateByPrimaryKeySelective(basCompanyWithBLOBs);
	}

	/**
	 * 根据pid获取机构列表并整理成html
	 */
	@Override
	public String selecCompanylistBypid(Integer aid, HttpServletRequest request) {
		// TODO Auto-generated method stub
		List<BasCompanyWithBLOBs> comps = basCompanyMapper
				.selectCompanyListByPid(aid);
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		String html = null;
		for (BasCompanyWithBLOBs basCompanyWithBLOBs : comps) {
			html += "<tr id=\"" + basCompanyWithBLOBs.getCid() + "\" ";
			html += "pid=\"" + basCompanyWithBLOBs.getPid() + "\" ";
			html += "hasChild=\"true\" >";
			html += "<td>" + basCompanyWithBLOBs.getCid() + "</td>";
			html += "<td><input type=\"checkbox\" name=\"cids\" "
					+ "\"id=\"cid\" " + "value=\""
					+ basCompanyWithBLOBs.getCid()
					+ "\" class=\"checked-focus\" />";
			html += "<td>" + basCompanyWithBLOBs.getName() + "</td>";
			html += "<td>" + basCompanyWithBLOBs.getIndustry() + "</td>";
			html += "<td>" + basCompanyWithBLOBs.getTelephone() + "</td>";
			html += "<td>" + basCompanyWithBLOBs.getFax() + "</td>";
			html += "<td>" + basCompanyWithBLOBs.getAddress() + "</td>";
			html += "<td><a href=\"updateCompanyInput?cid="
					+ basCompanyWithBLOBs.getCid()
					+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"编辑\"></a> ";
			html += "<a href=\""
					+ basePath
					+ "configController/ConfigList?cid="
					+ basCompanyWithBLOBs.getCid()
					+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"系统设置\"></a> ";
			html += "<a href=\""
					+ basePath
					+ "parameController/ParameList?cid="
					+ basCompanyWithBLOBs.getCid()
					+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"参数设置\"></a></td>";
			html += "</tr>";
		}
		return html;
	}

	/**
	 * 根据pid获取子机构节点信息
	 */
	@Override
	public List<PCompanyDto> selectCompanyByPid(Integer pid) {
		// TODO Auto-generated method stub
		return basCompanyMapper.selectPCompanyListBypid(pid);
	}

	/**
	 * 讲dtolist转json
	 */
	@Override
	public String jsonCompanyDtoList(String id) {
		// TODO Auto-generated method stub
		List<PCompanyDto> list = new ArrayList<PCompanyDto>();
		if (id == null) {
			list = basCompanyMapper.selectFirestCompanyList();
		} else {
			list = basCompanyMapper.selectPCompanyListBypid(Integer
					.parseInt(id));
		}
		List<Map<String, String>> jsonlist = new ArrayList<Map<String, String>>();
		for (PCompanyDto pCompanyDto : list) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", pCompanyDto.getCid().toString());
			map.put("text", pCompanyDto.getCname());
			map.put("state", "closed");
			jsonlist.add(map);
		}
		return JSON.toJSONString(jsonlist);
	}

	@Override
	public List<PCompanyDto> selectAllCompanyListBycid(Integer cid) {
		// TODO Auto-generated method stub
		List<PCompanyDto> lists = basCompanyMapper
				.selectAllCompanyListBycid(cid);
		for (PCompanyDto pCompanyDto : lists) {
			if (pCompanyDto.getCid() == cid) {
				pCompanyDto.setSelected(true);
			}
		}

		return lists;
	}

	@Override
	public String deleteCompany(String str) {
		// TODO Auto-generated method stub
		String cid[] = str.split(",");
		String result;
		for (String string : cid) {
			if (basCompanyMapper.selectCountByPid(Integer.parseInt(string)) > 0
					|| basCompanyMapper.selectCountBydFK(Integer
							.parseInt(string)) > 0
					|| basCompanyMapper.selectCountByuFK(Integer
							.parseInt(string)) > 0) {
				result = "第" + string + "几条记录删除失败，因为其正在被其他表关联";
				return result;
			}
		}
		for (String string : cid) {
			BasCompanyWithBLOBs basCompanyWithBLOBs = new BasCompanyWithBLOBs();
			basCompanyWithBLOBs.setCid(Integer.parseInt(string));
			basCompanyWithBLOBs.setIsdel(1);
			basCompanyMapper.updateByPrimaryKeySelective(basCompanyWithBLOBs);
		}
		result = "删除成功";
		return result;
	}

	@Override
	public List<PCompanyDto> obtainSchoolList() {
		return basCompanyMapper.selectSchoolList();
	}

}
