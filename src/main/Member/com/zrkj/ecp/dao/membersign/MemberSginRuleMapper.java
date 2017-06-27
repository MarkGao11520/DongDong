package com.zrkj.ecp.dao.membersign;

import com.zrkj.ecp.domain.membersign.MemberSginRule;
import com.zrkj.ecp.dto.membersign.MemberSignRuleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberSginRuleMapper {
    int deleteByPrimaryKey(Integer srid);

    int insert(MemberSginRule record);

    int insertSelective(MemberSginRule record);

    MemberSginRule selectByPrimaryKey(Integer srid);

    int updateByPrimaryKeySelective(MemberSginRule record);

    int updateByPrimaryKey(MemberSginRule record);

    /**
     * 获取列表
     * @param memberSignRuleDto
     * @return
     */
    List<MemberSginRule> selectMemberSignRuleList(MemberSignRuleDto memberSignRuleDto);

    /**
     * 获取数量
     * @param memberSignRuleDto
     * @return
     */
    Integer selectMemberSignRuleCount(MemberSignRuleDto memberSignRuleDto);

    Integer selectMaxAid();
}