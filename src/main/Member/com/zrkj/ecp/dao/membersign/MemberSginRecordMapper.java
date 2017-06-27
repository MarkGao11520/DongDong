package com.zrkj.ecp.dao.membersign;

import com.zrkj.ecp.domain.membersign.MemberSginRecord;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberSginRecordMapper {
    int deleteByPrimaryKey(Integer signid);

    int insert(MemberSginRecord record);

    int insertSelective(MemberSginRecord record);

    MemberSginRecord selectByPrimaryKey(Integer signid);

    int updateByPrimaryKeySelective(MemberSginRecord record);

    int updateByPrimaryKey(MemberSginRecord record);

    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    List<MemberSginRecord> selectMemberSignRecordList(MemberIntergralDto memberIntergralDto);

    /**
     * 获取数量
     * @param memberIntergralDto
     * @return
     */
    Integer selectMemberSignRecordCount(MemberIntergralDto memberIntergralDto);

    Integer selectMaxAid();

}