package com.zrkj.ecp.dao.app;

import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.dto.app.AppDto;
import com.zrkj.ecp.dto.app.PMappDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasAppMapper {
    int deleteByPrimaryKey(Integer appid);

    int insert(BasApp record);

    int insertSelective(BasApp record);

    BasApp selectByPrimaryKey(Integer appid);

    int updateByPrimaryKeySelective(BasApp record);

    int updateByPrimaryKey(BasApp record);
    
    /**
     * 查找最大id
     * @return
     */
    Integer selectMaxAid();
    
    /**
     * 获取应用列表
     * @return
     */
    List<BasApp>  selectAppList(AppDto appDto);
    
    /**
     * 获取总记录数
     * @param appDto
     * @return
     */
    Integer selectAppCount(AppDto appDto);
    
    /**
     * 获取pmdto
     * @return
     */
    List<PMappDto> selectPMappDtoList();
    
    /**
     * 查看是否有子关联
     * @param appid
     * @return
     */
    Integer selectCountByMFK(Integer appid);

    /**
     * 根据uid获取应用列表
     * @param uid
     * @return
     */
    List<BasApp> selectAppListByUid(Integer uid);
}