package com.zrkj.ecp.controller.expressfdex;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expressfdex.ExpressFdex;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressfdex.IExpressFdexService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/20.
 */
/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressFdexController")
public class ExpressFdexController {
	@Resource
	IExpressFdexService iExpressFdexService;

	/**
	 * 快递公司界面
	 * @param mv
	 * @return
	 */
	@RequestMapping("expressFdexList")
	public ModelAndView expressFdexList(ModelAndView mv){
		mv.setViewName("Express/expressfdex/expressFdexList");
		return mv;
	}


	/**
	 * 添加快递公司界面
	 */
	@RequestMapping("addExpressFdexInput")
	public ModelAndView addexpressFdexInput(ModelAndView mv){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","add");
		map.put("error","0");
		mv.addObject("code",map);
		mv.addObject("updateFdex",new ExpressFdex());
		mv.setViewName("Express/expressfdex/addExpressFdex");
		return mv;
	}

	/**
	 * 编辑快递公司界面
	 * @param mv
	 * @param fdid
	 * @return
	 */
	@RequestMapping("updateExpressFdexInput")
	public ModelAndView updateexpressFdexInput(ModelAndView mv,Integer fdid){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","update");
		map.put("error","0");
		mv.addObject("code",map);
		mv.addObject("updateFdex",iExpressFdexService.obtainExpressFdexById(fdid));
		mv.setViewName("Express/expressfdex/addExpressFdex");
		return mv;
	}

	/**
	 * 服务器端添加方法
	 * @param mv
	 * @param expressFdex
	 * @return
	 */
	@RequestMapping("addExpressFdexList")
	public ModelAndView addExpressFdex(ModelAndView mv,@ModelAttribute ExpressFdex expressFdex){
		if (iExpressFdexService.addExpressFdex(expressFdex)) {
			mv.setViewName("Express/expressfdex/expressFdexList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","add");
			map.put("error","1");
			mv.addObject("code",map);
			mv.setViewName("Express/expressfdex/addExpressFdex");
		}
		return mv;
	}

	/**
	 * 服务器端编辑方法
	 * @param mv
	 * @param expressFdex
	 * @return
	 */
	@RequestMapping("updateExpressFdexList")
	public ModelAndView updateExpressFdex(ModelAndView mv,@ModelAttribute ExpressFdex expressFdex){
		if (iExpressFdexService.updateExpressFdex(expressFdex)) {
			mv.setViewName("Express/expressfdex/expressFdexList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","update");
			map.put("error","2");
			mv.addObject("code",map);
			mv.addObject("updateFdex",iExpressFdexService.obtainExpressFdexById(expressFdex.getFdid()));
			mv.setViewName("Express/expressfdex/addExpressFdex");
		}
		return mv;
	}

	/**
	 * 获取快递公司列表
	 * @param expressDto
	 * @return {rows:[ExpressFdexl]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonExpressFdexList")
	@ResponseBody
	public String jsonExpressFdexList(@ModelAttribute ExpressDto expressDto){
		return JSON.toJSONString(iExpressFdexService.obtainExpredssFdexList(expressDto));
	}
	
	/**
	 * 根据id获取
	 * @param fdexId
	 * @return ExpressFdex 的json形式
     */
	@RequestMapping("jsonExpressFdexOne")
	@ResponseBody
	public String jsonExpressBillOne(Integer fdexId){
		return JSON.toJSONString(iExpressFdexService.obtainExpressFdexById(fdexId));
	}

	/**
	 * 获取快递公司列表(id,名称)
	 * @return
     */
	@RequestMapping("jsonExpressfdexDtoList")
	@ResponseBody
	public String jsonExpressDtoList(){
		return JSON.toJSONString(iExpressFdexService.obtainExpressFdexDtoList());
	}

	/**
	 * 客户端添加快递公司方法（邢）
	 * @param expressFdex
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("mobile/addExpressFdex")
	@ResponseBody
	public String addExpressFdex(@ModelAttribute ExpressFdex expressFdex){
		if(iExpressFdexService.addExpressFdex(expressFdex)){
			return "addSuccess";
		}else {
			return "addFailed";
		}
	}

	/**
	 *客户端 编辑快递公司方法（邢）
	 * @param expressFdex
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/updateExpressFdex")
	@ResponseBody
	public String updateExpressFdex(@ModelAttribute ExpressFdex expressFdex){
		if(iExpressFdexService.updateExpressFdex(expressFdex)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}


}
