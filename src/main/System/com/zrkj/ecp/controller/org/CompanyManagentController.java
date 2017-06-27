package com.zrkj.ecp.controller.org;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.org.BasCompanyWithBLOBs;
import com.zrkj.ecp.dto.org.CompDto;
import com.zrkj.ecp.dto.org.PCompanyDto;
import com.zrkj.ecp.dto.sys.basic.PAreaDto;
import com.zrkj.ecp.service.org.BasCompanyService;
import com.zrkj.ecp.service.org.parame.BasParameService;
import com.zrkj.ecp.service.sys.basic.BasAreaService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("CompanyManagent")
public class CompanyManagentController {
@Resource
BasCompanyService basCompanyService;
@Resource
BasAreaService basAreaService;
@Resource
BasUserService basUserService;
	
    /***
     * 新增显示
     * @param mv
     * @param basCompanyWithBLOBs
     * @return
     */
	@RequestMapping(value="/addCompanyInput")
	public ModelAndView addCompanyInput(ModelAndView mv, BasCompanyWithBLOBs basCompanyWithBLOBs){
		List<PCompanyDto> plist = new ArrayList<PCompanyDto>();
		PCompanyDto pCompanyDto = new PCompanyDto();
		pCompanyDto.setCid(basCompanyWithBLOBs.getCid());
		pCompanyDto.setCname(basCompanyWithBLOBs.getName());
		plist.add(pCompanyDto);
		mv.addObject("pcompList",plist);
		basCompanyWithBLOBs.setCid(null);
		basCompanyWithBLOBs.setName(null);
		mv.addObject("updateCompany",basCompanyWithBLOBs);
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "add");
		map.put("name", "新增");
		mv.addObject("code", map);
		mv.setViewName("System/org/addCompany");
		return mv;
	} 
	
	/**
	 * 地区转json
	 * @param pid
	 * @throws IOException
	 */
	@RequestMapping(value = "JsonAreaList",method = RequestMethod.POST)
	@ResponseBody
	public String JsonAreaList(Integer pid,String code,Integer aid) throws IOException{
		if(pid ==null){
			List<PAreaDto> plist = basAreaService.selectPAreaList();
			//System.out.println(JSON.toJSONString(plist));
			if(code!=null||code=="update"){
				for (PAreaDto pAreaDto : plist) {
					if(pAreaDto.getPid()==aid){
						pAreaDto.setSelected(true);
					}
				}
			}
			return JSON.toJSONString(plist);
		}else{
			List<PAreaDto> plist = basAreaService.selectPAreaListByPid(pid);
			if(code!=null||code=="update"){
				for (PAreaDto pAreaDto : plist) {
					if(pAreaDto.getPid()==aid){
						pAreaDto.setSelected(true);
					}
				}
			}
			return  JSON.toJSONString(plist);
		}
	}
	
	/**
	 * 新增机构
	 * @param mv
	 * @param basCompanyWithBLOBs
	 * @return
	 */
	@RequestMapping(value="addCompany")
	public ModelAndView addCompany(ModelAndView mv, @ModelAttribute BasCompanyWithBLOBs basCompanyWithBLOBs, HttpSession session){
		//System.out.println(JSON.toJSONString(basCompanyWithBLOBs));
		basCompanyService.addCompany(basCompanyWithBLOBs);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("机构管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的机构：" + JSON.toJSONString(basCompanyWithBLOBs));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.setViewName("System/org/companyList");
		return  mv;
	}
   
	/**
	 * 机构列表
	 * @param mv
	 * @return
	 */
	@RequestMapping(value="companyList")
	public ModelAndView CompanyList(ModelAndView mv){
		mv.setViewName("System/org/companyList");
		return  mv;
	}

	@RequestMapping("jsonOrgList")
	@ResponseBody
	public String jsonOrgList(@ModelAttribute CompDto compDto){
		if(compDto.getType()==1) {
			return JSON.toJSONString(basCompanyService.selectCompanylist(compDto));
		}else if(compDto.getType()==2){
			return JSON.toJSONString(basCompanyService.selectCompanylist(compDto).get("rows"));
		}else{
			return null;
		}
	}
	
	/**
	 * dto转json并打印
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "jsonCompanyList")
	@ResponseBody
	public String jsonCompanyList(String id,HttpServletResponse response){
		String json = basCompanyService.jsonCompanyDtoList(id);
		return json;
	}
	
	/**
	 * 获取公司列表的html
	 * @param cid
	 * @param response
	 */
	@RequestMapping(value = "companylistByPid",method = RequestMethod.GET)
	public void companylistByPid(Integer cid, HttpServletResponse response, HttpServletRequest request){
	
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out =response.getWriter();
			out.print(basCompanyService.selecCompanylistBypid(cid,request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改显示
	 */
	@RequestMapping(value = "updateCompanyInput",method = RequestMethod.GET)
	public ModelAndView updateCompanyInput(ModelAndView mv, Integer cid){
		BasCompanyWithBLOBs basCompanyWithBLOBs = basCompanyService.selectCompanyByCid(cid);
		mv.addObject("updateCompany", basCompanyWithBLOBs);
		Map<String, String> map = new HashMap<String, String>();
		map.put("url", "update");
		map.put("name", "编辑");
		mv.addObject("code", map);
		if(basCompanyWithBLOBs.getTreeorder()==1){
		
		}else{
			mv.addObject("pcompList",basCompanyService.selectCompany(basCompanyWithBLOBs.getPid()));
		}
		mv.setViewName("System/org/addCompany");
		return mv;
	}
	
	/**
	 * 更改机构
	 * @param mv
	 * @param basCompanyWithBLOBs
	 * @return
	 */
	@RequestMapping(value = "updateCompany")
	public ModelAndView updateCompany(ModelAndView mv, BasCompanyWithBLOBs basCompanyWithBLOBs, HttpSession session){
		//System.out.println(JSON.toJSONString(basCompanyWithBLOBs));
//		System.out.println("status1:"+basCompanyWithBLOBs.getStatus1());
		basCompanyWithBLOBs.setStatus(basCompanyWithBLOBs.getStatus1());
		basCompanyService.updateCompany(basCompanyWithBLOBs);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("机构管理");
		basLog.setActionname("修改");
		basLog.setContent("修改一个机构：" + JSON.toJSONString(basCompanyWithBLOBs));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		mv.setViewName("System/org/companyList");
		return  mv;
	}
	
	/**
	 * 批量删除
	 * @param allcomp
	 */
	@RequestMapping(value = "deleteComp",method = RequestMethod.POST)
	public void deleteComp(HttpServletResponse response, String allcomp, HttpSession session){
		 try {
     	    response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			BasLog basLog = (BasLog) session.getAttribute("log");
			basLog.setModname("机构管理");
			basLog.setActionname("删除");
			String result = basCompanyService.deleteCompany(allcomp);
			basLog.setContent("删除id:" + allcomp + "删除结果：" + result);
			basLog.setCreatedate(new Date());
			basUserService.insertlog(basLog);
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping("schoollist")
	@ResponseBody
	public String schoollist(){
		return JSON.toJSONString(basCompanyService.obtainSchoolList());
	}


}
