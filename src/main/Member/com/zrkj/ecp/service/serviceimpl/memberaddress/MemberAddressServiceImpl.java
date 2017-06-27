package com.zrkj.ecp.service.serviceimpl.memberaddress;

import com.zrkj.ecp.dao.member.MemberPropertyMapper;
import com.zrkj.ecp.dao.memberaddress.MemberAddressMapper;
import com.zrkj.ecp.domain.member.MemberProperty;
import com.zrkj.ecp.domain.memberaddress.MemberAddress;
import com.zrkj.ecp.dto.memberaddress.MemberAddressdDto;
import com.zrkj.ecp.service.memberaddress.MemberAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
@Service
public class MemberAddressServiceImpl implements MemberAddressService{
    @Resource
    MemberAddressMapper memberAddressMapper;
    @Resource
    MemberPropertyMapper memberPropertyMapper;

    /**
     * 添加
     * @param memberAddress
     * @return
     */
    @Override
    public Boolean addMemberAddress(MemberAddress memberAddress) {
        try {
            Integer mid = memberAddressMapper.selectMaxAid();
            if (mid == null) {
                memberAddress.setAmid(1);
            } else {
                memberAddress.setAmid(mid + 1);
            }
            memberAddress.setAdddate(new Date());
            memberAddressMapper.insertSelective(memberAddress);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 编辑
     * @param memberAddress
     * @return
     */
    @Override
    public Boolean updateMemberAddress(MemberAddress memberAddress) {
        try {
            memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     * @param cmIdStr
     * @return
     */
    @Override
    public Boolean deleteMemberAddress(String cmIdStr) {
        try {
            String cmId[] = cmIdStr.split(",");
            for (String string :cmId){
                MemberAddress memberAddress = new MemberAddress();
                memberAddress.setAmid(Integer.parseInt(string));
                memberAddress.setIsdel(1);
                memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * 根据用户id获取类别
     * @param mid
     * @return
     */
    @Override
    public List<MemberAddress> obtainMemberAddressListByMid(Integer mid) {
        return memberAddressMapper.selectMemberAddressListByMid(mid);
    }

    /**
     * 获取列表
     * @param memberAddressdDto
     * @return
     */
    @Override
    public Map<String, Object> obtainMemberAddressList(MemberAddressdDto memberAddressdDto) {
        memberAddressdDto.setPage((memberAddressdDto.getPage()-1)*memberAddressdDto.getRows());
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("total",memberAddressMapper.selectMemberAddressCount(memberAddressdDto));
        map.put("rows",memberAddressMapper.selectMemberAddressList(memberAddressdDto));
        return map;
    }

    /**
     * 更改默认地址
     * @param mid
     * @param cmid
     * @return
     */
    @Override
    public Boolean updateDefaultAddress(Integer mid, Integer cmid) {
        try {
            memberAddressMapper.updateDefaultAddress(mid);
            MemberAddress memberAddress = new MemberAddress();
            memberAddress.setAmid(cmid);
            memberAddress.setIsdefault(0);
            memberAddressMapper.updateByPrimaryKeySelective(memberAddress);
            MemberAddress memberAddress1 = memberAddressMapper.selectByPrimaryKey(cmid);
            MemberProperty memberProperty = new MemberProperty();
            memberProperty.setMid(mid);
            memberProperty.setAddress(memberAddress1.getAddress()==null?"":memberAddress1.getAddress());
            memberPropertyMapper.updateByPrimaryKeySelective(memberProperty);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 唯一查询
     * @param cmid
     * @return
     */
    @Override
    public MemberAddress obtainMemberAddtressByCmId(Integer cmid) {
        return memberAddressMapper.selectByPrimaryKey(cmid);
    }
}
