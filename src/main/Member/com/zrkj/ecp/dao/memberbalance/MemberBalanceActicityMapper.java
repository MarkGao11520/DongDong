package com.zrkj.ecp.dao.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalanceActicity;
import com.zrkj.ecp.domain.memberbalance.MemberBalanceActicityWithBLOBs;
import com.zrkj.ecp.dto.memberbalance.MemberBalancesActicityDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberBalanceActicityMapper {
    int deleteByPrimaryKey(Integer acid);

    int insert(MemberBalanceActicityWithBLOBs record);

    int insertSelective(MemberBalanceActicityWithBLOBs record);

    MemberBalanceActicityWithBLOBs selectByPrimaryKey(Integer acid);

    int updateByPrimaryKeySelective(MemberBalanceActicityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(MemberBalanceActicityWithBLOBs record);

    int updateByPrimaryKey(MemberBalanceActicity record);

    /**
     * 获取列表
     * @param memberBalancesActicityDto
     * @return
     */
    List<MemberBalanceActicityWithBLOBs> selectMemberBalancesActicityList(MemberBalancesActicityDto memberBalancesActicityDto);

    /**
     * 获取数量
     * @param memberBalancesActicityDto
     * @return
     */
    Integer selectMemberBalancesActicityCount(MemberBalancesActicityDto memberBalancesActicityDto);

    Integer  selectMaxAid();

}