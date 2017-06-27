package com.zrkj.ecp.dao.memberintegral;

import com.zrkj.ecp.domain.memberintegral.MemberRule;
import com.zrkj.ecp.dto.memberintegral.MemberRuleDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberRuleMapper {
    int deleteByPrimaryKey(Integer rid);

    int insert(MemberRule record);

    int insertSelective(MemberRule record);

    MemberRule selectByPrimaryKey(Integer rid);

    int updateByPrimaryKeySelective(MemberRule record);

    int updateByPrimaryKey(MemberRule record);

    /**
     * 获取会员积分规则列表
     * @param memberRuleDto
     * @return
     */
    List<MemberRule> selectMemberRuleList(MemberRuleDto memberRuleDto);

    /**
     * 获取总记录数
     * @param memberRuleDto
     * @return
     */
    Integer selectMemberRuleCount(MemberRuleDto memberRuleDto);

    Integer selectMaxAid();
}