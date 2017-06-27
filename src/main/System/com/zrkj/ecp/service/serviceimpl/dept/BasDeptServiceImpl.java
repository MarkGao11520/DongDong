package com.zrkj.ecp.service.serviceimpl.dept;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.dao.dept.BasDeptMapper;
import com.zrkj.ecp.domain.dept.BasDept;
import com.zrkj.ecp.dto.dept.DeptDto;
import com.zrkj.ecp.dto.dept.PdeptDto;
import com.zrkj.ecp.service.dept.BasDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basDeptService")
public class BasDeptServiceImpl implements BasDeptService {
	@Resource
	BasDeptMapper basDeptMapper;

	/**
	 * 测试
	 */
	@Override
	public BasDept testDept() {
		// TODO Auto-generated method stub
		return basDeptMapper.selectByPrimaryKey(1);
	}
    /**
     * 新增部门
     */
	@Override
	public void addDept(BasDept basDept) {
		// TODO Auto-generated method stub
		Integer maxid = basDeptMapper.selectMaxAid();
		if(maxid==null){
			basDept.setDid(1);
		}else{
			basDept.setDid(maxid+1);
		}
        basDeptMapper.insertSelective(basDept);
	}

	/**
	 * 查找最大id
	 */
	@Override
	public int selectMaxAId() {
		// TODO Auto-generated method stub
		return basDeptMapper.selectMaxAid();
	}

	/**
	 * 根据id查找dept
	 */
	@Override
	public BasDept selectDeptByAid(Integer did) {
		// TODO Auto-generated method stub
		return basDeptMapper.selectByPrimaryKey(did);
	}

	/**
	 * 更新dept
	 */
	@Override
	public void updateDept(BasDept basDept) {
		// TODO Auto-generated method stub
        basDeptMapper.updateByPrimaryKeySelective(basDept);
	}
	
	/**
	 * 获取地区列表
	 */
	@Override
	public Map<String, Object> selectDeptList(DeptDto DeptDto) {
		// TODO Auto-generated method stub
		String name = DeptDto.getDname();
		  if(DeptDto.getDname()!=null){
	            DeptDto.setDname("%"+name+"%");
	        }
	        DeptDto.setPage((DeptDto.getPage()-1)*DeptDto.getRows());
	        List<BasDept> DeptList =basDeptMapper.selectDeptList(DeptDto);
	        int size = basDeptMapper.selectDeptCount(DeptDto);
	        Map<String,Object> map = new HashMap<String, Object>();
	        map.put("rows",DeptList);
	        map.put("total",size);
	        return map;
	}
	
	/**
	 * 根据did查找dto
	 */
	@Override
	public List<PdeptDto> selectDeptDtoList(Integer cid, Integer did) {
		// TODO Auto-generated method stub
		List<PdeptDto> lists = basDeptMapper.selectDeptDtoList(cid);
		for (PdeptDto pdeptDto : lists) {
			if(pdeptDto.getDid()== did){
				pdeptDto.setSelected(true);
			}
		}
		return lists;
	}
	
	/**
	 * 将dtolist转换成jsontree形式
	 * @param cid
	 * @return
	 */
	@Override
	public String DtoListToJsonTree(Integer cid) {
		// TODO Auto-generated method stub
		List<PdeptDto> lists = basDeptMapper.selectDeptDtoList(cid);
		List<Map<String,String>> jsonlist = new ArrayList<Map<String,String>>();
		for (PdeptDto pdeptDto : lists) {
			Map<String,String> map = new HashMap<String, String>();
			map.put("id", pdeptDto.getDid().toString());
			map.put("text",pdeptDto.getDname());
			map.put("state", "");
			jsonlist.add(map);
		}
		return JSON.toJSONString(jsonlist);
	}
	
	/**
	 * 删除部门
	 */
	@Override
	public String deleteDept(String str) {
		// TODO Auto-generated method stub
		String did[] = str.split(",");
		String result;
		for (String string : did) {
			if(basDeptMapper.selectCountByuFK(Integer.parseInt(string))>0){
				result = "第"+string+"几条记录删除失败，因为其正在被其他表关联";
				return result;
			}
		}
		for (String string : did) {
		 	BasDept basDept = new BasDept();
        	basDept.setDid(Integer.parseInt(string));
        	basDept.setIsdel(1);
        basDeptMapper.updateByPrimaryKeySelective(basDept);
		}
		result = "删除成功";
		return result;
	}

}
