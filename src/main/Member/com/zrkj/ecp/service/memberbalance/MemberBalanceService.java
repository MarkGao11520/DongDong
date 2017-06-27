package com.zrkj.ecp.service.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalances;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberBalanceService {
    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    Map<String,Object> obtainMemberBalanceList(MemberIntergralDto memberIntergralDto);

    /**
     * 根据id获取
     * @param BaId
     * @return
     */
    MemberBalances obtainMemberBalanceById(Integer BaId);

    /**
     * 添加
     * @param memberBalances
     * @return
     */
    boolean addMemberBalaces(MemberBalances memberBalances);

    /**
     * 删除
     * @param memberBalancesStr
     * @return
     */
    boolean deleteMemberBalaces(String memberBalancesStr);

    /**
     * 编辑
     * @param memberBalances
     * @return
     */
    boolean updateMemberBalaces(MemberBalances memberBalances);
}
