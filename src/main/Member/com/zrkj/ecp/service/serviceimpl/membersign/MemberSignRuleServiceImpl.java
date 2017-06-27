package com.zrkj.ecp.service.serviceimpl.membersign;

import com.zrkj.ecp.domain.membersign.MemberSginRule;
import com.zrkj.ecp.dao.membersign.MemberSginRuleMapper;
import com.zrkj.ecp.dto.membersign.MemberSignRuleDto;
import com.zrkj.ecp.service.membersign.MemberSignRuleService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberSignRuleServiceImpl implements MemberSignRuleService{
    @Resource
    MemberSginRuleMapper memberSginRuleMapper;
    /**
     * 获取列表
     * @param memberSignRuleDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberSignRuleList(MemberSignRuleDto memberSignRuleDto) {
        memberSignRuleDto.setPage((memberSignRuleDto.getPage()-1)*memberSignRuleDto.getRows());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberSginRuleMapper.selectMemberSignRuleList(memberSignRuleDto));
        map.put("total",memberSginRuleMapper.selectMemberSignRuleCount(memberSignRuleDto));
        return map;
    }

    /**
     * 根据id获取信息
     * @param srId
     * @return
     */
    @Override
    public MemberSginRule obtainMemberSignRuleBySrId(Integer srId) {
        return memberSginRuleMapper.selectByPrimaryKey(srId);
    }

    /**
     * 添加
     * @param memberSginRule
     * @return
     */
    @Override
    public boolean addMemberSignRule(MemberSginRule memberSginRule) {
        try {
            Integer maxid = memberSginRuleMapper.selectMaxAid();
            if(maxid!=null) {
                memberSginRule.setSrid(maxid+1);
            }else{
                memberSginRule.setSrid(1);
            }
            memberSginRuleMapper.insertSelective(memberSginRule);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 编辑
     * @param memberSginRule
     * @return
     */
    @Override
    public boolean updateMemberSignRule(MemberSginRule memberSginRule) {
        try {
            memberSginRuleMapper.updateByPrimaryKeySelective(memberSginRule);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     * @param memberSginRuleStr
     * @return
     */
    @Override
    public boolean deleteMemberSignRule(String memberSginRuleStr) {
        try {
            String srId[] = memberSginRuleStr.split(",");
            MemberSginRule memberSginRule = new MemberSginRule();
            for (String string : srId) {
                memberSginRule.setSrid(Integer.parseInt(string));
                memberSginRule.setIsdel(1);
                memberSginRuleMapper.updateByPrimaryKeySelective(memberSginRule);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
