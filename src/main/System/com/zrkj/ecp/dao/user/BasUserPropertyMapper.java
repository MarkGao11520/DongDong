package com.zrkj.ecp.dao.user;



import com.zrkj.ecp.domain.user.BasUserProperty;
import com.zrkj.ecp.dto.user.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BasUserPropertyMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(BasUserProperty record);

    int insertSelective(BasUserProperty record);

    BasUserProperty selectByPrimaryKey(Integer uid);

    int updateByPrimaryKeySelective(BasUserProperty record);

    int updateByPrimaryKey(BasUserProperty record);

    /**
     * 获取用户列表
     * @param userDto
     * @return
     */
    List<BasUserProperty> selectUserList(UserDto userDto);

    /**
     * 获取总记录数
     * @param userDto
     * @return
     */
    Integer selectUserCount(UserDto userDto);

}