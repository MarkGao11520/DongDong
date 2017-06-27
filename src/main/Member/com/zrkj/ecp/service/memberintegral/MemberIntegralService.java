package com.zrkj.ecp.service.memberintegral;

import com.zrkj.ecp.domain.memberintegral.MemberIntegral;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberIntegralService {
    public Map<String,Object> obtainMemberIntegralList(MemberIntergralDto memberIntergralDto);

    public MemberIntegral obtainMemberIntegralByMid(Integer mid);

    public boolean addMemberIntegral(MemberIntegral memberIntegral);

    public boolean updateMemberIntegral(MemberIntegral memberIntegral);
}
