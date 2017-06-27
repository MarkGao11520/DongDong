package com.zrkj.ecp.controller.memberbalance;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.memberbalance.MemberBalanceActicityWithBLOBs;
import com.zrkj.ecp.domain.memberbalance.MemberBalanceLog;
import com.zrkj.ecp.domain.memberbalance.MemberBalances;
import com.zrkj.ecp.dto.memberbalance.MemberBalancesActicityDto;
import com.zrkj.ecp.service.member.MemberService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceActicityService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceLogService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceService;
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
@RequestMapping("memberBalanceActicityController")
public class MemberBalanceActicityController {
    @Resource
    MemberBalanceActicityService memberBalanceActicityService;
    @Resource
    MemberBalanceService memberBalanceService;
    @Resource
    MemberService memberService;
    @Resource
    MemberBalanceLogService memberBalanceLogService;

    @RequestMapping("memberBalanceActicityList")
    public ModelAndView memberBalanceActicityList(ModelAndView mv, Integer baid) {
        mv.addObject("baid", baid);
        mv.setViewName("Member/memberBalance/memberBalanceActicityList");
        return mv;
    }

    @RequestMapping("jsonBalanceActicityList")
    @ResponseBody
    public String jsonBalanceList(@ModelAttribute MemberBalancesActicityDto memberBalancesActicityDto) {
        return JSON.toJSONString(memberBalanceActicityService.obtainBalcnceActicityList(memberBalancesActicityDto));
    }

    /**
     * 添加余额变更
     *
     * @param memberBalanceActicity
     * @return
     */
    @RequestMapping("mobile/addBalanceActicity")
    @ResponseBody
    public String addBalanceActicity(MemberBalanceActicityWithBLOBs memberBalanceActicity, HttpServletRequest request) {
        if (memberBalanceActicityService.addBalanceActicity(memberBalanceActicity)) {
            MemberBalances memberBalances = new MemberBalances();
            memberBalances.setBaid(memberBalanceActicity.getBaid());
            memberBalances.setBalances(memberBalanceActicity.getBalances());
            memberBalanceService.updateMemberBalaces(memberBalances);
            MemberBalanceLog memberBalanceLog = new MemberBalanceLog();
            memberBalanceLog.setMid(memberBalanceActicity.getMid());
            memberBalanceLog.setLogcontent(JSON.toJSONString(memberBalanceActicity));
            memberBalanceLogService.addBalanceLog(memberBalanceLog,request);
            return "addSuccess";
        } else {
            return "addFailed";
        }
    }
}
