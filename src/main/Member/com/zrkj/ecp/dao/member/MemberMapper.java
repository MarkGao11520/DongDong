package com.zrkj.ecp.dao.member;

import com.zrkj.ecp.domain.member.Member;
import com.zrkj.ecp.domain.member.MemberInfo;
import com.zrkj.ecp.dto.member.EquipmentDto;
import com.zrkj.ecp.dto.member.MemberBasDeatilDto;
import com.zrkj.ecp.dto.member.MemberDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {
    int deleteByPrimaryKey(Integer mid);

    int insert(Member record);

    int insertSelective(Member record);

    Member selectByPrimaryKey(Integer mid);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);

    /**
     * 获取会员列表
     * @param memberDto
     * @return
     */
    List<MemberInfo> selectMemberList(@Param("memberDto") MemberDto memberDto);

    /**
     * 获取会员总数
     * @param memberDto
     * @return
     */
    Integer selectMemberCount(MemberDto memberDto);

    /**
     * 修改密码
     * @param oldPassword
     * @param uname
     * @param newPassword
     * @return
     */
    int updateMemberPassWord(@Param("oldPassword")String oldPassword,@Param("uname")String uname,@Param("newPassword")String newPassword);

    /**
     * 登录验证
     */
    Integer loginCheck(Member member);

    Integer selectMaxAid();

    List<EquipmentDto> selectequipmentList(String q);

    List<MemberBasDeatilDto> selectNameAndAdressByPhone(String phone);

    /**
     * 判断用户是否已经存在
     * @param uname
     * @return
     */
    Member checkMemberExists(String uname);

    int updateOpenIdByPhone(@Param("openid")String openid,@Param("uname")String uname);

    int updatePhoneByOpenId(@Param("openid")String openid,@Param("uname")String uname);


}