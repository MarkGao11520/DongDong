package com.zrkj.ecp.service.serviceimpl.org.parame;

import com.zrkj.ecp.dao.org.parame.BasParamMapper;
import com.zrkj.ecp.domain.org.parame.BasParam;
import com.zrkj.ecp.dto.org.parame.ParameDto;
import com.zrkj.ecp.service.org.parame.BasParameService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basParameService")
public class BasParameServiceImpl implements BasParameService {

	@Resource
	BasParamMapper basParamMapper;
	
	/**
	 * 获取一级列表
	 */
	@Override
	public Map<String, Object> selectFirstParameList(ParameDto parameDto) {
		// TODO Auto-generated method stub
		String name = parameDto.getParaName();
		if(parameDto.getParaName()!=null){
            parameDto.setParaName("%"+name+"%");
        }
        parameDto.setPage((parameDto.getPage()-1)*parameDto.getRows());
        List<BasParam> list = basParamMapper.selectparameList(parameDto);
        int size = basParamMapper.selectparameCount(parameDto);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",list);
        map.put("total",size);
        return map;
	}

	/**
	 * 根据id获取列表
	 */
	@Override
	public String selectParamListById(Integer paraid) {
		// TODO Auto-generated method stub
		 List<BasParam> list = basParamMapper.selectparameListByPid(paraid);
		String html = null;
		for (BasParam basParame : list) {
			html += "<tr id=\""+basParame.getParaid()+"\" ";
			html += "pid=\""+basParame.getPparaid()+"\" ";
			html += "hasChild=\"true\" >";
			html +="<td>"+basParame.getParaid()+"</td>";
			html +="<td><input type=\"checkbox\" name=\"paraids\" "+"\"id=\"paraid\" "+ "value=\""+basParame.getParaid()+"\" class=\"checked-focus\" />";
			html +="<td>"+basParame.getParaname()+"</td>";
			html +="<td>"+basParame.getDescription()+"</td>";
			html +="<td>"+basParame.getIsend()+"</td>";
			html +="<td><a href=\"updateParameInput?paraid="+basParame.getParaid()+"\"><input type=\"button\" class=\"btn btn-warning\" value=\"编辑\"></a> ";
			html +="<a href=\"addParameInput?cid="+basParame.getCid()+"&pparaid="+basParame.getParaid()+"\"><input type=\"button\" class=\"btn btn-warning\" value=\"添加\"></a></td>";
			html +="</tr>";
		}
		return html;
	}

	/**
	 * 添加
	 */
	@Override
	public void addParame(BasParam basParam) {
		// TODO Auto-generated method stub
		if(basParamMapper.selectMaxAid()==null){
			basParam.setParaid(1);
		}else{
			basParam.setParaid(basParamMapper.selectMaxAid()+1);
		}
		if(basParam.getPparaid()==null){
			basParam.setType(1);
		}else{
			basParam.setType(basParamMapper.selectTypeByid(basParam.getPparaid())+1);
		}
		basParamMapper.insertSelective(basParam);
	}
	
	

	/**
	 * 修改
	 */
	@Override
	public void updateParame(BasParam basParam) {
		// TODO Auto-generated method stub
		basParamMapper.updateByPrimaryKeySelective(basParam);
	}

	/**
	 * 删除
	 */
	@Override
	public String deleteParame(String str) {
		// TODO Auto-generated method stub
		String paramid[] = str.split(",");
		String result;
		for (String string : paramid) {
			if(basParamMapper.selectCountByPid(Integer.parseInt(string))>0){
				result = "第"+string+"条记录删除失败，因为其正在被其他表关联";
				return result;
			}
		}
		for (String string : paramid) {
			BasParam basParam = new BasParam();
			basParam.setParaid(Integer.parseInt(string));
			basParam.setIsdel(1);
			basParamMapper.updateByPrimaryKeySelective(basParam);
		}
		result = "删除成功";
		return result;
	}

	@Override
	public List<BasParam> obtainParameListByCid(Integer cid) {
		return basParamMapper.selectparameListBycid(cid);
	}

	@Override
	public BasParam seleteParameByid(Integer paraid) {
		// TODO Auto-generated method stub
		return basParamMapper.selectByPrimaryKey(paraid);
	}
	
	

}
