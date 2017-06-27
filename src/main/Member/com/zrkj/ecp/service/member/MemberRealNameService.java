package com.zrkj.ecp.service.member;

import com.zrkj.ecp.domain.member.MemberRealName;
import com.zrkj.ecp.dto.member.ImageDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberRealNameService {


    /**
     * 根据主键查找会员
     * @param mid
     * @return
     */
    public MemberRealName obtainMemberByMid(Integer mid);

    /**
     * 通过审核
     */
    public boolean throughReview(Map<String,Object> map, HttpServletRequest reServletRequest, HttpSession session);

    /**
     * 实名验证
     * @param imageDto
     * @param memberRealname
     */
    public boolean memberRealName(ImageDto imageDto, MemberRealName memberRealname, HttpServletRequest reServletRequest);
}
