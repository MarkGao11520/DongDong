package com.zrkj.ecp.controller.sys.basic;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.sys.basic.BasArea;
import com.zrkj.ecp.dto.sys.basic.AreaDto;
import com.zrkj.ecp.dto.sys.basic.PAreaDto;
import com.zrkj.ecp.service.sys.basic.BasAreaService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by gaowenfeng on 2016/12/21.
 */
@Controller
@RequestMapping(value = "AreaManagent")
public class AreaManagentController {
	@Resource
	BasAreaService basAreaService;
	@Resource
	BasUserService basUserService;

	/**
	 * 跳转添加地区界面
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping("/addAreaInput")
	public ModelAndView addAreaInput(ModelAndView mv, BasArea basArea,Integer code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "add");
		map.put("name", "新增");
		mv.addObject("code", map);
		if(code==null) {
			basArea.setZonetype(String.valueOf(Integer.parseInt(basArea.getZonetype()) + 1));
		}
		List<PAreaDto> list = new ArrayList<PAreaDto>();
		PAreaDto pAreaDto = new PAreaDto();
		pAreaDto.setPid(basArea.getAid());
		pAreaDto.setPname(basArea.getAname());
		list.add(pAreaDto);
		mv.addObject("plist", list);
		basArea.setAname(null);
		basArea.setAid(null);
		mv.addObject("updateArea", basArea);
		mv.setViewName("System/sys/basic/addArea");
		return mv;
	}

	/**
	 * 添加地区
	 * 
	 * @param mv
	 * @param basArea
	 * @return
	 */
	@RequestMapping(value = "/addArea", method = RequestMethod.POST)
	public ModelAndView addArea(ModelAndView mv, @ModelAttribute BasArea basArea, HttpSession session) {
		basAreaService.addArea(basArea);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("地区管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的地区：" + JSON.toJSONString(basArea));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.addObject("areaMap", basAreaService.selectAreaList(new AreaDto()));
		mv.setViewName("System/sys/basic/areaList");
		return mv;
	}

	/**
	 * 地区列表
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/areaList", method = RequestMethod.GET)
	public ModelAndView areaList(ModelAndView mv) {
		mv.setViewName("System/sys/basic/areaList");
		return mv;
	}

	@RequestMapping("jsonAreaList")
	@ResponseBody
	public String jsonAreaList(@ModelAttribute AreaDto areaDto){
		if(areaDto.getType()==1) {
			return JSON.toJSONString(basAreaService.selectAreaList(areaDto));
		}else if(areaDto.getType()==2){
			return JSON.toJSONString(basAreaService.selectAreaList(areaDto).get("rows"));
		}else{
			return null;
		}
	}
	
	/**
	 * 根据aid获取list
	 * @param aid
	 * @param resp
	 */
	@RequestMapping(value = "/areaListByAid", method = RequestMethod.GET)
	public void areaListByAid(Integer aid,HttpServletResponse resp) {
		resp.setCharacterEncoding("UTF-8");
		try {
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print(basAreaService.selectAreaByPid(aid));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@RequestMapping(value = "/updateAreaInput", method = RequestMethod.GET)
	public ModelAndView updateAreaInput(ModelAndView mv, Integer aid) {
		BasArea basArea = basAreaService.selectAreaByAid(aid);
		mv.addObject("updateArea", basArea);
		if(Integer.parseInt(basArea.getZonetype())==1){
		mv.addObject("plist", "");
		}else{
			mv.addObject("plist",basAreaService.selectAllPAreaListByPid(basArea.getPid()));
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "update");
		map.put("name", "编辑");
		map.put("aid", aid);
		mv.addObject("code", map);
		mv.setViewName("System/sys/basic/addArea");
		return mv;
	}

	@RequestMapping(value = "/updateArea", method = RequestMethod.POST)
	public ModelAndView updateArea(ModelAndView mv,
								   @ModelAttribute BasArea basArea, HttpSession session) {
		//System.out.println(JSON.toJSONString(basArea));
		basAreaService.updateArea(basArea);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("地区管理");
		basLog.setActionname("修改");
		basLog.setContent("修改一个地区：" + JSON.toJSONString(basArea));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.addObject("areaMap", basAreaService.selectAreaList(new AreaDto()));
		mv.setViewName("System/sys/basic/areaList");
		return mv;
	}

	@RequestMapping(value = "deleteArea", method = RequestMethod.GET)
	public void deleteArea(String allarea, HttpServletResponse response, HttpSession session) {
        //System.out.println("allarea"+allarea);
        try {
        	    response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			BasLog basLog = (BasLog) session.getAttribute("log");
			basLog.setModname("地区管理");
			basLog.setActionname("删除");
			String result = basAreaService.deleteArea(allarea);
			basLog.setContent("删除id:" + allarea + "删除结果：" + result);
			basLog.setCreatedate(new Date());
			basUserService.insertlog(basLog);
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

}
