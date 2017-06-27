package com.zrkj.ecp.controller.expressfdex;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expressfdex.ExpressSendFeeDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expressfdex.ExpressSendFee;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressfdex.IExpressFeeService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressSendFeeController")
public class ExpressSendFeeController {
	@Resource
	IExpressFeeService iExpressSendFeeService;

	/**
	 * 快递公司派送费用界面
	 * @param mv
	 * @param fdid
	 * @return
	 */
	@RequestMapping("expressSendFeeList")
	public ModelAndView expressSendFeeList(ModelAndView mv,Integer fdid){
		mv.addObject("fdid",fdid);
		mv.setViewName("Express/expressfdex/expressSendFeeList");
		return mv;
	}

	/**
	 * 服务器端添加界面
	 * @param mv
	 * @param expressSendFee
	 * @return
	 */
	@RequestMapping("addExpressSendFeeInput")
	public ModelAndView addexpressSendFeeInput(ModelAndView mv,ExpressSendFee expressSendFee){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","add");
		mv.addObject("code",map);
		mv.addObject("updateSendFee",expressSendFee);
		mv.setViewName("Express/expressfdex/addExpressSendFee");
		return mv;
	}

	/**
	 * 服务器端编辑界面
	 * @param mv
	 * @param sendid
	 * @return
	 */
	@RequestMapping("updateExpressSendFeeInput")
	public ModelAndView updateexpressSendFeeInput(ModelAndView mv,Integer sendid){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","update");
		mv.addObject("code",map);
		mv.addObject("updateSendFee",iExpressSendFeeService.obtainExpressSendFeeById(sendid));
		mv.setViewName("Express/expressfdex/addExpressSendFee");
		return mv;
	}

	/**
	 * 服务器端添加方法
	 * @param mv
	 * @param expressSendFee
	 * @return
	 */
	@RequestMapping("addExpressSendFeeList")
	public ModelAndView addExpressSendFee(ModelAndView mv,@ModelAttribute ExpressSendFee expressSendFee){
		if (iExpressSendFeeService.addExpressSendFee(expressSendFee)) {
			mv.addObject("fdid",expressSendFee.getFdid());
			mv.setViewName("Express/expressfdex/expressSendFeeList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","add");
			map.put("error","1");
			mv.addObject("code",map);
			mv.setViewName("Express/expressfdex/addExpressSendFee");
		}
		return mv;
	}

	/**
	 * 服务器端编辑方法
	 * @param mv
	 * @param expressSendFee
	 * @return
	 */
	@RequestMapping("updateExpressSendFeeList")
	public ModelAndView updateExpressSendFee(ModelAndView mv,@ModelAttribute ExpressSendFee expressSendFee){
		if (iExpressSendFeeService.updateExpressSendFee(expressSendFee)) {
			mv.addObject("fdid",expressSendFee.getFdid());
			mv.setViewName("Express/expressfdex/expressSendFeeList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","update");
			map.put("error","2");
			mv.addObject("code",map);
			mv.addObject("updateSendFee",iExpressSendFeeService.obtainExpressSendFeeById(expressSendFee.getSendid()));
			mv.setViewName("Express/expressfdex/addExpressSendFee");
		}
		return mv;
	}


	/**
	 * 获取派送费列表
	 * @param expressDto
	 * @param expressSendFeeDto
	 * @return {rows:[ExpressSendFeel]   数据列表,total:Integer  总数}
     */
	@RequestMapping("mobile/jsonExpressSendFeeList")
	@ResponseBody
	public String jsonExpressSendFeeList(@ModelAttribute ExpressDto expressDto, @ModelAttribute ExpressSendFeeDto expressSendFeeDto){
		return JSON.toJSONString(iExpressSendFeeService.obtainExpredssSendFeeList(expressDto,expressSendFeeDto));
	}
	
	/**
	 * 根据id获取
	 * @param sendId
	 * @return ExpressSendFee的json形式
     */
	@RequestMapping("jsonExpressSendFeeOne")
	@ResponseBody
	public String jsonExpressBillOne(Integer sendId){
		return JSON.toJSONString(iExpressSendFeeService.obtainExpressSendFeeById(sendId));
	}

	/**
	 * 添加派送费
	 * 客户端添加方法（邢）
	 * @param expressSendFee
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("mobile/addExpressSendFee")
	@ResponseBody
	public String addExpressSendFee(@ModelAttribute ExpressSendFee expressSendFee){
		if(iExpressSendFeeService.addExpressSendFee(expressSendFee)){
			return "addSuccess";
		}else {
			return "addFailed";
		}
	}

	/**
	 * 编辑派送费
	 * 客户端添加方法（邢）
	 * @param expressSendFee
	 * @return updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/updateExpressSendFee")
	@ResponseBody
	public String updateExpressSendFee(@ModelAttribute ExpressSendFee expressSendFee){
		if(iExpressSendFeeService.updateExpressSendFee(expressSendFee)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}

}
