package com.zrkj.ecp.service.memberaddress;

import com.zrkj.ecp.domain.memberaddress.MemberAddress;
import com.zrkj.ecp.dto.memberaddress.MemberAddressdDto;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
public interface MemberAddressService {
    public Boolean addMemberAddress(MemberAddress memberAddress);

    public Boolean updateMemberAddress(MemberAddress memberAddress);

    public Boolean deleteMemberAddress(String cmIdstr);

    public List<MemberAddress> obtainMemberAddressListByMid(Integer mid);

    public Map<String,Object> obtainMemberAddressList(MemberAddressdDto memberAddressdDto);

    public Boolean updateDefaultAddress(Integer mid,Integer cmid);

    public MemberAddress obtainMemberAddtressByCmId(Integer mid);
}
