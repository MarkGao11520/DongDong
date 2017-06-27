package com.zrkj.ecp.service.serviceimpl.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalanceActicityWithBLOBs;
import com.zrkj.ecp.dao.memberbalance.MemberBalanceActicityMapper;
import com.zrkj.ecp.dto.memberbalance.MemberBalancesActicityDto;
import com.zrkj.ecp.service.member.MemberService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceActicityService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberBalacneActicityServiceImpl implements MemberBalanceActicityService{
    @Resource
    MemberBalanceActicityMapper balanceActicityMapper;
    @Resource
    MemberService memberService;
    /**
     * 获取列表
     * @param memberBalancesActicityDto
     * @return
     */
    @Override
    public Map<String, Object> obtainBalcnceActicityList(MemberBalancesActicityDto memberBalancesActicityDto) {
        memberBalancesActicityDto.setPage((memberBalancesActicityDto.getPage()-1)*memberBalancesActicityDto.getRows());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",balanceActicityMapper.selectMemberBalancesActicityList(memberBalancesActicityDto));
        map.put("total",balanceActicityMapper.selectMemberBalancesActicityCount(memberBalancesActicityDto));
        return map;
    }

    /**
     * 根据id获取
     * @param acId
     * @return
     */
    @Override
    public MemberBalanceActicityWithBLOBs obtainBalcnceActicityById(Integer acId) {
        return balanceActicityMapper.selectByPrimaryKey(acId);
    }

    /**
     * 添加
     * @param memberBalanceActicityWithBLOBs
     * @return
     */
    @Override
    public boolean addBalanceActicity(MemberBalanceActicityWithBLOBs memberBalanceActicityWithBLOBs) {
        try {
            Integer maxid = balanceActicityMapper.selectMaxAid();
            if(maxid!=null){
                memberBalanceActicityWithBLOBs.setAcid(maxid+1);
            }else {
                memberBalanceActicityWithBLOBs.setAcid(1);
            }
            memberBalanceActicityWithBLOBs.setAdddate(new Date());
            balanceActicityMapper.insertSelective(memberBalanceActicityWithBLOBs);
            return true;
        }catch (Exception e){
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean updateBalanceActicity(MemberBalanceActicityWithBLOBs memberBalanceActicityWithBLOBs) {
        try {
            balanceActicityMapper.updateByPrimaryKeySelective(memberBalanceActicityWithBLOBs);
            return true;
        }catch (Exception e){
            e.getMessage();
        }
        return false;
    }

    @Override
    public boolean deleteBalanceActicity(String acIdStr, HttpServletRequest request) {
        try {
            String acId[] = acIdStr.split(",");
            MemberBalanceActicityWithBLOBs memberBalanceActicityWithBLOBs = new MemberBalanceActicityWithBLOBs();
            for (String string:acId){
                memberBalanceActicityWithBLOBs.setAcid(Integer.parseInt(string));
                memberBalanceActicityWithBLOBs.setIsdel(1);
                memberBalanceActicityWithBLOBs.setIp(memberService.getIp(request));
                balanceActicityMapper.updateByPrimaryKeySelective(memberBalanceActicityWithBLOBs);
            }
            return true;
        }catch (Exception e){
            e.getMessage();
        }
        return false;
    }
}
