package com.zrkj.ecp.service.serviceimpl.position;

import com.zrkj.ecp.dao.position.BasPositionMapper;
import com.zrkj.ecp.domain.position.BasPosition;
import com.zrkj.ecp.dto.position.PositionDto;
import com.zrkj.ecp.dto.position.PpositionDto;
import com.zrkj.ecp.service.position.BasPositionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basPositionService")
public class BasPositionServiceImpl implements BasPositionService {
	@Resource
	BasPositionMapper basPositionMapper;

	/**
	 * 添加
	 */
	@Override
	public void addPosition(BasPosition basPosition) {
		// TODO Auto-generated method stub
		Integer maxid = basPositionMapper.selectMaxid();
		if (maxid == null) {
			basPosition.setPositionid(1);
		} else {
			basPosition.setPositionid(maxid + 1);
		}
		if (basPosition.getPpositionid() == null) {
			basPosition.setPosorder(1);
		} else {
			basPosition.setPosorder(basPositionMapper
					.selectPositionOrderById(basPosition.getPpositionid()) + 1);
		}
		basPositionMapper.insertSelective(basPosition);
	}

	/**
	 * 查找岗位列表
	 */
	@Override
	public Map<String, Object> positionList(PositionDto positionDto) {
		// TODO Auto-generated method stub
		String name = positionDto.getPositionName();
		positionDto.setPage((positionDto.getPage() -1)* positionDto.getRows());
		if (positionDto.getPositionName() != null) {
			positionDto.setPositionName("%" + name + "%");
		}
		List<BasPosition> positionlist = basPositionMapper
				.selectPositionList(positionDto);
		int size = basPositionMapper.selectPositionCount(positionDto) ;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", size);
		map.put("rows", positionlist);
		return map;
	}

	/**
	 * 根据pid查找岗位
	 */
	@Override
	public String selectPositionListByPid(Integer ppositionid,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		List<BasPosition> comps = basPositionMapper
				.selectPositionListByPid(ppositionid);
		String html = null;
		for (BasPosition basPosition : comps) {
			html += "<tr id=\"" + basPosition.getPositionid() + "\"  ";
			html += "pid=\"" + basPosition.getPpositionid() + "\" ";
			html += "hasChild=\"true\" >";
			html += "<td>" + basPosition.getPositionid() + "</td>";
			html += "<td><input type=\"checkbox\" name=\"positionids\" "
					+ "\"id=\"positionid\" " + "value=\""
					+ basPosition.getPositionid()
					+ "\" class=\"checked-focus\" />";
			html += "<td>" + basPosition.getPositionname() + "</td>";
			html += "<td>" + basPosition.getQuota() + "</td>";
			html += "<td><a href=\"updatePositionInput?positionid="
					+ basPosition.getPositionid()
					+ "\"><input type=\"button\" class=\"btn btn-warning\" value=\"编辑\"></a> ";
			html += "<a href=\"addPositionInput?cid="
					+ basPosition.getCid()
					+ "&did="
					+ basPosition.getDid()
					+ "&ppositionid="
					+ basPosition.getPositionid()
					+ "\"><input type=\"button\" class=\"btn btn-primary\" value=\"添加\"></a> ";
			html += "<a href=\""
					+ basePath
					+ "modController/modMain?type=1&code=2&positionid="
					+ basPosition.getPositionid()
					+ "\"><input type=\"button\" class=\"btn btn-denger\" value=\"授权\"></a></td>";
			html += "</tr>";
		}
		return html;
	}

	/**
	 * 根据pid查找dtolist
	 * 
	 * @param positionid
	 * @return
	 */
	@Override
	public List<PpositionDto> selectPositionDtoListByPid(Integer positionid,
														 Integer ppositionid) {
		// TODO Auto-generated method stub
		List<PpositionDto> list = new ArrayList<PpositionDto>();

		list = basPositionMapper.selectPpositionDtoListByPid(ppositionid);

		// System.out.println("pid:"+positionid);
		// System.out.println("list:"+JSON.toJSONString(list));
		return list;
	}

	/**
	 * 根据主键查找
	 */
	@Override
	public BasPosition selectPositionByPositionid(Integer positionid) {
		// TODO Auto-generated method stub
		return basPositionMapper.selectByPrimaryKey(positionid);
	}

	/**
	 * 修改
	 */
	@Override
	public void updatePosition(BasPosition basPosition) {
		// TODO Auto-generated method stub
		if (basPosition.getPpositionid() == null) {
			basPosition.setPosorder(1);
		} else {
			basPosition.setPosorder(basPositionMapper
					.selectPositionOrderById(basPosition.getPpositionid()) + 1);
		}
		basPositionMapper.updateByPrimaryKeySelective(basPosition);
	}

	/**
	 * 删除
	 */
	@Override
	public String deletePosition(String str) {
		// TODO Auto-generated method stub
		String positionid[] = str.split(",");
		String result;
		for (String string : positionid) {
			if (basPositionMapper.selectCountByPid(Integer.parseInt(string)) > 0
					|| basPositionMapper.selectCountByMRFK(Integer
							.parseInt(string)) > 0) {
				result = "第" + string + "条记录删除失败，因为其正在被其他表关联";
				return result;
			}
		}
		for (String string : positionid) {
			BasPosition basPosition = new BasPosition();
			basPosition.setPositionid(Integer.parseInt(string));
			basPosition.setIsdel(1);
			basPositionMapper.updateByPrimaryKeySelective(basPosition);
		}
		result = "删除成功";
		return result;
	}

	@Override
	public List<PpositionDto> selectPositionDtoList(Integer did, Integer posid) {
		// TODO Auto-generated method stub
		List<PpositionDto> list = basPositionMapper.selectPositionDtoListByDid(did);
		for (PpositionDto ppositionDto : list) {
			if(ppositionDto.getPositionid()==posid){
				ppositionDto.setSelected(true);
			}
		}
		return list;
	}

}
