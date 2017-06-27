package com.zrkj.ecp.dao.dept;

import com.zrkj.ecp.domain.dept.BasDept;
import com.zrkj.ecp.dto.dept.DeptDto;
import com.zrkj.ecp.dto.dept.PdeptDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasDeptMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(BasDept record);

    int insertSelective(BasDept record);

    BasDept selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(BasDept record);

    int updateByPrimaryKey(BasDept record);
    
    /**
     * 查找最大id
     * @return
     */
    Integer selectMaxAid();
    
    /**
     * 获取部门列表
     * @param deptDto
     * @return
     */
    List<BasDept> selectDeptList(DeptDto deptDto);
    
    /**
     * 获取总记录数
     * @return
     */
    Integer selectDeptCount(DeptDto deptDto);
    
    /**
     * 获取pdeptdto
     * @param did
     * @return
     */
    List<PdeptDto>  selectDeptDtoList(Integer cid);
    
    /**
     * 根据did查找dame
     * @param did
     * @return
     */
    String selectDeptNameByDid(Integer did);
    
    /**
     * 查找是否有子关联
     * @param integer
     * @return
     */
    Integer selectCountByuFK(Integer integer);
    
   
}