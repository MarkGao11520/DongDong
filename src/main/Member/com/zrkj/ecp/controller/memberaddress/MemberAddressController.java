package com.zrkj.ecp.controller.memberaddress;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.memberaddress.MemberAddress;
import com.zrkj.ecp.dto.memberaddress.MemberAddressdDto;
import com.zrkj.ecp.service.memberaddress.MemberAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
@Controller
@RequestMapping("memberAddressController")
public class MemberAddressController {
    @Resource
    MemberAddressService memberAddressService;

    /**
     * 添加
     * @param memberAddress
     * @return
     */
    @RequestMapping("mobile/addMemberAddress")
    @ResponseBody
    public String addMemberAddress(MemberAddress memberAddress){
        if(memberAddressService.addMemberAddress(memberAddress)){
            return RETURNCODE.SUCCESS;
        }else{
            return RETURNCODE.ADDFAILED;
        }
    }

    /**
     * 编辑
     * @param memberAddress
     * @return
     */
    @RequestMapping("mobile/updateMemberAddress")
    @ResponseBody
    public String updateMemberAddress(MemberAddress memberAddress){
        if(memberAddressService.updateMemberAddress(memberAddress)){
            return RETURNCODE.SUCCESS;
        }else{
            return RETURNCODE.UPDATEFAILED;
        }
    }

    /**
     * 删除
     * @param cmid
     * @return
     */
    @RequestMapping("mobile/delteMemberAddress")
    @ResponseBody
    public String deleteMemberAddress(String cmid){
        if(memberAddressService.deleteMemberAddress(cmid)){
            return RETURNCODE.SUCCESS;
        }else{
            return RETURNCODE.UPDATEFAILED;
        }
    }

    /**
     * 获取列表根据用户id
     * @param mid
     * @return
     */
    @RequestMapping("mobile/memberListByMid")
    @ResponseBody
    public String memberListByMid(Integer mid){
        return JSON.toJSONString(memberAddressService.obtainMemberAddressListByMid(mid));
    }

    /**
     * 获取列表根据用户id
     * @param cmid
     * @return
     */
    @RequestMapping("mobile/memberListBycmId")
    @ResponseBody
    public String memberListBycmId(Integer cmid){
        return JSON.toJSONString(memberAddressService.obtainMemberAddtressByCmId(cmid));
    }

    @RequestMapping("memberList")
    public ModelAndView memberList(ModelAndView mv,Integer mid){
        mv.addObject("mid",mid);
        mv.setViewName("Member/memberaddress/addressList");
        return mv;
    }

    /**
     * 获取列表
     * @param memberAddressdDto
     * @return
     */
    @RequestMapping("jsonmemberList")
    @ResponseBody
    public String jsonmemberList(MemberAddressdDto memberAddressdDto){
        return JSON.toJSONString(memberAddressService.obtainMemberAddressList(memberAddressdDto));
    }

    /**
     * 修改默认地址
     * @param cmid
     * @param mid
     * @return
     */
    @RequestMapping("mobile/updateDefaultAddress")
    @ResponseBody
    public String updateDefaultAddress(Integer cmid,Integer mid){
        if(memberAddressService.updateDefaultAddress(mid,cmid)){
            return RETURNCODE.SUCCESS;
        }else {
            return RETURNCODE.UPDATEFAILED;
        }
    }


}
