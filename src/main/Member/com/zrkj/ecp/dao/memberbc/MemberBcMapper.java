package com.zrkj.ecp.dao.memberbc;

import com.zrkj.ecp.domain.memberbc.MemberBc;
import com.zrkj.ecp.dto.memberbc.MemberBcDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberBcMapper {
    int deleteByPrimaryKey(Integer bcid);

    int insert(MemberBc record);

    int insertSelective(MemberBc record);

    MemberBc selectByPrimaryKey(Integer bcid);

    int updateByPrimaryKeySelective(MemberBc record);

    int updateByPrimaryKey(MemberBc record);

    /**
     * 获取列表
     * @param memberBcDto
     * @return
     */
    List<MemberBc> selectMemberBcList(MemberBcDto memberBcDto);

    /**
     * 获取数量
     * @param memberBcDto
     * @return
     */
    Integer selectMemberBcCount(MemberBcDto memberBcDto);

    Integer  selectMaxAid();
}