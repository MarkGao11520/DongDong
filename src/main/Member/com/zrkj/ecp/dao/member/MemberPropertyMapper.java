package com.zrkj.ecp.dao.member;

import com.zrkj.ecp.domain.member.MemberProperty;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberPropertyMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(MemberProperty record);

    int insertSelective(MemberProperty record);

    MemberProperty selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberProperty record);

    int updateByPrimaryKey(MemberProperty record);

    Integer selectMaxAid();

}