package com.zrkj.ecp.service.serviceimpl.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalanceLog;
import com.zrkj.ecp.dao.memberbalance.MemberBalanceLogMapper;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.service.member.MemberService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceLogService;

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
public class MemberBalanceLogServiceImpl implements MemberBalanceLogService{
    @Resource
    MemberBalanceLogMapper memberBalanceLogMapper;
    @Resource
    MemberService memberService;
    /**
     * 查找列表
     * @param memberIntergralDto
     * @return
     */
    @Override
    public Map<String, Object> obtainBalanceLogList(MemberIntergralDto memberIntergralDto) {
        memberIntergralDto.setPage((memberIntergralDto.getPage()-1)*memberIntergralDto.getRows());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberBalanceLogMapper.selectMemberBalancesLogList(memberIntergralDto));
        map.put("total",memberBalanceLogMapper.selectMemberBalancesLogCount(memberIntergralDto));
        return map;
    }

    /**
     * 添加
     * @param balanceLog
     * @return
     */
    @Override
    public boolean addBalanceLog(MemberBalanceLog balanceLog, HttpServletRequest request) {
        try {
            String maxid = memberBalanceLogMapper.selectMaxAid().toString();
            if(maxid!=null){
                balanceLog.setLogid((maxid+1));
            }else {
                balanceLog.setLogid("1");
            }
            balanceLog.setAdddate(new Date());
            balanceLog.setIp(memberService.getIp(request));
            memberBalanceLogMapper.insertSelective(balanceLog);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 根据id获取
     * @param logId
     * @return
     */
    @Override
    public MemberBalanceLog obtianBalanceLogById(String logId) {
        return memberBalanceLogMapper.selectByPrimaryKey(logId);
    }
}
