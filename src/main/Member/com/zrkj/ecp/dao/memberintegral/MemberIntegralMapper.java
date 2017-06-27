package com.zrkj.ecp.dao.memberintegral;

import com.zrkj.ecp.domain.memberintegral.MemberIntegral;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberIntegralMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(MemberIntegral record);

    int insertSelective(MemberIntegral record);

    MemberIntegral selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(MemberIntegral record);

    int updateByPrimaryKey(MemberIntegral record);

    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    List<MemberIntegral> selectMemberInteList(MemberIntergralDto memberIntergralDto);

    /**
     * 获取总数
     * @param memberIntergralDto
     * @return
     */
    Integer selectMemberInteCount(MemberIntergralDto memberIntergralDto);

    Integer selectMaxAid();

}