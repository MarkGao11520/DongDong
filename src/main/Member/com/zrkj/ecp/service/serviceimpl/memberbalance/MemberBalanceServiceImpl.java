package com.zrkj.ecp.service.serviceimpl.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalances;
import com.zrkj.ecp.dao.memberbalance.MemberBalancesMapper;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.service.memberbalance.MemberBalanceService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberBalanceServiceImpl implements MemberBalanceService{
    @Resource
    MemberBalancesMapper memberBalancesMapper;
    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberBalanceList(MemberIntergralDto memberIntergralDto) {
        memberIntergralDto.setPage((memberIntergralDto.getPage()-1)*memberIntergralDto.getRows());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberBalancesMapper.selectMemberBalancesList(memberIntergralDto));
        map.put("total",memberBalancesMapper.selectMemberBalancesCount(memberIntergralDto));
        return map;
    }

    /**
     * 根据id获取
     * @param BaId
     * @return
     */
    @Override
    public MemberBalances obtainMemberBalanceById(Integer BaId) {
        return memberBalancesMapper.selectByPrimaryKey(BaId);
    }

    /**
     * 添加
     * @param memberBalances
     * @return
     */
    @Override
    public boolean addMemberBalaces(MemberBalances memberBalances) {
        try {
            Integer maxid = memberBalancesMapper.selectMaxAid();
            if(maxid!=null){
                memberBalances.setBaid(maxid+1);
            }else {
                memberBalances.setBaid(1);
            }
            memberBalancesMapper.insertSelective(memberBalances);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     * @param memberBalancesStr
     * @return
     */
    @Override
    public boolean deleteMemberBalaces(String memberBalancesStr) {
        try {
            String baId[] = memberBalancesStr.split(",");
            MemberBalances memberBalances = new MemberBalances();
            for (String string:baId){
                memberBalances.setBaid(Integer.parseInt(string));
                memberBalances.setIsdel(1);
                memberBalancesMapper.insertSelective(memberBalances);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 编辑
     * @param memberBalances
     * @return
     */
    @Override
    public boolean updateMemberBalaces(MemberBalances memberBalances) {
        try {
            memberBalancesMapper.updateByPrimaryKeySelective(memberBalances);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
