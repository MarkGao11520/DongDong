package com.zrkj.ecp.service.serviceimpl.memberintegral;

import com.zrkj.ecp.domain.memberintegral.MemberRule;
import com.zrkj.ecp.dao.memberintegral.MemberRuleMapper;
import com.zrkj.ecp.dto.memberintegral.MemberRuleDto;
import com.zrkj.ecp.service.memberintegral.MemberRuleService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberRuleServiceImpl implements MemberRuleService {
    @Resource
    MemberRuleMapper memberRuleMapper;
    /**
     * 获取规则列表
     * @param memberRuleDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberRule(MemberRuleDto memberRuleDto) {
        memberRuleDto.setPage((memberRuleDto.getPage()-1)*memberRuleDto.getRows());
        if(memberRuleDto.getRuleDetail()!=null){
            memberRuleDto.setRuleDetail("%"+memberRuleDto.getRuleDetail()+"%");
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberRuleMapper.selectMemberRuleList(memberRuleDto));
        map.put("total",memberRuleMapper.selectMemberRuleCount(memberRuleDto));
        return map;
    }

    /**
     * 根据id获取规则信息
     * @param rId
     * @return
     */
    @Override
    public MemberRule obtainMemberRuleById(Integer rId) {
        return memberRuleMapper.selectByPrimaryKey(rId);
    }

    @Override
    public boolean addMemberRule(MemberRule memberRule) {
        try {
            Integer maxid = memberRuleMapper.selectMaxAid();
            if(maxid!=null) {
                memberRule.setRid(maxid+1);
            }else {
                memberRule.setRid(1);
            }
            memberRuleMapper.insertSelective(memberRule);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateMemberRule(MemberRule memberRule) {
        try {
            memberRuleMapper.updateByPrimaryKeySelective(memberRule);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteMemberRule(String memberRuleStr) {
        try {
            String rId[] = memberRuleStr.split(",");
            MemberRule memberRule = new MemberRule();
            for (String string : rId) {
                memberRule.setRid(Integer.parseInt(string));
                memberRule.setIsdel(1);
                memberRuleMapper.updateByPrimaryKeySelective(memberRule);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
