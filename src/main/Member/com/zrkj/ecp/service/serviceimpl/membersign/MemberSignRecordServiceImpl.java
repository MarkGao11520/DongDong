package com.zrkj.ecp.service.serviceimpl.membersign;

import com.zrkj.ecp.domain.membersign.MemberSginRecord;
import com.zrkj.ecp.dao.membersign.MemberSginRecordMapper;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.service.member.MemberService;
import com.zrkj.ecp.service.membersign.MemberSignRecordService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberSignRecordServiceImpl implements MemberSignRecordService{
    @Resource
    MemberSginRecordMapper memberSginRecordMapper;
    @Resource
    MemberService memberService;
    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberSignRecordList(MemberIntergralDto memberIntergralDto) {
        memberIntergralDto.setPage((memberIntergralDto.getPage()-1)*memberIntergralDto.getRows());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberSginRecordMapper.selectMemberSignRecordList(memberIntergralDto));
        map.put("total",memberSginRecordMapper.selectMemberSignRecordCount(memberIntergralDto));
        return map;
    }

    /**
     * 添加
     * @param memberSginRecord
     * @return
     */
    @Override
    public boolean addMemberSignRecord(MemberSginRecord memberSginRecord, HttpServletRequest request) {
        try {
            memberSginRecord.setSignip(memberService.getIp(request));
            Integer maxid = memberSginRecordMapper.selectMaxAid();
            if(maxid!=null){
                memberSginRecord.setSignid(maxid+1);
            }else {
                memberSginRecord.setSignid(1);
            }

            memberSginRecordMapper.insertSelective(memberSginRecord);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 根据id获取
     * @param reId
     * @return
     */
    @Override
    public MemberSginRecord obtainRecordById(Integer reId) {
        return memberSginRecordMapper.selectByPrimaryKey(reId);
    }
}
