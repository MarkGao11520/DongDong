package com.zrkj.ecp.controller.dept;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.dept.BasDept;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.dto.dept.DeptDto;
import com.zrkj.ecp.dto.org.PCompanyDto;
import com.zrkj.ecp.service.dept.BasDeptService;
import com.zrkj.ecp.service.org.BasCompanyService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2016/12/21.
 */
@Controller
@RequestMapping(value = "DeptManagent")
public class DeptManagentController {
	@Resource
	BasCompanyService basCompanyService;
	@Resource
	BasDeptService basDeptService;
	@Resource
	BasUserService basUserService;

	/**
	 * 跳转添加部门界面
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping("/addDeptInput")
	public ModelAndView addDeptInput(ModelAndView mv, BasDept basDept) {
		List<PCompanyDto> plist = basCompanyService.selectAllCompanyListBycid(basDept.getCid());
		mv.addObject("plist",plist);
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "add");
		map.put("name", "新增");
		mv.addObject("code", map);
		mv.addObject("updateDept", basDept);
		mv.setViewName("System/dept/addDept");
		return mv;
	}

	/**
	 * 添加地区
	 * 
	 * @param mv
	 * @param basDept
	 * @return
	 */
	@RequestMapping(value = "/addDept", method = RequestMethod.POST)
	public ModelAndView addDept(ModelAndView mv, @ModelAttribute BasDept basDept, HttpSession session) {
		
		basDeptService.addDept(basDept);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("部门管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的部门" + JSON.toJSONString(basDept));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		DeptDto deptDto = new DeptDto();
		deptDto.setCid(basDept.getCid());
		deptDto.setDname(basDept.getDeptname());
		mv.addObject("cid", deptDto.getCid());
		mv.setViewName("System/dept/DeptList");
		return mv;
	}

	/**
	 * 地区列表
	 * 
	 * @param mv
	 * @param DeptDto
	 * @return
	 */
	@RequestMapping(value = "/DeptList", method = RequestMethod.GET)
	public ModelAndView DeptList(ModelAndView mv,
								 @ModelAttribute DeptDto DeptDto) {
		mv.addObject("cid", DeptDto.getCid());
		mv.setViewName("System/dept/DeptList");
		return mv;
	}

	@RequestMapping("jsonDeptList")
	@ResponseBody
	public  String jsonDeptList(@ModelAttribute DeptDto DeptDto){
		return JSON.toJSONString(basDeptService.selectDeptList(DeptDto));
	}
	
	/**
	 * 左边导航
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/deptLeft")
	public ModelAndView deptLeft(ModelAndView mv){
		mv.setViewName("System/dept/deptLeft");
		return mv;
	}
	
	/**
	 * 总体导航
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/deptMain")
	public ModelAndView deptMain(ModelAndView mv){
		mv.setViewName("System/dept/deptMain");
		return mv;
	}
	

	/**
	 * 修改显示
	 * @param mv
	 * @return
	 */
	@RequestMapping(value = "/updateDeptInput", method = RequestMethod.GET)
	public ModelAndView updateDeptInput(ModelAndView mv, Integer did) {
		BasDept basDept = basDeptService.selectDeptByAid(did);
		mv.addObject("updateDept", basDept);
		List<PCompanyDto> plist = basCompanyService.selectAllCompanyListBycid(basDept.getCid());
		mv.addObject("plist",plist);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("url", "update");
		map.put("name", "编辑");
		map.put("did", did);
		mv.addObject("code", map);
		mv.setViewName("System/dept/addDept");
		return mv;
	}

	/**
	 * 修改部门信息
	 * @param mv
	 * @param basDept
	 * @return
	 */
	@RequestMapping(value = "/updateDept", method = RequestMethod.POST)
	public ModelAndView updateDept(ModelAndView mv,
								   @ModelAttribute BasDept basDept, HttpSession session) {
		//System.out.println(JSON.toJSONString(basDept));
		basDeptService.updateDept(basDept);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("部门管理");
		basLog.setActionname("修改");
		basLog.setContent("修改一个部门信息：" + JSON.toJSONString(basDept));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		DeptDto deptDto = new DeptDto();
		deptDto.setCid(basDept.getCid());
		deptDto.setDname(basDept.getDeptname());
		mv.addObject("cid", deptDto.getCid());
		mv.setViewName("System/dept/DeptList");
		return mv;
	}

	/**
	 * 删除dept(逻辑删除)
	 * @param allDept
	 * @return
	 */
	@RequestMapping(value = "deleteDept", method = RequestMethod.POST)
	public void deleteDept(HttpServletResponse response, String allDept, HttpSession session) {
        //System.out.println("allDept"+allDept);
		 try {
	     	    response.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				BasLog basLog = (BasLog) session.getAttribute("log");
				basLog.setModname("部门管理");
				basLog.setActionname("删除");
				String result = basDeptService.deleteDept(allDept);
				basLog.setContent("删除内容：" + allDept+"删除结果:"+result);
				basLog.setCreatedate(new Date());
				basUserService.insertlog(basLog);
				out.print(result);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
