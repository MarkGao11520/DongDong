package com.zrkj.ecp.service.memberintegral;

import com.zrkj.ecp.domain.memberintegral.MemberRule;
import com.zrkj.ecp.dto.memberintegral.MemberRuleDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberRuleService {
    /**
     * 获取会员规则列表
     * @param memberRuleDto
     * @return
     */
    public Map<String,Object> obtainMemberRule(MemberRuleDto memberRuleDto);

    /**
     * 根据id获取会员规则
     * @param rId
     * @return
     */
    public MemberRule obtainMemberRuleById(Integer rId);

    /**
     * 添加
     * @param memberRule
     * @return
     */
    public boolean addMemberRule(MemberRule memberRule);

    /**
     * 编辑
     * @param memberRule
     * @return
     */
    public boolean updateMemberRule(MemberRule memberRule);

    /**
     * 删除
     * @param memberRuleStr
     * @return
     */
    public boolean deleteMemberRule(String memberRuleStr);
}
