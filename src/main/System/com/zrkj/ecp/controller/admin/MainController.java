package com.zrkj.ecp.controller.admin;

import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.service.app.BasAppService;
import com.zrkj.ecp.service.mod.BasModService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by gaowenfeng on 2017/3/21.
 */
@Controller
public class MainController {
    @Resource
    BasUserService basUserService;
    @Resource
    BasAppService basAppService;
    @Resource
    BasModService basModService;
    @RequestMapping("/")
    public ModelAndView login(ModelAndView mv, HttpServletRequest request) {
        List<BasApp> list = basUserService.loginCheck(request);
        mv.addObject("appList", list);
        mv.setViewName("System/admin/main");
        return mv;
    }
}
