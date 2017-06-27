package com.zrkj.ecp.service.serviceimpl.memberbc;

import com.zrkj.ecp.domain.memberbc.MemberBc;
import com.zrkj.ecp.dao.memberbc.MemberBcMapper;
import com.zrkj.ecp.dto.memberbc.MemberBcDto;
import com.zrkj.ecp.service.memberbc.MemberBcService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberBcServiceImpl implements MemberBcService{
    @Resource
    MemberBcMapper memberBcMapper;
    /**
     * 获取列表
     * @param memberBcDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberBcList(MemberBcDto memberBcDto) {
        memberBcDto.setPage((memberBcDto.getPage()-1)*memberBcDto.getRows());
        if(memberBcDto.getUserName()!=null){
            memberBcDto.setUserName("%"+memberBcDto.getUserName()+"%");
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberBcMapper.selectMemberBcList(memberBcDto));
        map.put("total",memberBcMapper.selectMemberBcCount(memberBcDto));
        return map;
    }

    /**
     * 根据id获取信息
     * @param bcId
     * @return
     */
    @Override
    public MemberBc obtainMemberBcByBcId(Integer bcId) {
        return memberBcMapper.selectByPrimaryKey(bcId);
    }

    /**
     * 添加
     * @param memberBc
     * @return
     */
    @Override
    public boolean addMemberBc(MemberBc memberBc) {
        try {
            Integer maxid = memberBcMapper.selectMaxAid();
            if(maxid!=null){
                memberBc.setBcid(maxid+1);
            }else {
                memberBc.setBcid(1);
            }

            memberBc.setAdddate(new Date());
            memberBcMapper.insertSelective(memberBc);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 编辑
     * @param memberBc
     * @return
     */
    @Override
    public boolean updateMemberBc(MemberBc memberBc) {
        try {
            memberBcMapper.updateByPrimaryKeySelective(memberBc);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     * @param bcStr
     * @return
     */
    @Override
    public boolean deleteMemberBc(String bcStr) {
        try {
            String bcId[] = bcStr.split(",");
            MemberBc memberBc = new MemberBc();
            for (String string : bcId){
                memberBc.setBcid(Integer.parseInt(string));
                memberBc.setIsdel(1);
                memberBcMapper.updateByPrimaryKeySelective(memberBc);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
