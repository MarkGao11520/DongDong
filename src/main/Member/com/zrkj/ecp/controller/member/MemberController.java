package com.zrkj.ecp.controller.member;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.datadictionary.DataDictionary;
import com.zrkj.ecp.datadictionary.DataObject;
import com.zrkj.ecp.domain.member.Member;
import com.zrkj.ecp.domain.member.MemberProperty;
import com.zrkj.ecp.domain.member.MemberRealName;
import com.zrkj.ecp.dto.member.*;
import com.zrkj.ecp.service.member.MemberRealNameService;
import com.zrkj.ecp.service.member.MemberService;
import com.zrkj.ecp.service.serviceimpl.expresssms.RandomUtils;
import com.zrkj.ecp.service.serviceimpl.expresssms.SmsBase;
import com.zrkj.ecp.untils.Untils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/1/18.
 */
@Controller
@RequestMapping("memberController")
public class MemberController {
    @Resource
    MemberService memberService;
    @Resource
    MemberRealNameService memberRealNameService;
    @Resource
    SmsBase smsBase;

    /**
     * 返回会员列表界面
     *
     * @param mv
     * @return
     */
    @RequestMapping("memberList")
    public ModelAndView memberList(ModelAndView mv) {
        mv.setViewName("Member/member/memberList");
        return mv;
    }

    /**
     * 获取会员列表
     *
     * @param memberDto
     * @param response
     * @return
     */
    @RequestMapping("jsonMemberList")
    @ResponseBody
    public String jsonMemberList(@ModelAttribute MemberDto memberDto, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        return JSON.toJSONString(memberService.obtainMemberList(memberDto));
    }

    /**
     * excel导入
     *
     * @param importFile
     * @return
     */
    @RequestMapping("excelImport")
    @ResponseBody
    public String excelImport(MultipartFile importFile, HttpServletRequest request) {
//        System.out.println(importFile.getOriginalFilename());
//        System.out.println(importFile.getContentType());
        return memberService.excelImport(importFile, request);
    }

    /**
     * 会员实名信息详情
     *
     * @param mv
     * @param mid
     * @return
     */
    @RequestMapping("memberRealNameDetail")
    public ModelAndView memberRealNameDetail(ModelAndView mv, Integer mid, HttpServletRequest request) {
        mv.addObject("memberDetail", memberRealNameService.obtainMemberByMid(mid));
        mv.addObject("memberPDetail", memberService.obtainMemberPropertyByMemberId(mid, request));
        mv.setViewName("Member/member/memberRealNamedetail");
        return mv;
    }

    /**
     * 会员详情
     *
     * @param mv
     * @param mid
     * @return
     */
    @RequestMapping("memberDetail")
    public ModelAndView memberDetail(ModelAndView mv, Integer mid, HttpServletRequest request) {
        mv.addObject("updateUser", memberService.obtainMemberByMemberId(mid));
        mv.addObject("updateUserPro", memberService.obtainMemberPropertyByMemberId(mid, request));
        mv.setViewName("Member/member/MemberDetail");
        return mv;
    }

    /**
     * 批量通过审核
     *
     * @param midstr
     * @param request
     * @param session
     * @param response
     * @return
     */
    @RequestMapping("throughReviewAll")
    @ResponseBody()
    public String throughReview(String midstr, HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Untils.TYPE, Untils.ALL);
        map.put(Untils.ALLMEMBER, midstr);
        if (memberRealNameService.throughReview(map, request, session)) {
            return "true";
        } else {
            return "false";
        }
    }

    /**
     * 通过审核
     *
     * @param mv
     * @param memberRealname
     * @param request
     * @param session
     * @return
     */
    @RequestMapping("throughReview")
    public ModelAndView throughReview(ModelAndView mv, @ModelAttribute MemberRealName memberRealname, HttpServletRequest request, HttpSession session) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(Untils.TYPE, Untils.ONE);
        map.put(Untils.ONEMEMBER, memberRealname);

        if (memberRealNameService.throughReview(map, request, session)) {
            mv.setViewName("Member/member/memberList");
        } else {
            mv.addObject("error", "审核失败");
            mv.addObject("memberDetail", memberRealNameService.obtainMemberByMid(memberRealname.getMid()));
            mv.addObject("memberPDetail", memberService.obtainMemberPropertyByMemberId(memberRealname.getMid(), request));
            mv.setViewName("Member/member/memberRealNamedetail");
        }
        return mv;
    }

    /**
     * 会员登录
     *
     * @param member
     * @param request
     * @return
     */
    @RequestMapping("mobile/memberLogin")
    @ResponseBody
    public String memberLogin(@ModelAttribute Member member, HttpServletRequest request) {
        Map<String, Object> map = memberService.loginCheck(member, request);
//        if((Boolean) map.get("loginCheck")){
//            return RETURNCODE.SUCCESS;
//        }else {
//            return RETURNCODE.PASSWORDERROR;
//        }
        return JSON.toJSONString(map);
    }

    /**
     * 会员注册
     *
     * @param member
     * @param memberProperty
     * @param memberRealName
     * @return
     */
    @RequestMapping("mobile/memberRegister")
    @ResponseBody
    public String memberRegister(@ModelAttribute Member member, @ModelAttribute MemberProperty memberProperty, @ModelAttribute MemberRealName memberRealName, String msgcode, String id, HttpServletRequest request) {
//        System.out.println(DataDictionary.DataMap.get(id).getContent());
        if (msgcode.equals(DataDictionary.DataMap.get(id).getContent())) {
            if (memberService.MemberRegister(member, memberProperty, memberRealName, request)) {
                return RETURNCODE.SUCCESS;
            } else {
                return RETURNCODE.REGISTERFAIELD;
            }
        } else {
            return RETURNCODE.MSGCODEERROR;
        }
    }

    /**
     * 更改会员属性
     *
     * @param memberProperty
     * @return
     */
    @RequestMapping("mobile/updateMemberProperty")
    @ResponseBody
    public String updateMemberProperty(MemberProperty memberProperty, Member member) {
        if (memberService.updateMemberProperty(memberProperty, member)) {
            return RETURNCODE.SUCCESS;
        } else {
            return RETURNCODE.UPDATEFAILED;
        }
    }

    /**
     * 修改密码
     *
     * @param password
     * @param oldPassword
     * @param uname
     * @return
     */
    @RequestMapping("mobile/updatePassword")
    @ResponseBody
    public String updatePassword(String password, String oldPassword, String uname) {

        if (memberService.updateMemberPassword(oldPassword, password, uname)) {
            return RETURNCODE.SUCCESS;
        } else {
            return RETURNCODE.UPDATEFAILED;
        }

    }


    @RequestMapping("mobile/checkMobileCode")
    @ResponseBody
    public String checkMobileCode(String msgcode, String id) {
        try {
            if (msgcode.equals(DataDictionary.DataMap.get(id).getContent())) {
                return RETURNCODE.SUCCESS;
            } else {
                return RETURNCODE.MSGCODEERROR;
            }
        }catch (Exception e){
            return RETURNCODE.MSGCODEERROR;
        }

    }

    /**
     * 实名验证
     *
     * @param imageDto
     * @param memberRealName
     * @param request
     * @return
     */
    @RequestMapping("mobile/realName")
    @ResponseBody
    public String realName(@ModelAttribute ImageDto imageDto, @ModelAttribute MemberRealName memberRealName, HttpServletRequest request) {
        if (memberRealNameService.memberRealName(imageDto, memberRealName, request)) {
            return RETURNCODE.SUCCESS;
        } else {
            return RETURNCODE.REALNAMEFAILED;
        }
    }

    /**
     * 会员实名信息
     *
     * @param mid
     * @return
     */
    @RequestMapping("mobile/realNameDetail")
    @ResponseBody
    public String realNameDetail(Integer mid) {
        return JSON.toJSONString(memberRealNameService.obtainMemberByMid(mid));
    }

    /**
     * 会员详情
     *
     * @param mid
     * @return
     */
    @RequestMapping("mobile/jsonMemberDetail")
    @ResponseBody
    public String memberDetail(Integer mid, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Member", memberService.obtainMemberByMemberId(mid));
        map.put("MemberPro", memberService.obtainMemberPropertyByMemberId(mid, request));
        return JSON.toJSONString(map);
    }

    /**
     * 模糊查询
     *
     * @param searchDTO
     * @param response
     * @return
     */
    @RequestMapping("getEquipmentList")
    @ResponseBody
    public String getEquipmentList(SearchDTO searchDTO, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        String id = searchDTO.getId();
        String keyWord = searchDTO.getQ();
        if (id == null && keyWord == "" && keyWord == null) {
            return "";
        }
        if (id != null) { // 修改时传入id
            List<EquipmentDto> equipmentList = new ArrayList<EquipmentDto>(1);// 这里需要返回一个jsonArray
            EquipmentDto equipmentDto = new EquipmentDto();
            equipmentDto.setId(keyWord);
            equipmentDto.setText(keyWord);
            equipmentList.add(equipmentDto);
            return JSON.toJSONString(equipmentDto);
        } else {
            if (searchDTO.getQ().length() >= 4) {
                List<EquipmentDto> equipmentList = memberService.obtainequipmentList(searchDTO.getQ());
                return JSON.toJSONString(equipmentList);
            } else {
                return null;
            }

        }
    }

    /**
     * 根据会员姓名获取地址和电话
     *
     * @param phone
     * @return
     */
    @RequestMapping("mobile/obationMemberNameAndAddressByPhone")
    @ResponseBody
    public MemberBasDeatilDto obationMemberNameAndAddressByPhone(String phone) {
        return memberService.obtaionPhoneAndAddressByPhone(phone);
    }

    /**
     * 验证码
     *
     * @param phone
     * @return
     */
    @RequestMapping("mobile/mobileCheckCode")
    @ResponseBody
    public String mobileCheckCode(String phone, Integer code) {
        int isexist = memberService.checkMemberExists(phone);
        if (code != null) {
            if (code == 1) {
                if(isexist==0){
                    return "-100";
                }else {
                    isexist = 1;
                }
            }
        }
        if (isexist >= 0) {
            try {
                String msgcode = RandomUtils.msgCode();
                String content = "感谢您使用驯鹿，您当前的验证码为:" + msgcode + ",有效时间为60秒，请在时效内使用。如非本人发起请与我们联系。";
                System.out.println("开始发短信");
                if (Integer.parseInt(smsBase.SendSms(phone, content)) > 0) {
                    System.out.println("结束发短信");
                    DataObject dataObject = new DataObject();
                    dataObject.setCreateTime(new Date());
                    dataObject.setContent(msgcode);
                    String id = UUID.randomUUID().toString();
                    DataDictionary.DataMap.put(id, dataObject);
                    return id;
                }
                return RETURNCODE.SENDMSGCODEFAILED;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return RETURNCODE.SENDMSGCODEFAILED;
        } else {
            return RETURNCODE.USEREXISTS;
        }

    }


    /**
     * 上传头像
     *
     * @param request
     * @param photo
     * @param mid
     * @return
     */
    @RequestMapping("uploadPhoto")
    @ResponseBody
    public String uploadPhoto(HttpServletRequest request, MultipartFile photo, Integer mid) {
        Map<String,String> map = memberService.uploadPhoto(request, photo, mid);
        return JSON.toJSONString(map);
    }


    @RequestMapping("mobile/addWeiXinUser")
    @ResponseBody
    public int addWeiXinUser(String phone,String openid){
        if(phone!=null&&openid!=null){
            return memberService.addOpenId(openid,phone);
        }else{
            return 0;
        }
    }

    @RequestMapping("mobile/updatePhoneByOpenId")
    @ResponseBody
    public int updatePhoneByOpenId(String phone,String openid){
        if(phone!=null&&openid!=null){
            return memberService.updatePhoneByOpenId(openid,phone);
        }else{
            return 0;
        }
    }
}
