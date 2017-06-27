package com.zrkj.ecp.controller.memberbc;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.memberbc.MemberBc;
import com.zrkj.ecp.dto.memberbc.MemberBcDto;
import com.zrkj.ecp.service.memberbc.MemberBcService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by gaowenfeng on 2017/1/16.
 */
@Controller
@RequestMapping("memberBcController")
public class MemberBcController {
    @Resource
    MemberBcService memberBcService;

    @RequestMapping("memberBcList")
    public ModelAndView memberBcList(ModelAndView mv,Integer mid){
        mv.addObject("mid",mid);
        mv.setViewName("Member/memberBc/memberBcList");
        return mv;
    }

    @RequestMapping("jsonBcList")
    @ResponseBody
    public String jsonRecordList(@ModelAttribute MemberBcDto memberBcDto, HttpServletResponse response){
        response.setCharacterEncoding("utf-8");
        return JSON.toJSONString(memberBcService.obtainMemberBcList(memberBcDto));
    }

    /**
     * 修改银行卡
     * @param memberBc
     * @return
     */
    @RequestMapping("mobile/updateMemberBc")
    @ResponseBody
    public String updateMemberBc(MemberBc memberBc){
        if(memberBcService.updateMemberBc(memberBc)){
            return "updateSuccess";
        }else {
            return "updateFailed";
        }
    }

    /**
     * 添加银行卡
     * @param memberBc
     * @return
     */
    @RequestMapping("mobile/addMemberBc")
    @ResponseBody
    public String addMemberBc(MemberBc memberBc){
        if(memberBcService.addMemberBc(memberBc)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }

    /**
     * 唯一查找
     * @param bcid
     * @return
     */
    @RequestMapping("mobile/jsonMemberBcOne")
    @ResponseBody
    public String jsonMemberBcOne(Integer bcid){
        return JSON.toJSONString(memberBcService.obtainMemberBcByBcId(bcid));
    }
}
