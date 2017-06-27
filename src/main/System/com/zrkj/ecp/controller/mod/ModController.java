package com.zrkj.ecp.controller.mod;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.mod.BasMod;
import com.zrkj.ecp.domain.mod.BasRoleMod;
import com.zrkj.ecp.dto.app.PMappDto;
import com.zrkj.ecp.dto.mod.ModDto;
import com.zrkj.ecp.dto.mod.PModDto;
import com.zrkj.ecp.service.app.BasAppService;
import com.zrkj.ecp.service.mod.BasModService;
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
@RequestMapping("modController")
public class ModController {
	@Resource
	BasAppService basAppService;
	@Resource
	BasModService basModService;
	@Resource
	BasUserService basUserService;
	
	/**
	 * 主界面
	 * @param mv
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "modMain",method = RequestMethod.GET)
	public ModelAndView modMain(ModelAndView mv, Integer type, Integer code, Integer positionid){
		switch (type) {
		case 1:
			mv.addObject("code",code);
			mv.addObject("positionid",positionid);
			mv.setViewName("System/mod/modMain");
			break;
		case 2:
			mv.addObject("positionid",positionid);
			mv.addObject("PMappList",basAppService.selectPMappDtoList());
			mv.setViewName("System/mod/modLeft");
			break;
		default:
			break;
		}
		return mv;
	}
	
	/**
	 * 获取一级功能列表
	 * @param mv
	 * @param modDto
	 * @return
	 */
	@RequestMapping(value = "modList",method = RequestMethod.GET)
	public ModelAndView modList(ModelAndView mv, Integer appid){
		System.out.println("appid+");
		mv.addObject("appid",appid);
		mv.setViewName("System/mod/modList");
		return mv;
	}
	
	/**
	 * 获取功能列表
	 * @param pid
	 * @param response
	 */
	@RequestMapping(value = "modlistByPid")
	public void modlistByPid(@ModelAttribute ModDto modDto, HttpServletResponse response){
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			if(modDto.getType()==1){
			out.print(JSON.toJSONString(basModService.selectFirstModList(modDto)));
			}else if(modDto.getType()==2){
				out.print(JSON.toJSONString(basModService.selectFirstModList(modDto).get("rows")));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "jsonModList")
	public void jsonModList(Integer pid, HttpServletResponse response, HttpServletRequest request){
		try {
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.print(basModService.jsonModListByPid(pid));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加显示
	 * @param mv
	 * @param modid
	 * @param appid
	 * @return
	 */
	@RequestMapping(value = "addModInput",method = RequestMethod.GET)
	public ModelAndView addModInput(ModelAndView mv, Integer modid, Integer appid){
		List<PModDto>  pmodelist = basModService.selectAllPModDtoBymodid(modid);
		List<PModDto>  plist = new ArrayList<PModDto>();
		List<PMappDto> applist = new ArrayList<PMappDto>();
		for (PModDto pModDto : pmodelist) {
			if(pModDto.getModid() == modid){
				plist.add(pModDto);
			}
		}
		List<PMappDto> pmapplist = basAppService.selectPMappDtoList();
		for (PMappDto pMappDto : pmapplist) {
			if(null!=appid&&0!=pMappDto.getAppid()&&pMappDto.getAppid()==appid){
				applist.add(pMappDto);
			}
		}
		mv.addObject("plist",plist);
		mv.addObject("applist",applist);
		mv.addObject("updateMod",new BasMod());
		Map<String, String> map = new HashMap<String, String>();
 		map.put("url", "add");
 		map.put("name", "新增");
 		mv.addObject("code", map);
		mv.setViewName("System/mod/addMod");
		return mv;
	}
	
	/**
	 *添加
	 * @param mv
	 * @param basMod
	 * @return
	 */
	@RequestMapping(value = "addMod",method = RequestMethod.POST)
	public ModelAndView addMod(ModelAndView mv, @ModelAttribute BasMod basMod, HttpSession session){
		basModService.addMod(basMod);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("功能管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的功能：" + JSON.toJSONString(basMod));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		ModDto modDto = new ModDto();
		modDto.setAppid((basMod.getAppid().toString()));
		mv.addObject("appid",basMod.getAppid().toString());
		mv.setViewName("System/mod/modList");
 		
		return mv;
	}
	
	/**
	 * 编辑显示
	 * @param mv
	 * @param modid
	 * @return
	 */
	@RequestMapping(value = "updateModInput",method = RequestMethod.GET)
	public ModelAndView updateModInput(ModelAndView mv, Integer modid){
		BasMod basMod = basModService.selectModByModid(modid);
		List<PModDto>  pmodelist = basModService.selectAllPModDtoBymodid(basMod.getPid());
		List<PMappDto> pmapplist = basAppService.selectPMappDtoList();
		mv.addObject("plist",pmodelist);
		mv.addObject("applist",pmapplist);
		Map<String, String> map = new HashMap<String, String>();
 		map.put("url", "update");
 		map.put("name", "编辑");
 		mv.addObject("code", map);
 		mv.addObject("updateMod",basMod);
 		mv.setViewName("System/mod/addMod");
 		return mv;
	}
	
	/**
	 *编辑
	 * @param mv
	 * @param basMod
	 * @return
	 */
	@RequestMapping(value = "updateMod",method = RequestMethod.POST)
	public ModelAndView updateMod(ModelAndView mv, @ModelAttribute BasMod basMod, HttpSession session){
		basModService.updateMod(basMod);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("功能管理");
		basLog.setActionname("修改");
		basLog.setContent("修改一个功能：" + JSON.toJSONString(basMod));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		ModDto modDto = new ModDto();
		modDto.setAppid((basMod.getAppid().toString()));
		mv.addObject("appid",basMod.getAppid().toString());
		mv.setViewName("System/mod/modList");
		return mv;
	}
	
	/**
	 * 删除
	 * @param modidstr
	 */
	@RequestMapping(value = "deleteMod",method = RequestMethod.POST)
	public void deleteMod(String modidstr, HttpServletResponse response, HttpSession session){
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			BasLog basLog = (BasLog) session.getAttribute("log");
			basLog.setModname("功能管理");
			basLog.setActionname("删除");
			String result = basModService.deleteMod(modidstr);
			basLog.setContent("删除内容：" + modidstr+"删除结果:"+result);
			basLog.setCreatedate(new Date());
			basUserService.insertlog(basLog);
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 授权显示
	 * @param mv
	 * @param modDto
	 * @return
	 */
	@RequestMapping(value = "authorization",method = RequestMethod.GET)
	public ModelAndView authorization(ModelAndView mv, @ModelAttribute ModDto modDto, @ModelAttribute BasRoleMod basRoleMod){
		List<PModDto> list = (List<PModDto>) basModService.selectPModDto(modDto,basRoleMod).get("first");
		mv.addObject("PmodDtoList",list);
		mv.addObject("appid",modDto.getAppid());
		mv.addObject("positionid",basRoleMod.getPositionid());
		mv.setViewName("System/mod/authorization");
		return mv;
	}
	
	@RequestMapping(value = "modHmtl")
	public void modHtml(ModDto modDto, @ModelAttribute BasRoleMod basRoleMod, HttpServletResponse response){
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(basModService.selectPModDto(modDto,basRoleMod).get("listhtml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 授权表更新
	 * @param basRoleMod
	 * @param modidstr
	 * @param nocheckstr
	 */
	@RequestMapping(value = "authorization1",method = RequestMethod.POST)
	@ResponseBody
	public String authorization(@ModelAttribute BasRoleMod basRoleMod, String modidstr , String nocheckstr, HttpSession session){
		//System.out.println(JSON.toJSONString(basRoleMod));
		//System.out.println(nocheckstr);
		try {
			basModService.updateModRole(basRoleMod, modidstr, nocheckstr);
			BasLog basLog = (BasLog) session.getAttribute("log");
			basLog.setModname("功能管理");
			basLog.setActionname("授权");
			basLog.setContent("授权职位，应用，功能：" + basRoleMod + "新增授权id:" + modidstr + "取消的授权：" + nocheckstr);
			basLog.setCreatedate(new Date());
			basUserService.insertlog(basLog);
			return "success";
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		return "failed";
	}
	

}
