package com.zrkj.ecp.dao.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalances;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberBalancesMapper {
    int deleteByPrimaryKey(Integer baid);

    int insert(MemberBalances record);

    int insertSelective(MemberBalances record);

    MemberBalances selectByPrimaryKey(Integer baid);

    int updateByPrimaryKeySelective(MemberBalances record);

    int updateByPrimaryKey(MemberBalances record);

    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    List<MemberBalances> selectMemberBalancesList(MemberIntergralDto memberIntergralDto);

    /**
     * 获取数量
     * @param memberIntergralDto
     * @return
     */
    Integer selectMemberBalancesCount(MemberIntergralDto memberIntergralDto);

    Integer  selectMaxAid();

}