package com.zrkj.ecp.controller.member;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.member.MemberLevel;
import com.zrkj.ecp.dto.member.MemberLevelDto;
import com.zrkj.ecp.service.member.MemberLevelService;
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
@RequestMapping("memberLevelController")
public class MemberLevelController {
    @Resource
    MemberLevelService memberLevelService;

    /**
     * 界面
     * @param mv
     * @return
     */
    @RequestMapping("memberLevelList")
    public ModelAndView memberLebelList(ModelAndView mv){
        mv.setViewName("Member/memberlevel/MemberLevelList");
        return mv;
    }

    /**
     * 列表
     * @param memberLevelDto
     * @return
     */
    @RequestMapping("jsonLevelList")
    @ResponseBody
    public String jsonLevelList(@ModelAttribute MemberLevelDto memberLevelDto){
        return JSON.toJSONString(memberLevelService.obtainMemberLevelList(memberLevelDto));
    }

    /**
     * 新增界面显示
     * @param mv
     * @return
     */
    @RequestMapping("addLevelInput")
    public ModelAndView addLevelInput(ModelAndView mv){
        Map<String,String> map = new HashMap<String,String>();
        map.put("url","add");
        map.put("name","新增");
        mv.addObject("code",map);
        mv.addObject("updateLevel",new MemberLevel());
        mv.setViewName("Member/memberlevel/addMemberLevel");
        return mv;
    }

    @RequestMapping("addLevel")
    public ModelAndView addLevel(@ModelAttribute MemberLevel memberLevel,ModelAndView mv){
        if(memberLevelService.addMemberLevel(memberLevel)){
            mv.setViewName("Member/memberlevel/MemberLevelList");
        }else {
            mv.addObject("error","添加失败");
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","add");
            map.put("name","新增");
            mv.addObject("code",map);
            mv.setViewName("Member/memberlevel/addMemberLevel");
        }
        return mv;
    }

    /**
     * 新增界面显示
     * @param mv
     * @return
     */
    @RequestMapping("updateLevelInput")
    public ModelAndView updateLevelInput(ModelAndView mv,Integer leid){
        Map<String,String> map = new HashMap<String,String>();
        map.put("url","update");
        map.put("name","编辑");
        mv.addObject("code",map);
        mv.addObject("updateLevel",memberLevelService.obtainMemberLevelByLeId(leid));
        mv.setViewName("Member/memberlevel/addMemberLevel");
        return mv;
    }

    @RequestMapping("updateLevel")
    public ModelAndView updateLevel(@ModelAttribute MemberLevel memberLevel,ModelAndView mv){
        if(memberLevelService.updateMemberLevel(memberLevel)){
            mv.setViewName("Member/memberlevel/MemberLevelList");
        }else {
            mv.addObject("error","修改失败");
            Map<String,String> map = new HashMap<String,String>();
            map.put("url","update");
            map.put("name","修改");
            mv.addObject("code",map);
            mv.setViewName("Member/memberlevel/addMemberLevel");
        }
        return mv;
    }

    /**
     * 删除
     * @param response
     * @param leIdstr
     */
    @RequestMapping("deleteLevel")
    public void deleteLevel(HttpServletResponse response,String leIdstr){
        response.setCharacterEncoding("utf-8");
        try {
            PrintWriter out = response.getWriter();
            if(memberLevelService.deleteMemberLevel(leIdstr)){
            out.print("删除成功");
            }else {
                out.print("删除失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改等级
     * @param memberLevel
     * @return
     */
    @RequestMapping("updateMemberLevel")
    @ResponseBody
    public String updateMemberLevel(MemberLevel memberLevel){
        if(memberLevelService.updateMemberLevel(memberLevel)){
            return "updateSuccess";
        }else {
            return "updateFailed";
        }
    }

    /**
     * 添加等级
     * @param memberLevel
     * @return
     */
    @RequestMapping("addMemberLevel")
    @ResponseBody
    public String addMemberLevel(MemberLevel memberLevel){
        if(memberLevelService.addMemberLevel(memberLevel)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }

    /**
     * 唯一查找
     * @param levelid
     * @return
     */
    @RequestMapping("jsonMemberLevelOne")
    @ResponseBody
    public String jsonMemberLevelOne(Integer levelid){
        return JSON.toJSONString(memberLevelService.obtainMemberLevelByLeId(levelid));
    }
}
