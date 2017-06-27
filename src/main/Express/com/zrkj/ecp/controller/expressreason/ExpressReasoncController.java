package com.zrkj.ecp.controller.expressreason;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expressreason.ExpressReason;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressreason.IExpressReasonService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("expressReasoncController")
public class ExpressReasoncController {
	@Resource
	IExpressReasonService iExpressReasonService;

	/**
	 * 服务器端界面
	 * @param mv
	 * @return
	 */
	@RequestMapping("expressReasonList")
	public ModelAndView expressReasonList(ModelAndView mv){
		mv.setViewName("Express/expressreason/expressReason");
		return mv;
	}

	/**
	 * 服务器端添加界面
	 * @param mv
	 * @return
	 */
	@RequestMapping("addExpressReasonInput")
	public ModelAndView addexpressReasonInput(ModelAndView mv){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","add");
		mv.addObject("code",map);
		mv.addObject("updateReason",new ExpressReason());
		mv.setViewName("Express/expressreason/addExpressReason");
		return mv;
	}

	/**
	 * 服务器端编辑界面
	 * @param mv
	 * @param reasonid
	 * @return
	 */
	@RequestMapping("updateExpressReasonInput")
	public ModelAndView updateexpressReasonInput(ModelAndView mv,Integer reasonid){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","update");
		mv.addObject("code",map);
		mv.addObject("updateReason",iExpressReasonService.obtainExpressReasonById(reasonid));
		mv.setViewName("Express/expressreason/addExpressReason");
		return mv;
	}

	/**
	 * 服务器端添加方法
	 * @param mv
	 * @param expressReason
	 * @return
	 */
	@RequestMapping("addExpressReasonList")
	public ModelAndView addExpressReason(ModelAndView mv,@ModelAttribute ExpressReason expressReason){
		if (iExpressReasonService.addExpressReason(expressReason)) {
			mv.setViewName("Express/expressreason/expressReason");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","add");
			map.put("error","1");
			mv.addObject("code",map);
			mv.setViewName("Express/expressreason/addExpressReason");
		}
		return mv;
	}

	/**
	 * 服务器端编辑方法
	 * @param mv
	 * @param expressReason
	 * @return
	 */
	@RequestMapping("updateExpressReasonList")
	public ModelAndView updateExpressReason(ModelAndView mv,@ModelAttribute ExpressReason expressReason){
		if (iExpressReasonService.updateExpressReason(expressReason)) {
			mv.setViewName("Express/expressreason/expressReason");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","update");
			map.put("error","2");
			mv.addObject("code",map);
			mv.addObject("updateReason",iExpressReasonService.obtainExpressReasonById(expressReason.getReasonid()));
			mv.setViewName("Express/expressreason/addExpressReason");
		}
		return mv;
	}


	/**
	 * 获取异常原因列表
	 * @param expressDto
	 * @return {rows:[ExpressReason]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonExpressReasonList")
	@ResponseBody
	public String jsonExpressReasonList(@ModelAttribute ExpressDto expressDto){
		return JSON.toJSONString(iExpressReasonService.obtainExpredssReasonList(expressDto));
	}

	/**
	 * 根据id获取异常原因
	 * @param ReasonId
	 * @return ExpressReason的json形式
     */
	@RequestMapping("jsonExpressReasonOne")
	@ResponseBody
	public String jsonExpressReasonOne(Integer ReasonId){
		return JSON.toJSONString(iExpressReasonService.obtainExpressReasonById(ReasonId));
	}


	/**
	 * 添加异常原因
	 * @param expressReason
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("addExpressReason")
	@ResponseBody
	public String addExpressReason(@ModelAttribute ExpressReason expressReason){
		if(iExpressReasonService.addExpressReason(expressReason)){
			return "addSuccess";
		}else {
			return "addFailed";
		}
	}

	/**
	 * 编辑异常原因
	 * @param expressReason
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("updateExpressReason")
	@ResponseBody
	public String updateExpressReason(@ModelAttribute ExpressReason expressReason){
		if(iExpressReasonService.updateExpressReason(expressReason)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}

}
