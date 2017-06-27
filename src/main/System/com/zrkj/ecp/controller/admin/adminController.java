package com.zrkj.ecp.controller.admin;

import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.mod.BasMod;
import com.zrkj.ecp.domain.user.BasUser;
import com.zrkj.ecp.domain.user.SysUser;
import com.zrkj.ecp.dto.app.AppDto;
import com.zrkj.ecp.service.app.BasAppService;
import com.zrkj.ecp.service.mod.BasModService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("adminController")
public class adminController {
    @Resource
    BasUserService basUserService;
    @Resource
    BasAppService basAppService;
    @Resource
    BasModService basModService;


    @RequestMapping("jsonMod")
    @ResponseBody
    public List<BasMod> jsonMod(String appid){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer uid;
        SysUser sysUser = new SysUser();
        if (principal instanceof UserDetails) {
            sysUser = (SysUser) principal;
        }
        uid = Integer.parseInt(sysUser.getId().toString());
       // System.out.println(appid);
       // System.out.println(basModService.obtainModListByAppId(Integer.parseInt(appid),Integer.parseInt((String) session.getAttribute("loginUid"))));
        return basModService.obtainModListByAppId(Integer.parseInt(appid),uid);
    }

//    /**
//     * 登录验证
//     *
//     * @param mv
//     * @return
//     */
//    @RequestMapping("mainPage")
//    public ModelAndView login(ModelAndView mv, HttpServletRequest request) {
//        List<BasApp> list = basUserService.loginCheck(request);
//        mv.addObject("appList", list);
//        mv.setViewName("System/admin/main");
//        return mv;
//    }


}
