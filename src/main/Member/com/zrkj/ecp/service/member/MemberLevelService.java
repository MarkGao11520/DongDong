package com.zrkj.ecp.service.member;

import com.zrkj.ecp.domain.member.MemberLevel;
import com.zrkj.ecp.dto.member.MemberLevelDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberLevelService {
    /**
     * 获取会员等级列表
     * @param memberLevelDto
     * @return
     */
    public Map<String,Object> obtainMemberLevelList(MemberLevelDto memberLevelDto);

    /**
     * 根据等级id获取会员等级
     * @param leId
     * @return
     */
    public MemberLevel obtainMemberLevelByLeId(Integer leId);

    /**
     * 添加
     * @param memberLevel
     * @return
     */
    public boolean addMemberLevel(MemberLevel memberLevel);

    /**
     * 编辑
     * @param memberLevel
     * @return
     */
    public boolean updateMemberLevel(MemberLevel memberLevel);

    /**
     * 删除
     * @param memberLevelStr
     * @return
     */
    public boolean deleteMemberLevel(String memberLevelStr);
}
