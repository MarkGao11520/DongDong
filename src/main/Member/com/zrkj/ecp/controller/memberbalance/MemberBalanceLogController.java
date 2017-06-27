package com.zrkj.ecp.controller.memberbalance;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.memberbalance.MemberBalanceLog;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.service.memberbalance.MemberBalanceLogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by gaowenfeng on 2017/1/18.
 */
@Controller
@RequestMapping("memberBalanceLogController")
public class MemberBalanceLogController {
    @Resource
    MemberBalanceLogService memberBalanceLogService;

    @RequestMapping("memberBalanceLogList")
    public ModelAndView memberBalanceLogList(ModelAndView mv, Integer mid){
        mv.addObject("mid",mid);
        mv.setViewName("Member/memberBalance/memberBalanceLogList");
        return mv;
    }

    @RequestMapping("jsonBalanceLogList")
    @ResponseBody
    public String jsonBalanceLogList(@ModelAttribute MemberIntergralDto memberIntergralDto){
        return JSON.toJSONString(memberBalanceLogService.obtainBalanceLogList(memberIntergralDto));
    }

    /**
     * 添加余额日志
     * @param memberBalanceLog
     * @return
     */
    @RequestMapping("addBalanceLog")
    @ResponseBody
    public String addBalanceLog(MemberBalanceLog memberBalanceLog, HttpServletRequest request){
        if(memberBalanceLogService.addBalanceLog(memberBalanceLog,request)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }
}
