package com.zrkj.ecp.controller.memberintegral;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.memberintegral.MemberIntegral;
import com.zrkj.ecp.domain.memberintegral.MemberRule;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.dto.memberintegral.MemberRuleDto;
import com.zrkj.ecp.service.memberintegral.MemberIntegralService;
import com.zrkj.ecp.service.memberintegral.MemberRuleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/18.
 */
@Controller
@RequestMapping("memberIntegralController")
public class MemberIntegralController {
    @Resource
    MemberIntegralService memberIntegralService;
    @Resource
    MemberRuleService memberRuleService;

    @RequestMapping("memberIntegralList")
    public ModelAndView memberIntegralList(ModelAndView mv,Integer mid){
        mv.addObject("mid",mid);
        mv.setViewName("Member/memberIntegral/memberIntegralList");
        return mv;
    }

    @RequestMapping("jsonIntegralList")
    @ResponseBody
    public String jsonIntegralList(@ModelAttribute MemberIntergralDto memberIntergralDto, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        return JSON.toJSONString(memberIntegralService.obtainMemberIntegralList(memberIntergralDto));
    }

    /**
     * 修改积分
     * @param memberIntegral
     * @return
     */
    @RequestMapping("updateMemberIntegral")
    @ResponseBody
    public String updateMemberIntegral(MemberIntegral memberIntegral){
        if(memberIntegralService.updateMemberIntegral(memberIntegral)){
            return "updateSuccess";
        }else {
            return "updateFailed";
        }
    }

    /**
     * 添加积分
     * @param memberIntegral
     * @return
     */
    @RequestMapping("addMemberIntegral")
    @ResponseBody
    public String addMemberIntegral(MemberIntegral memberIntegral){
        if(memberIntegralService.addMemberIntegral(memberIntegral)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }

    /**
     * 唯一查找
     * @param leid
     * @return
     */
    @RequestMapping("jsonMemberIntegralOne")
    @ResponseBody
    public String jsonMemberIntegralOne(Integer leid){
        return JSON.toJSONString(memberIntegralService.obtainMemberIntegralByMid(leid));
    }

    /**
     * 界面
     * @param mv
     * @return
     */
    @RequestMapping("memberRuleList")
    public ModelAndView memberRuleList(ModelAndView mv){
        mv.setViewName("Member/memberIntegral/MemberRuleList");
        return mv;
    }

    /**
     * 列表
     * @param memberRuleDto
     * @return
     */
    @RequestMapping("jsonRuleList")
    @ResponseBody
    public String jsonLevelList(@ModelAttribute MemberRuleDto memberRuleDto){
        return JSON.toJSONString(memberRuleService.obtainMemberRule(memberRuleDto));
    }

    /**
     * 新增界面显示
     * @param mv
     * @return
     */
    @RequestMapping("addRuleInput")
    public ModelAndView addRuleInput(ModelAndView mv){
        Map<String,String> map = new HashMap<String,String>();
        map.put("url","add");
        map.put("name","新增");
        mv.addObject("code",map);
        mv.addObject("updateRule",new MemberRule());
        mv.setViewName("Member/memberIntegral/addMemberRule");
        return mv;
    }

    @RequestMapping("addRule")
    public ModelAndView addLevel(@ModelAttribute MemberRule memberRule, ModelAndView mv){
        if(memberRuleService.addMemberRule(memberRule)){
            mv.setViewName("Member/memberIntegral/MemberRuleList");
        }else {
            mv.addObject("error","添加失败");
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","add");
            map.put("name","新增");
            mv.addObject("code",map);
            mv.setViewName("Member/memberIntegral/addMemberRule");
        }
        return mv;
    }

    /**
     * 编辑界面显示
     * @param mv
     * @return
     */
    @RequestMapping("updateRuleInput")
    public ModelAndView updateRuleInput(ModelAndView mv,Integer rid){
        Map<String,String> map = new HashMap<String,String>();
        map.put("url","update");
        map.put("name","编辑");
        mv.addObject("code",map);
        mv.addObject("updateRule",memberRuleService.obtainMemberRuleById(rid));
        mv.setViewName("Member/memberIntegral/addMemberRule");
        return mv;
    }

    @RequestMapping("updateRule")
    public ModelAndView updateRule(@ModelAttribute MemberRule memberRule,ModelAndView mv){
        if(memberRuleService.updateMemberRule(memberRule)){
            mv.setViewName("Member/memberIntegral/MemberRuleList");
        }else {
            mv.addObject("error","修改失败");
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","update");
            map.put("name","修改");
            mv.addObject("code",map);
            mv.setViewName("Member/memberIntegral/addMemberRule");
        }
        return mv;
    }

    /**
     * 删除
     * @param response
     * @param rIdstr
     */
    @RequestMapping("deleteRule")
    public void deleteLevel(HttpServletResponse response,String rIdstr){
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();
            if(memberRuleService.deleteMemberRule(rIdstr)){
                out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
