package com.zrkj.ecp.controller.app;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.dto.app.AppDto;
import com.zrkj.ecp.service.app.BasAppService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("appController")
public class appController {
	@Resource
	BasAppService basAppService;
	@Resource
	BasUserService basUserService;

	/**
	 * 添加显示
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping("addAppInput")
	public ModelAndView addAppInput(ModelAndView mv) {
		mv.addObject("code", "addApp");
		mv.addObject("updateApp",new BasApp());
		mv.setViewName("System/app/addApp");
		return mv;
	}

	/**
	 * 添加应用
	 * 
	 * @param mv
	 * @param basApp
	 * @return
	 */
	@RequestMapping(value = "addApp", method = RequestMethod.POST)
	public ModelAndView addApp(ModelAndView mv, @ModelAttribute BasApp basApp,
							   HttpSession session) {

		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("应用管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的应用：" + JSON.toJSONString(basApp));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		basAppService.addApp(basApp);
		mv.addObject("AppMap", basAppService.appList(new AppDto()));
		mv.setViewName("System/app/AppList");
		return mv;
	}

	/**
	 * 查找应用列表
	 * 
	 * @param appDto
	 * @return
	 */
	@RequestMapping(value = "jsonAppList")
	@ResponseBody
	public String jsonAppList(@ModelAttribute AppDto appDto){
		return JSON.toJSONString(basAppService.appList(appDto));
	}
	
	@RequestMapping("appList")
	public ModelAndView appList(ModelAndView mv) {
		mv.setViewName("System/app/AppList");
		return mv;
	}

	/**
	 * 修改输入
	 * 
	 * @param mv
	 * @param appid
	 * @return
	 */
	@RequestMapping(value = "updateAppInput", method = RequestMethod.GET)
	public ModelAndView updateAppInput(ModelAndView mv, Integer appid) {
		mv.addObject("updateApp", basAppService.selectAppByAppId(appid));
		mv.addObject("code", "updateApp");
		mv.setViewName("System/app/addApp");
		return mv;
	}

	/**
	 * 修改
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "updateApp", method = RequestMethod.POST)
	public ModelAndView updateApp(ModelAndView mv, @ModelAttribute BasApp basApp, HttpSession session, HttpServletRequest request) {
		Map<String,String> map = basAppService.uploadAPK(request,basApp.getApk(),basApp.getAppid());
		if(Boolean.parseBoolean(map.get("isSuccess").toString().trim())){
			basApp.setAppurl(map.get("url").toString());
		}else{
			mv.addObject("updateApp", basAppService.selectAppByAppId(basApp.getAppid()));
			mv.addObject("code", "updateApp");
			mv.addObject("error","1");
			mv.setViewName("System/app/addApp");
			return mv;
		}
		basAppService.updateApp(basApp);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("应用管理");
		basLog.setActionname("修改");
		basApp.setApk(null);
		basLog.setContent("修改内容:" + JSON.toJSONString(basApp));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.addObject("AppMap", basAppService.appList(new AppDto()));
		mv.setViewName("System/app/AppList");
		return mv;
	}

	/**
	 * 删除
	 * 
	 * @param allappid
	 */
	@RequestMapping(value = "deleteApp", method = RequestMethod.POST)
	public void deleteApp(String allappid, HttpServletResponse response,
			HttpSession session) {
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			BasLog basLog = (BasLog) session.getAttribute("log");
			basLog.setModname("应用管理");
			basLog.setActionname("删除");
			String result = basAppService.deleteApp(allappid);
			basLog.setContent("删除id:" + allappid + "删除结果：" + result);
			basLog.setCreatedate(new Date());
			basUserService.insertlog(basLog);
			out.print(result);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
