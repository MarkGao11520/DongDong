package com.zrkj.ecp.controller.position;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.position.BasPosition;
import com.zrkj.ecp.dto.position.PositionDto;
import com.zrkj.ecp.service.position.BasPositionService;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("PositionController")
public class PositionController {
@Resource
BasPositionService basPositionService;
@Resource
BasUserService basUserService;
	
	/**
	 * 添加显示
	 * @param mv
	 * @param basPosition
	 * @return
	 */
	@RequestMapping("addPositionInput")
	public ModelAndView addPositionInput(ModelAndView mv, BasPosition basPosition){
		Map<String,String> map = new HashMap<String,String>();
		map.put("url", "add");
		map.put("name", "新增");
		mv.addObject("code",map);
		mv.addObject("plist",basPositionService.selectPositionDtoListByPid(basPosition.getPositionid(), basPosition.getPpositionid()));
		mv.addObject("updatePosition",basPosition);
	//	System.out.println("basposition:"+JSON.toJSONString(basPosition));
	//	System.out.println("list:"+JSON.toJSONString(object));
		mv.setViewName("System/position/addPosition");
		return mv;
	}
	
	/**
	 * 添加
	 * @param mv
	 * @param basPosition
	 * @return
	 */
	@RequestMapping("addPosition")
	public ModelAndView addPosition(ModelAndView mv, BasPosition basPosition, HttpSession session){
		basPositionService.addPosition(basPosition);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("岗位管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的岗位：" + JSON.toJSONString(basPosition));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		PositionDto positionDto = new PositionDto();
		positionDto.setCid(basPosition.getCid());
		positionDto.setDid(basPosition.getDid());
		mv.addObject("cid",positionDto.getCid());
		mv.addObject("did",positionDto.getDid());
		mv.setViewName("System/position/positionLis");
		return mv;
	}
	
	/**
	 * 三级结构
	 * @param mv
	 * @param type
	 * @param cid
	 * @return
	 */
	@RequestMapping("positionMain")
	public ModelAndView userMain(ModelAndView mv, Integer type, Integer cid){
		switch (type) {
		case 1:
			mv.setViewName("System/position/positionMain");
			break;
		case 2:
			mv.setViewName("System/position/positionLeft");
			break;
		case 3:
			mv.addObject("cid",cid);
			mv.setViewName("System/position/positionDept");
			break;
		default:
			break;
		}
		//mv.setViewName("System/uesr/userMain");
		return mv;
	}
	
	 /**
     * 显示职位列表
     * @param mv
     * @return
     */
	@RequestMapping("positionList")
	public ModelAndView positionList(ModelAndView mv, @ModelAttribute PositionDto positionDto){
		//System.out.println(JSON.toJSONString(basPositionService.positionList(positionDto)));
		mv.addObject("cid",positionDto.getCid());
		mv.addObject("did",positionDto.getDid());
		mv.setViewName("System/position/positionLis");
		return mv;
	}

	@RequestMapping("jsonpositionList")
	@ResponseBody
	public String jsonpositionList(@ModelAttribute PositionDto positionDto){
		if(positionDto.getType()==1) {
			return JSON.toJSONString(basPositionService.positionList(positionDto));
		}else if(positionDto.getType()==2){
			return JSON.toJSONString(basPositionService.positionList(positionDto).get("rows"));
		}else{
			return null;
		}
	}
	
	/**
	 * 获取岗位列表的html
	 * @param response
	 */
	@RequestMapping(value = "positionlistByPid",method = RequestMethod.GET)
	public void positionlistByPid(Integer ppositionid, HttpServletResponse response, HttpServletRequest request){
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out =response.getWriter();
			out.print(basPositionService.selectPositionListByPid(ppositionid,request));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 编辑显示
	 * @param mv
	 * @param basPosition
	 * @return
	 */
	@RequestMapping("updatePositionInput")
	public ModelAndView updatePositionInput(ModelAndView mv, BasPosition basPosition){
		basPosition = basPositionService.selectPositionByPositionid(basPosition.getPositionid());
		Map<String,String> map = new HashMap<String,String>();
		map.put("url", "update");
		map.put("name", "编辑");
		mv.addObject("code",map);
		mv.addObject("updatePosition",basPosition);
		mv.addObject("plist",basPositionService.selectPositionDtoListByPid(basPosition.getPositionid(), basPosition.getPpositionid()));
		mv.addObject("updatePosition",basPosition);
		mv.setViewName("System/position/addPosition");
		return mv;
	}
	
	/**
	 * 编辑
	 * @param mv
	 * @param basPosition
	 * @return
	 */
	@RequestMapping("updatePosition")
	public ModelAndView updatePosition(ModelAndView mv, @ModelAttribute BasPosition basPosition, HttpSession session){
		basPositionService.updatePosition(basPosition);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("岗位管理");
		basLog.setActionname("修改");
		basLog.setContent("修改一个岗位：" + JSON.toJSONString(basPosition));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		PositionDto positionDto = new PositionDto();
		positionDto.setCid(basPosition.getCid());
		positionDto.setDid(basPosition.getDid());
		mv.addObject("cid",positionDto.getCid());
		mv.addObject("did",positionDto.getDid());
		mv.setViewName("System/position/positionLis");
		return mv;
	}
	
	/**
	 * 删除
	 * @param allpositionid
	 */
	@RequestMapping(value = "deletePosition",method = RequestMethod.POST)
	public void deletePosition(String allpositionid, HttpServletResponse response, HttpSession session){
		response.setCharacterEncoding("UTF-8");
		try {
			PrintWriter out = response.getWriter();
			BasLog basLog = (BasLog) session.getAttribute("log");
			basLog.setModname("岗位管理");
			basLog.setActionname("删除");
			String result = basPositionService.deletePosition(allpositionid);
			basLog.setContent("删除id:" + allpositionid + "删除结果：" + result);
			basLog.setCreatedate(new Date());
			basUserService.insertlog(basLog);
			out.print(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
