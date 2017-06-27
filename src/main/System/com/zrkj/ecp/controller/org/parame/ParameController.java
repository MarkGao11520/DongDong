package com.zrkj.ecp.controller.org.parame;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.org.parame.BasParam;
import com.zrkj.ecp.dto.org.parame.ParameDto;
import com.zrkj.ecp.service.org.parame.BasParameService;
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

@Controller
@RequestMapping("parameController")
public class ParameController {
	@Resource
	BasParameService basParameService;
	@Resource
	BasUserService basUserService;
	
	/**
	 * 获取一级列表
	 * @param mv
	 * @param parameDto
	 * @return
	 */
	@RequestMapping(value= "ParameList")
	public ModelAndView ParameList(ModelAndView mv, @ModelAttribute ParameDto parameDto){
		mv.addObject("cid",parameDto.getCid());
		mv.setViewName("System/org/param/ParameList");
		return mv;
	}

	@RequestMapping("jsonParamList")
	@ResponseBody
	public String jsonParamList(@ModelAttribute ParameDto parameDto){
		if(parameDto.getType()==1){
			return JSON.toJSONString(basParameService.selectFirstParameList(parameDto));
		}else if(parameDto.getType()==2){
			return JSON.toJSONString(basParameService.selectFirstParameList(parameDto).get("rows"));
		}else{
			return null;
		}
	}
	
	/**
	 * 根据id获取列表
	 * @param pid
	 * @param response
	 */
	@RequestMapping(value = "parameListByPid")
	public void parameListByPid(Integer pid,HttpServletResponse response){
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(basParameService.selectParamListById(pid));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加显示
	 * @param mv
	 * @param basParam
	 * @return
	 */
	@RequestMapping(value = "addParameInput")
	public ModelAndView addParameInput(ModelAndView mv, @ModelAttribute BasParam basParam){
		mv.addObject("updateParame",basParam);
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "add");
		map.put("name", "新增");
		mv.addObject("code", map);
		mv.setViewName("System/org/param/addParame");
		return mv;
	}
	
	/**
	 * 添加
	 * @param mv
	 * @param basParam
	 * @param session
	 * @param parameDto
	 * @return
	 */
	@RequestMapping(value = "addParame",method = RequestMethod.POST)
	public ModelAndView addParame(ModelAndView mv, @ModelAttribute BasParam basParam, HttpSession session, @ModelAttribute ParameDto parameDto){
		basParameService.addParame(basParam);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("参数设置");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的参数：" + JSON.toJSONString(basParam));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.addObject("cid",parameDto.getCid());
		mv.setViewName("System/org/param/ParameList");
		return mv;
	}
	
	@RequestMapping(value = "updateParameInput")
	public ModelAndView updateParameInput(ModelAndView mv, Integer paraid){
		mv.addObject("updateParame",basParameService.seleteParameByid(paraid));
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "update");
		map.put("name", "编辑");
		mv.addObject("code", map);
		mv.setViewName("System/org/param/addParame");
		return mv;
	}
	
	/**
	 * 修改
	 * @param mv
	 * @param basParam
	 * @param parameDto
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "updateParame",method = RequestMethod.POST)
	public ModelAndView updateParame(ModelAndView mv, @ModelAttribute BasParam basParam, @ModelAttribute ParameDto parameDto, HttpSession session){
		basParameService.updateParame(basParam);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("参数设置");
		basLog.setActionname("编辑");
		basLog.setContent("修改一个参数：" + JSON.toJSONString(basParam));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.addObject("cid",parameDto.getCid());
		mv.setViewName("System/org/param/ParameList");
		return mv;
	}
	
	/**
	 * 修改
	 * @param response
	 * @param allParame
	 * @param session
	 */
	@RequestMapping(value = "delteParame",method = RequestMethod.POST)
	public void delteParame(HttpServletResponse response, String allParame, HttpSession session){
		 try {
	     	    response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				BasLog basLog = (BasLog) session.getAttribute("log");
				basLog.setModname("系统设置");
				basLog.setActionname("删除");
				String result = basParameService.deleteParame(allParame);
				basLog.setContent("删除id:" + allParame + "删除结果：" + result);
				basLog.setCreatedate(new Date());
				basUserService.insertlog(basLog);
				out.print(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	/**
	 * 根据cid查找学校网点
	 * @param cid
	 * @return
     */
	@RequestMapping("branchesList")
	@ResponseBody
	public String branchesList(Integer cid){
		List<Map<String,Object>> resultlist = new ArrayList<Map<String, Object>>();
		List<BasParam> paramList = basParameService.obtainParameListByCid(cid);
		for(BasParam basParam:paramList){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("wangdian",basParam);
			ParameDto parameDto = new ParameDto();
			parameDto.setCid(basParam.getCid());
			parameDto.setParaid(basParam.getParaid());
			parameDto.setPage(1);
			parameDto.setRows(100);
			List<BasParam> xinxiList = (List<BasParam>) basParameService.selectFirstParameList(parameDto).get("rows");
			for(BasParam basParam1:xinxiList){
				if(basParam1.getParaname().trim().equals("网点手机号")){
					map.put("phone",basParam1);
				}else if(basParam1.getParaname().trim().equals("工作时间")){
					map.put("time",basParam1);
				}
			}
			resultlist.add(map);
		}
		return JSON.toJSONString(resultlist);
	}

	/**
	 * 根据网点id获取网点名称
	 * @param paramid
	 * @return
	 */
	@RequestMapping(value = "obtainConfigNameByConfigId")
	@ResponseBody
	public String obtainConfigNameByConfigId(Integer paramid){
		return basParameService.seleteParameByid(paramid).getParaname();
	}

}
