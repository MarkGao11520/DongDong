package com.zrkj.ecp.service.member;

import com.zrkj.ecp.domain.member.Member;
import com.zrkj.ecp.domain.member.MemberProperty;
import com.zrkj.ecp.domain.member.MemberRealName;
import com.zrkj.ecp.dto.member.EquipmentDto;
import com.zrkj.ecp.dto.member.MemberBasDeatilDto;
import com.zrkj.ecp.dto.member.MemberDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/16.
 */
public interface MemberService {
    /**
     * 获取会员列表
     * @param memberDto
     * @return
     */
    public Map<String,Object> obtainMemberList(MemberDto memberDto);

    /**
     * 根据id获取会员信息
     * @param memberId
     * @return
     */
    public Member obtainMemberByMemberId(Integer memberId);

    public MemberProperty obtainMemberPropertyByMemberId(Integer memberId,HttpServletRequest request);


    /**
     * 更新会员属性
     * @param memberProperty
     * @return
     */
    public Boolean updateMemberProperty(MemberProperty memberProperty,Member member);

    /**
     * 更改密码
     * @param oldPassword
     * @param newPassword
     * @param uname
     * @return
     */
    public Boolean updateMemberPassword(String oldPassword,String newPassword,String uname);

    /**
     * 会员注册
     * @param member
     * @return
     */
    public Boolean MemberRegister(Member member,MemberProperty memberProperty, MemberRealName memberRealName,HttpServletRequest request);

    /**
     * 删除会员
     * @param memberIdStr
     * @return
     */
    public Boolean deleteMember(String memberIdStr);

    /**
     * 登录验证
     * @param member
     * @param request
     * @return
     */
    public Map<String,Object> loginCheck(Member member, HttpServletRequest request);

    /**
     * 获取ip
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request);

    public List<EquipmentDto> obtainequipmentList(String q);

    public MemberBasDeatilDto obtaionPhoneAndAddressByPhone(String phone);

    public String excelImport(MultipartFile importfile,HttpServletRequest request);

    /**
     * 判断用户是否已经存在
     */
    public int checkMemberExists(String uname);

    /**
     * 头像上传
     * @param request
     * @param multipartFile
     * @param mid
     * @return
     */
    public Map<String,String> uploadPhoto(HttpServletRequest request,MultipartFile multipartFile,Integer mid);

    public Map<String,Object> obtainMemberByPhone(String phone);

    int addOpenId(String openid,String uname);

    int updatePhoneByOpenId(String openid,String uname);
}
