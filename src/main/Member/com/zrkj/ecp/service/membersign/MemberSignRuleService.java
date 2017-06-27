package com.zrkj.ecp.service.membersign;

import com.zrkj.ecp.domain.membersign.MemberSginRule;
import com.zrkj.ecp.dto.membersign.MemberSignRuleDto;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public interface MemberSignRuleService {
    /**
     * 获取列表
     * @param memberSignRuleDto
     * @return
     */
    public Map<String,Object> obtainMemberSignRuleList(MemberSignRuleDto memberSignRuleDto);

    /**
     * 根据id获取
     * @param srId
     * @return
     */
    public MemberSginRule obtainMemberSignRuleBySrId(Integer srId);

    /**
     * 添加
     * @param memberSginRule
     * @return
     */
    public boolean addMemberSignRule(MemberSginRule memberSginRule);

    /**
     * 修改
     * @param memberSginRule
     * @return
     */
    public boolean updateMemberSignRule(MemberSginRule memberSginRule);

    /**
     * 删除
     * @param memberSginRuleStr
     * @return
     */
    public boolean deleteMemberSignRule(String memberSginRuleStr);
}
