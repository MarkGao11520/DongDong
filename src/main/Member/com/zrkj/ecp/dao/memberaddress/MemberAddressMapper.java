package com.zrkj.ecp.dao.memberaddress;

import com.zrkj.ecp.domain.memberaddress.MemberAddress;
import com.zrkj.ecp.dto.memberaddress.MemberAddressdDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberAddressMapper {
    int deleteByPrimaryKey(Integer amid);

    int insert(MemberAddress record);

    int insertSelective(MemberAddress record);

    MemberAddress selectByPrimaryKey(Integer amid);

    int updateByPrimaryKeySelective(MemberAddress record);

    int updateByPrimaryKey(MemberAddress record);

    List<MemberAddress> selectMemberAddressList(MemberAddressdDto memberAddressdDto);

    Integer selectMemberAddressCount(MemberAddressdDto memberAddressdDto);

    List<MemberAddress> selectMemberAddressListByMid(Integer mid);

    Integer selectMaxAid();

    void updateDefaultAddress(Integer mid);
}