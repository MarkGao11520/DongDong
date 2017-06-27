package com.zrkj.ecp.service.serviceimpl.memberintegral;

import com.zrkj.ecp.domain.memberintegral.MemberIntegral;
import com.zrkj.ecp.dao.memberintegral.MemberIntegralMapper;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.service.memberintegral.MemberIntegralService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberIntegralServiceImpl implements MemberIntegralService{
    @Resource
    MemberIntegralMapper memberIntegralMapper;

    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberIntegralList(MemberIntergralDto memberIntergralDto) {
        memberIntergralDto.setPage((memberIntergralDto.getPage()-1)*memberIntergralDto.getRows());
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberIntegralMapper.selectMemberInteList(memberIntergralDto));
        map.put("total",memberIntegralMapper.selectMemberInteCount(memberIntergralDto));
        return map;
    }

    /**
     * 根据id查找信息
     * @param mid
     * @return
     */
    @Override
    public MemberIntegral obtainMemberIntegralByMid(Integer mid) {
        return memberIntegralMapper.selectByPrimaryKey(mid);
    }

    /**
     * 添加
     * @param memberIntegral
     * @return
     */
    @Override
    public boolean addMemberIntegral(MemberIntegral memberIntegral) {
        try {
            memberIntegralMapper.insertSelective(memberIntegral);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 编辑
     * @param memberIntegral
     * @return
     */
    @Override
    public boolean updateMemberIntegral(MemberIntegral memberIntegral) {
        try {
            memberIntegralMapper.updateByPrimaryKeySelective(memberIntegral);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
