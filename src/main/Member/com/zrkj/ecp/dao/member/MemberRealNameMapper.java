package com.zrkj.ecp.dao.member;

import com.zrkj.ecp.domain.member.MemberRealName;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberRealNameMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(MemberRealName record);

    int insertSelective(MemberRealName record);

    MemberRealName selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberRealName record);

    int updateByPrimaryKey(MemberRealName record);

    Integer selectMaxAid();

}