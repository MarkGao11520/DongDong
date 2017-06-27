package com.zrkj.ecp.dao.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalanceLog;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberBalanceLogMapper {
    int deleteByPrimaryKey(String logid);

    int insert(MemberBalanceLog record);

    int insertSelective(MemberBalanceLog record);

    MemberBalanceLog selectByPrimaryKey(String logid);

    int updateByPrimaryKeySelective(MemberBalanceLog record);

    int updateByPrimaryKeyWithBLOBs(MemberBalanceLog record);

    int updateByPrimaryKey(MemberBalanceLog record);

    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    List<MemberBalanceLog> selectMemberBalancesLogList(MemberIntergralDto memberIntergralDto);

    /**
     * 获取数量
     * @param memberIntergralDto
     * @return
     */
    Integer selectMemberBalancesLogCount(MemberIntergralDto memberIntergralDto);

    Integer  selectMaxAid();

}