package com.zrkj.ecp.service.serviceimpl.member;

import com.zrkj.ecp.domain.member.Member;
import com.zrkj.ecp.domain.member.MemberRealName;
import com.zrkj.ecp.dao.member.MemberMapper;
import com.zrkj.ecp.dao.member.MemberRealNameMapper;
import com.zrkj.ecp.dto.member.ImageDto;
import com.zrkj.ecp.service.member.MemberRealNameService;
import com.zrkj.ecp.untils.Untils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
@Service
public class MemberRealNameServiceImpl implements MemberRealNameService{
    @Resource
    MemberRealNameMapper memberRealNameMapper;
    @Resource
    MemberMapper memberMapper;

    /**
     * 根据mid查找会员实名信息
     * @param mid
     * @return
     */
    @Override
    public MemberRealName obtainMemberByMid(Integer mid) {
        return memberRealNameMapper.selectByPrimaryKey(mid);
    }

    /**
     * 通过审核
     * @param map
     * @param reServletRequest
     * @param session
     * @return
     */
    @Override
    public boolean throughReview(Map<String, Object> map, HttpServletRequest reServletRequest, HttpSession session) {
        try {
            if((Integer)map.get(Untils.TYPE)==Untils.ONE){
                MemberRealName memberRealname = (MemberRealName) map.get(Untils.ONEMEMBER);
                memberRealname.setCheckdate(new Date());
                memberRealname.setUpdatedate(new Date());
                memberRealNameMapper.updateByPrimaryKeySelective(memberRealname);
            }else if((Integer)map.get(Untils.TYPE)==Untils.ALL){
                String mid[] = map.get(Untils.ALLMEMBER).toString().split(",");
                for (String string : mid) {
                    MemberRealName memberRealname = new MemberRealName();
                    memberRealname.setMid(Integer.parseInt(string));
                    memberRealname.setStatus(2);
                    memberRealname.setUid((Integer.parseInt(session.getAttribute("loginUid").toString())));
                    memberRealname.setCheckdate(new Date());
                    memberRealname.setUpdatedate(new Date());
                    Member member = new Member();
                    member.setMid(Integer.parseInt(string));
                    member.setStatus(2);
                    memberMapper.updateByPrimaryKeySelective(member);
                    memberRealNameMapper.updateByPrimaryKeySelective(memberRealname);
                }
            }
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 实名验证
     * @param imageDto
     * @param memberRealname
     * @param request
     * @return
     */
    @Override
    public boolean memberRealName(ImageDto imageDto, MemberRealName memberRealname, HttpServletRequest request) {
        String fontName = UUID.randomUUID().toString()+".png";
        String backName = UUID.randomUUID().toString()+".png";
        String hardName = UUID.randomUUID().toString()+".png";
        String path = request.getServletContext().getRealPath(
                "idphoto/");
        Date timeCur = new Date();
        SimpleDateFormat fmtYY = new SimpleDateFormat("yyyy");
        SimpleDateFormat fmtMM = new SimpleDateFormat("MM");
        SimpleDateFormat fmtDD = new SimpleDateFormat("dd");
        String strYY = fmtYY.format(timeCur);
        String strMM = fmtMM.format(timeCur);
        String strDD = fmtDD.format(timeCur);
        String dataPath = "/" + strYY + "/" + strMM + "/" + strDD + "/"+memberRealname.getUid()+"/";
        String bpath = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+bpath+"/"+"idphoto"+dataPath;
        String realPath = path + dataPath ;
        File dir = new File(realPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File font = new File(realPath,fontName);
        File back = new File(realPath,backName);
        File hand = new File(realPath,hardName);
        try {
            imageDto.getIdfaceurl().transferTo(font);
            imageDto.getIdbackurl().transferTo(back);
            imageDto.getHandidurl().transferTo(hand);
            memberRealname.setIdfronturl(basePath+fontName);
            memberRealname.setIdback(basePath+backName);
            memberRealname.setHandid(basePath+hardName);
            memberRealname.setSubmitdate(new Date());
            memberRealname.setMid((Integer)(request.getSession().getAttribute("memberLoginUname")));
            memberRealNameMapper.updateByPrimaryKeySelective(memberRealname);
            request.getSession().setAttribute("userStatus", 1);
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        return false;
    }
}
