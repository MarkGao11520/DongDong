package com.zrkj.ecp.dao.mod;

import com.zrkj.ecp.domain.mod.BasMod;
import com.zrkj.ecp.dto.mod.ModDto;
import com.zrkj.ecp.dto.mod.PModDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BasModMapper {
    int deleteByPrimaryKey(Integer modid);

    int insert(BasMod record);

    int insertSelective(BasMod record);

    BasMod selectByPrimaryKey(Integer modid);

    int updateByPrimaryKeySelective(BasMod record);

    int updateByPrimaryKey(BasMod record);
    
    /**
     * 获取一级功能列表
     * @return
     */
    List<BasMod> selectFirstModList(ModDto modDto);
    
    /**
     * 获取总记录数
     * @param modDto
     * @return
     */
    Integer selectFirstModCount(ModDto modDto);
    
    /**
     * 根据父节点选取子节点
     * @param pid
     * @return
     */
    List<BasMod> selectModListByPid(Integer pid);
    
    /**
     * 查找所有pmoddto
     * @param modid
     * @return
     */
    List<PModDto>  selectAllPModDtoBymodid(Integer modid);
    
    /**
     * 获取最大id
     * @return
     */
    Integer selectMaxAid();
    
    /**
     * 根据modid查找等级
     * @param modid
     * @return
     */
    Integer selectClassLevelByModId(Integer modid);
    
    /**
     * 获取功能dtolist
     * @param modDto
     * @return
     */
    List<PModDto> selectFirstModDtoList(ModDto modDto);
    
    /**
     * 查看是否有子关联
     * @param modid
     * @return
     */
    Integer selectCountByMRFK(Integer modid);
    
    /**
     * 根据appid查找应用
     * @return
     */
    List<BasMod> selectModList(Integer appid);

    /**
     * 根据uid和appid获取applist
     * @param appid
     * @param uid
     * @return
     */
   List<BasMod> selectModListByAppidAndUid(@Param("appid") Integer appid, @Param("uid") Integer uid);
}