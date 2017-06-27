package com.zrkj.ecp.dao.org.config;

import com.zrkj.ecp.domain.org.config.BasConfig;
import com.zrkj.ecp.dto.org.config.ConfigDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasConfigMapper {
    int deleteByPrimaryKey(Integer cofid);

    int insert(BasConfig record);

    int insertSelective(BasConfig record);

    BasConfig selectByPrimaryKey(Integer cofid);

    int updateByPrimaryKeySelective(BasConfig record);

    int updateByPrimaryKey(BasConfig record);
    
    /**
     * 获取列表    
     * @param configDto
     * @return
     */
    List<BasConfig> selectConfigList(ConfigDto configDto);
    
    /**
     * 获取总数
     * @param configDto
     * @return
     */
    Integer selectCount(ConfigDto configDto);
    
    /**
     * 获取最大id
     * @return
     */
    Integer selectMaxAid();
}