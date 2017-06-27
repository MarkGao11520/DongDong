package com.zrkj.ecp.controller.expresslog;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresslog.IExpressLogService;
import org.springframework.web.servlet.ModelAndView;

/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressLogController")
public class ExpressLogController {
	@Resource
	IExpressLogService iExpressLogService;

	/**
	 * 服务器端日志界面
	 * @param mv
	 * @return
	 */
	@RequestMapping("expressLogList")
	public ModelAndView expressLogList(ModelAndView mv){
		mv.setViewName("Express/expresslog/expressLogList");
		return mv;
	}

	/**
	 * 获取操作日志列表
	 * @param expressDto
	 * @return {rows:[ExpressLog]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonExpressLogList")
	@ResponseBody
	public String jsonExpressLogList(@ModelAttribute ExpressDto expressDto){
		return JSON.toJSONString(iExpressLogService.obtainExpredssLogList(expressDto));
	}

	/**
	 * 根据id获取操作日志
	 * @param LogId
	 * @return ExpressLog 的json形式
     */
	@RequestMapping("jsonExpressLogOne")
	@ResponseBody
	public String jsonExpressLogOne(Integer LogId){
		return JSON.toJSONString(iExpressLogService.obtainExpressLogById(LogId));
	}


	/**
	 * 添加操作日志
	 * @param expressLog
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("addExpressLog")
	@ResponseBody
	public String addExpressLog(@ModelAttribute ExpressLog expressLog){
		if(iExpressLogService.addExpressLog(expressLog)){
			return "addSuccess";
		}else {
			return "addFailed";
		}
	}

	/**
	 * 编辑操作日志
	 * @param expressLog
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("updateExpressLog")
	@ResponseBody
	public String updateExpressLog(@ModelAttribute ExpressLog expressLog){
		if(iExpressLogService.updateExpressLog(expressLog)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}


}
