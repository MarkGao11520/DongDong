package com.zrkj.ecp.controller.membersign;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.membersign.MemberSginRecord;
import com.zrkj.ecp.domain.membersign.MemberSginRule;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.dto.membersign.MemberSignRuleDto;
import com.zrkj.ecp.service.membersign.MemberSignRecordService;

import com.zrkj.ecp.service.membersign.MemberSignRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaowenfeng on 2017/1/18.
 */
@Controller
@RequestMapping("memberSignController")
public class MemberSignController {
    @Resource
    MemberSignRecordService memberSignRecordService;
    @Resource
    MemberSignRuleService memberSignRuleService;

    @RequestMapping("memberSignRecordList")
    public ModelAndView memberSignRecordList(ModelAndView mv,Integer mid){
        mv.addObject("mid",mid);
        mv.setViewName("Member/memberSign/memberSignRecordList");
        return mv;
    }

    @RequestMapping("jsonRecordList")
    @ResponseBody
    public String jsonRecordList(@ModelAttribute MemberIntergralDto memberIntergralDto, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        return JSON.toJSONString(memberSignRecordService.obtainMemberSignRecordList(memberIntergralDto));
    }


    /**
     * 添加签到记录
     * @param memberSginRecord
     * @return
     */
    @RequestMapping("addMemberSignRecord")
    @ResponseBody
    public String addMemberSignRecord(MemberSginRecord memberSginRecord, HttpServletRequest request){
        if(memberSignRecordService.addMemberSignRecord(memberSginRecord,request)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }

    /**
     * 唯一查找
     * @param reid
     * @return
     */
    @RequestMapping("jsonMemberSignRecordOne")
    @ResponseBody
    public String jsonMemberLevelOne(Integer reid){
        return JSON.toJSONString(memberSignRuleService.obtainMemberSignRuleBySrId(reid));
    }
    
    /**
     * 界面
     * @param mv
     * @return
     */
    @RequestMapping("memberSignRuleList")
    public ModelAndView memberSignRuleList(ModelAndView mv){
        mv.setViewName("Member/memberSign/MemberSignRuleList");
        return mv;
    }

    /**
     * 列表
     * @param memberSignRuleDto
     * @return
     */
    @RequestMapping("jsonSignRuleList")
    @ResponseBody
    public String jsonLevelList(@ModelAttribute MemberSignRuleDto memberSignRuleDto){
        return JSON.toJSONString(memberSignRuleService.obtainMemberSignRuleList(memberSignRuleDto));
    }

    /**
     * 新增界面显示
     * @param mv
     * @return
     */
    @RequestMapping("addSignRuleInput")
    public ModelAndView addSignRuleInput(ModelAndView mv){
        Map<String,String> map = new HashMap<String,String>();
        map.put("url","add");
        map.put("name","新增");
        mv.addObject("code",map);
        mv.addObject("updateSignRule",new MemberSginRule());
        mv.setViewName("Member/memberSign/addMemberSignRule");
        return mv;
    }

    @RequestMapping("addSignRule")
    public ModelAndView addLevel(@ModelAttribute MemberSginRule memberSignRule, ModelAndView mv){
        if(memberSignRuleService.addMemberSignRule(memberSignRule)){
            mv.setViewName("Member/memberSign/MemberSignRuleList");
        }else {
            mv.addObject("error","添加失败");
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","add");
            map.put("name","新增");
            mv.addObject("code",map);
            mv.setViewName("Member/memberSign/addMemberSignRule");
        }
        return mv;
    }

    /**
     * 编辑界面显示
     * @param mv
     * @return
     */
    @RequestMapping("updateSignRuleInput")
    public ModelAndView updateSignRuleInput(ModelAndView mv,Integer srid){
        Map<String,String> map = new HashMap<String,String>();
        map.put("url","update");
        map.put("name","编辑");
        mv.addObject("code",map);
        mv.addObject("updateSignRule",memberSignRuleService.obtainMemberSignRuleBySrId(srid));
        mv.setViewName("Member/memberSign/addMemberSignRule");
        return mv;
    }

    @RequestMapping("updateSignRule")
    public ModelAndView updateSignRule(@ModelAttribute MemberSginRule memberSignRule,ModelAndView mv){
        if(memberSignRuleService.updateMemberSignRule(memberSignRule)){
            mv.setViewName("Member/memberSign/MemberSignRuleList");
        }else {
            mv.addObject("error","修改失败");
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","update");
            map.put("name","修改");
            mv.addObject("code",map);
            mv.setViewName("Member/memberSign/addMemberSignRule");
        }
        return mv;
    }

    /**
     * 删除
     * @param response
     * @param srIdstr
     */
    @RequestMapping("deleteSignRule")
    public void deleteLevel(HttpServletResponse response,String srIdstr){
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();
            if(memberSignRuleService.deleteMemberSignRule(srIdstr)){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
