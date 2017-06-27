package com.zrkj.ecp.service.serviceimpl.member;

import com.zrkj.ecp.domain.member.MemberLevel;
import com.zrkj.ecp.dao.member.MemberLevelMapper;
import com.zrkj.ecp.dto.member.MemberLevelDto;
import com.zrkj.ecp.service.member.MemberLevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberLevelServiceImpl implements MemberLevelService{
    @Resource
    MemberLevelMapper memberLevelMapper;
    /**
     * 获取会员等级列表
     * @param memberLevelDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberLevelList(MemberLevelDto memberLevelDto) {
        memberLevelDto.setPage((memberLevelDto.getPage()-1)*memberLevelDto.getRows());
        if(memberLevelDto.getLeName()!=null){
            memberLevelDto.setLeName("%"+memberLevelDto.getLeName()+"%");
        }
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("rows",memberLevelMapper.selectMemberLebelList(memberLevelDto));
        map.put("total",memberLevelMapper.selectMemberLevelCount(memberLevelDto));
        return map;
    }

    /**
     * 根据leId获取等级
     * @param leId
     * @return
     */
    @Override
    public MemberLevel obtainMemberLevelByLeId(Integer leId) {
        return memberLevelMapper.selectByPrimaryKey(leId);
    }

    @Override
    public boolean addMemberLevel(MemberLevel memberLevel) {
        try {
            Integer maxid = memberLevelMapper.selectMaxAid();
            if(maxid!=null) {
                memberLevel.setLeid(maxid + 1);
            }else{
                memberLevel.setLeid(1);
            }
            memberLevel.setAdddate(new Date());
            memberLevelMapper.insertSelective(memberLevel);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateMemberLevel(MemberLevel memberLevel) {
        try {
            memberLevelMapper.updateByPrimaryKeySelective(memberLevel);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteMemberLevel(String memberLevelStr) {
        try {
            String leId[] = memberLevelStr.split(",");
            MemberLevel memberlevel = new MemberLevel();
            for (String string : leId) {
                memberlevel.setLeid(Integer.parseInt(string));
                memberlevel.setIsdel(1);
                memberLevelMapper.updateByPrimaryKeySelective(memberlevel);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
