package com.zrkj.ecp.dao.member;

import com.zrkj.ecp.domain.member.MemberLevel;
import com.zrkj.ecp.dto.member.MemberLevelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberLevelMapper {
    int deleteByPrimaryKey(Integer leid);

    int insert(MemberLevel record);

    int insertSelective(MemberLevel record);

    MemberLevel selectByPrimaryKey(Integer leid);

    int updateByPrimaryKeySelective(MemberLevel record);

    int updateByPrimaryKey(MemberLevel record);

    /**
     * 获取会员等级列表
     * @param memberLevelDto
     * @return
     */
    List<MemberLevel> selectMemberLebelList(MemberLevelDto memberLevelDto);

    /**
     * 获取会员等级数量
     * @param memberLevelDto
     * @return
     */
    Integer selectMemberLevelCount(MemberLevelDto memberLevelDto);

    Integer selectMaxAid();
}