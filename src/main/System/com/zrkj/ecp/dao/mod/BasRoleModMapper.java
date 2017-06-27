package com.zrkj.ecp.dao.mod;

import com.zrkj.ecp.domain.mod.BasRoleMod;
import com.zrkj.ecp.domain.mod.BasRoleModKey;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BasRoleModMapper {
    int deleteByPrimaryKey(BasRoleModKey key);

    int insert(BasRoleMod record);

    int insertSelective(BasRoleMod record);

    BasRoleMod selectByPrimaryKey(BasRoleModKey key);

    int updateByPrimaryKeySelective(BasRoleMod record);

    int updateByPrimaryKey(BasRoleMod record);
}