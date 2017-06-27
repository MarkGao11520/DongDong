package com.zrkj.ecp.controller.memberbalance;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.memberbalance.MemberBalanceActicity;
import com.zrkj.ecp.domain.memberbalance.MemberBalances;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;
import com.zrkj.ecp.service.memberbalance.MemberBalanceActicityService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceLogService;
import com.zrkj.ecp.service.memberbalance.MemberBalanceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by gaowenfeng on 2017/1/18.
 */
@Controller
@RequestMapping("memberBalacneController")
public class MemberBalacneController {
    @Resource
    MemberBalanceService memberBalanceService;
    @Resource
    MemberBalanceActicityService memberBalanceActicityService;
    @Resource
    MemberBalanceLogService memberBalanceLogService;

    @RequestMapping("memberBalanceList")
    public ModelAndView memberBalanceList(ModelAndView mv,Integer mid){
        mv.addObject("mid",mid);
        mv.setViewName("Member/memberBalance/memberBalanceList");
        return mv;
    }

    @RequestMapping("jsonBalanceList")
    @ResponseBody
    public String jsonBalanceList(@ModelAttribute MemberIntergralDto memberIntergralDto){
        return JSON.toJSONString(memberBalanceService.obtainMemberBalanceList(memberIntergralDto));
    }

    /**
     * 修改余额
     * @param memberBalances
     * @return
     */
    @RequestMapping("mobile/updateBalance")
    @ResponseBody
    public String updateBalance(MemberBalances memberBalances){
        if(memberBalanceService.updateMemberBalaces(memberBalances)){
//            MemberBalanceActicity memberBalanceActicity = new MemberBalanceActicity();
//            memberBalanceActicity.set
            return "updateSuccess";
        }else {
            return "updateFailed";
        }
    }

    /**
     * 添加余额
     * @param memberBalances
     * @return
     */
    @RequestMapping("mobile/addBalance")
    @ResponseBody
    public String addBalance(MemberBalances memberBalances){
        if(memberBalanceService.addMemberBalaces(memberBalances)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }

    /**
     * 唯一查找
     * @param balId
     * @return
     */
    @RequestMapping("mobile/jsonBalanceOne")
    @ResponseBody
    public String jsonBalanceOne(Integer balId){
        return JSON.toJSONString(memberBalanceService.obtainMemberBalanceById(balId));
    }
}
