package com.zrkj.ecp.controller.user;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.user.BasUser;
import com.zrkj.ecp.domain.user.BasUserProperty;
import com.zrkj.ecp.dto.log.LogDto;
import com.zrkj.ecp.dto.user.JsonListDto;
import com.zrkj.ecp.dto.user.UserDto;
import com.zrkj.ecp.service.dept.BasDeptService;
import com.zrkj.ecp.service.org.BasCompanyService;
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
@RequestMapping("/UserManagent")
public class userManagentController {
@Resource
BasUserService basUserService;
@Resource
BasCompanyService basCompanyService;
@Resource
BasDeptService basDeptService;
@Resource
BasPositionService basPositionService;

    /**
     * 显示用户列表
     * @param mv
     * @param userDto
     * @return
     */
	@RequestMapping("UserList")
	public ModelAndView UserList(ModelAndView mv, @ModelAttribute UserDto userDto){
		mv.addObject("cid",userDto.getCid());
		
		mv.setViewName("System/user/userManagement");
		return mv;
	}

	@RequestMapping("jsonUserList")
	public void  jsonUserList(@ModelAttribute UserDto userDto, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.print(JSON.toJSONString(basUserService.UserList(userDto)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 添加用户界面显示
	 * @param mv
	 * @param basUser
	 * @return
	 */
	@RequestMapping("addUserInput")
	public ModelAndView addUserList(ModelAndView mv, @ModelAttribute BasUser basUser){
		Map<String,String> map = new HashMap<String,String>();
		map.put("url", "add");
		map.put("name", "用户");
		mv.addObject("code",map);
		BasUser basUser1 =  new BasUser();
		basUser1.setCid(basUser.getCid());
		mv.addObject("updateUser",basUser1);
		mv.addObject("updateUserPro",new BasUserProperty());
		mv.setViewName("System/user/addUser");
		return mv;
	}
	
	@RequestMapping("userMain")
	public ModelAndView userMain(ModelAndView mv, Integer type, Integer cid){
		switch (type) {
		case 1:
			mv.setViewName("System/user/userMain");
			break;
		case 2:
			mv.setViewName("System/user/userLeft");
			break;
		case 3:
			mv.addObject("cid",cid);
			mv.setViewName("System/user/userDept");
			break;
		default:
			break;
		}
		//mv.setViewName("System/uesr/userMain");
		return mv;
	}
	
	/**
	 * 企业部门转json
	 * @param jsonListDto
	 */
	@RequestMapping(value="JsonList",method = RequestMethod.POST)
	@ResponseBody
	public String JsonList(@ModelAttribute JsonListDto jsonListDto, HttpServletResponse response) {
		switch (jsonListDto.getType()) {
			case 1:
				return (JSON.toJSONString(basCompanyService.selectAllCompanyListBycid(jsonListDto.getCid())));
			case 2:
				return (JSON.toJSONString(basDeptService.selectDeptDtoList(jsonListDto.getCid(), jsonListDto.getDid())));
			case 3:
				return (JSON.toJSONString(basPositionService.selectPositionDtoList(jsonListDto.getDid(), jsonListDto.getPosid())));
			default:
				return null;
		}
	}
	
	@RequestMapping(value="JsonTreeList",method = RequestMethod.POST)
	@ResponseBody
	public String JsonTreeList(Integer cid,HttpServletResponse response) {
		return basDeptService.DtoListToJsonTree(cid);
	}
	/**
	 * 添加新用户
	 * @param mv
	 * @param basUser
	 * @param basUserProperty
	 * @return
	 */
	@RequestMapping(value="addUser",method = RequestMethod.POST)
	public ModelAndView addUser(ModelAndView mv, @ModelAttribute BasUser basUser, @ModelAttribute BasUserProperty basUserProperty, HttpSession session){
		basUserService.addUser(basUser, basUserProperty);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("用户管理");
		basLog.setActionname("添加");
		basLog.setContent("添加一个新的用户：" + JSON.toJSONString(basUser));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		UserDto userDto = new UserDto();
		if(basUser.getDid()!=null) {
			userDto.setDid(basUser.getDid());
		}
		if(basUser.getCid()!=null) {
			userDto.setCid(basUser.getCid());
		}
		mv.addObject("cid",userDto.getCid());
		mv.setViewName("System/user/userManagement");
		return mv;
	}
	
	/**
	 * 修改界面显示
	 * @param mv
	 * @param uid
	 * @return
	 */
	@RequestMapping("updateUserInput")
	public ModelAndView updateUserInput(ModelAndView mv, Integer uid){
		Map<String,Object> map = basUserService.selectUserByUid(uid);
		mv.addObject("updateUser",map.get("user"));
		mv.addObject("updateUserPro",map.get("userp"));
		map.put("url", "update");
		map.put("name", "修改");
		mv.addObject("code",map);
		mv.setViewName("System/user/addUser");
		return mv;
	}
	
	/**
	 * 更改用户
	 * @param mv
	 * @param basUser
	 * @param basUserProperty
	 * @param session
	 * @return
	 */
	@RequestMapping(value="updateUser",method = RequestMethod.POST)
	public ModelAndView updateUser(ModelAndView mv, @ModelAttribute BasUser basUser, @ModelAttribute BasUserProperty basUserProperty, HttpSession session){
		basUserService.updateUser(basUser, basUserProperty);
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("用户管理");
		basLog.setActionname("修改");
		basLog.setContent("修改一个用户：" + JSON.toJSONString(basUser));
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
		UserDto userDto = new UserDto();
		if(basUser.getDid()!=null) {
			userDto.setDid(basUser.getDid());
		}
		if(basUser.getCid()!=null) {
			userDto.setCid(basUser.getCid());
		}
		mv.addObject("cid",userDto.getCid());
		mv.setViewName("System/user/userManagement");
		return mv;
	}
	
	/**
	 * 删除用户
	 * @param allUser
	 * @param session
	 */
	@RequestMapping("deleteUser")
	public void deleteUser(String allUser,HttpSession session){
		
		String str[] = allUser.split(",");
		for (String string : str) {
			BasUser basUser = new BasUser();
			BasUserProperty basUserProperty = new BasUserProperty();
			basUser.setUid(Integer.parseInt(string));
			basUserProperty.setUid(Integer.parseInt(string));
			basUser.setIsdel(1);
			basUserProperty.setIsdel(1);
			basUserService.updateUser(basUser, basUserProperty);
		}
		BasLog basLog = (BasLog) session.getAttribute("log");
		basLog.setModname("用户管理");
		basLog.setActionname("删除");
		String result = "删除成功";
		basLog.setContent("删除id:" + allUser + "删除结果：" + result);
		basLog.setCreatedate(new Date());
		basUserService.insertlog(basLog);
	}
	
	/**
	 * 日志列表
	 * @param mv
	 * @param logDto
	 * @return
	 */
	@RequestMapping("LogList")
	public ModelAndView LogList(ModelAndView mv, @ModelAttribute LogDto logDto){
		mv.addObject("uid",logDto.getUid());
		mv.setViewName("System/log/logList");
		return mv;
	}

	@RequestMapping("JsonlogList")
	public void JsonlogList(HttpServletResponse response, @ModelAttribute LogDto logDto){
		logDto.setPage(logDto.getPage()-1);
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			Map<String,Object> resMap = basUserService.selectLogList(logDto);
			out.print(JSON.toJSONString(resMap));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	@RequestMapping("mobile/loginCheck")
	@ResponseBody
	public String loginCheck(BasUser basUser, HttpServletRequest request){
		Map<String,Object> map =  basUserService.loginCheck(basUser,request);
			return JSON.toJSONString(map);
	}

	@RequestMapping("updatePassword")
	@ResponseBody
	public String updatePassword(String oldP,String newP){
		if(basUserService.updatePassWord(oldP,newP)){
			return "1";
		}else{
			return "0";
		}
	}
}
