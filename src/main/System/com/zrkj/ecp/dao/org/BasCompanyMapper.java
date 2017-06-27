package com.zrkj.ecp.dao.org;

import com.zrkj.ecp.domain.org.BasCompany;
import com.zrkj.ecp.domain.org.BasCompanyWithBLOBs;
import com.zrkj.ecp.dto.org.CompDto;
import com.zrkj.ecp.dto.org.PCompanyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasCompanyMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(BasCompanyWithBLOBs record);

    int insertSelective(BasCompanyWithBLOBs record);

    BasCompanyWithBLOBs selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(BasCompanyWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BasCompanyWithBLOBs record);

    int updateByPrimaryKey(BasCompany record);
    
    /**
     * 获取父节点
     * @return
     */
    List<PCompanyDto> selectPCompanyList(Integer cid);
    List<PCompanyDto>selectFirestCompanyList();
    
    /**
     * 根据pid获取子节点
     * @return
     */
    List<PCompanyDto> selectPCompanyListBypid(Integer pid);
    
    /**
     * 查找cid和pid=cid的
     * @param cid
     * @return
     */
    List<PCompanyDto>  selectAllCompanyListBycid(Integer cid);
    
    /**
     * 获取最大id
     * @return
     */
    Integer selectMaxAid();
    
    /**
     * 获取机构列表
     * @param compDto
     * @return
     */
    List<BasCompanyWithBLOBs> selectCompanyList(CompDto compDto);
    
    /**
     * 根据pid获取list
     * @param compDto
     * @return
     */
    List<BasCompanyWithBLOBs> selectCompanyListByPid(Integer aid);
    
    /**
     * 获取总记录数
     * @return
     */
    Integer selectCompanyCount(CompDto compDto);
    
    /**
     * 根据cid查看是否有关联
     * @param cid
     * @return
     */
    Integer selectCountByPid(Integer cid);
    
    Integer selectCountBydFK(Integer cid);
    
    Integer selectCountByuFK(Integer cid);

    /**
     * 获取学校列表
     * @return
     */
    List<PCompanyDto> selectSchoolList();
    
    
}