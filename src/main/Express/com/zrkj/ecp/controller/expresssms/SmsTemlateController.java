package com.zrkj.ecp.controller.expresssms;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expresssms.SmsTemplate;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresssms.ISmsTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("smsTemlateController")
public class SmsTemlateController {
	@Resource
	ISmsTemplate iSmsTemplateService;

	/**
	 * 服务器端短信界面
	 * @return
	 */
	@RequestMapping("SmsTemplateLeft")
	public ModelAndView SmsTemplateLeft(){
		return new ModelAndView("Express/expresssms/template/TemplateLeft");
	}

	/**
	 * 服务器端短信界面
	 */
	@RequestMapping("SmsTemplateMain")
	public ModelAndView SmsTemplateMain(){
		return new ModelAndView("Express/expresssms/template/TemplateMain");
	}

	/**
	 * 服务器端短信界面
	 */
	@RequestMapping("SmsTemplateList")
	public ModelAndView SmsTemplateList(ModelAndView mv,Integer cid){
		mv.addObject("cid",cid);
		mv.setViewName("Express/expresssms/template/TemplateList");
		return mv;
	}

	/**
	 * 服务器端短信界面
	 */
	@RequestMapping("addSmsTemplateInput")
	public ModelAndView addSmsTemplateInput(ModelAndView mv,SmsTemplate SmsTemplate){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","add");
		mv.addObject("code",map);
		mv.addObject("updateSmsTemplate",SmsTemplate);
		mv.setViewName("Express/expresssms/template/addTemplate");
		return mv;
	}

	/**
	 * 服务器端短信界面
	 */
	@RequestMapping("updateSmsTemplateInput")
	public ModelAndView updateSmsTemplateFeeInput(ModelAndView mv,Integer temid){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","update");
		mv.addObject("code",map);
		mv.addObject("updateSmsTemplate",iSmsTemplateService.obtainSmsTemplateById(temid));
		mv.setViewName("Express/expresssms/template/addTemplate");
		return mv;
	}

	/**
	 * 服务器端短信界面
	 */
	@RequestMapping("addSmsTemplateList")
	public ModelAndView addSmsTemplateList(ModelAndView mv,@ModelAttribute SmsTemplate SmsTemplate){
		if (iSmsTemplateService.addSmsTemplate(SmsTemplate)) {
			mv.addObject("cid",SmsTemplate.getCid());
			mv.setViewName("Express/expresssms/template/TemplateList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","add");
			map.put("error","1");
			mv.addObject("code",map);
			mv.addObject("updateSmsTemplate",SmsTemplate);
			mv.setViewName("Express/expresssms/template/addTemplate");
		}
		return mv;
	}

	/**
	 * 服务器端短信界面
	 */
	@RequestMapping("updateSmsTemplateList")
	public ModelAndView updateSmsTemplateList(ModelAndView mv,@ModelAttribute SmsTemplate SmsTemplate){
		if (iSmsTemplateService.updateSmsTemplate(SmsTemplate)) {
			mv.addObject("cid",SmsTemplate.getCid());
			mv.setViewName("Express/expresssms/template/TemplateList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","update");
			map.put("error","2");
			mv.addObject("code",map);
			mv.addObject("updateSmsTemplate",iSmsTemplateService.obtainSmsTemplateById(SmsTemplate.getTemid()));
			mv.setViewName("Express/expresssms/template/addTemplate");
		}
		return mv;
	}


	/**
	 * 获取短信模板列表
	 * @param expressDto
	 * @param smsTemplateDto
	 * @return {rows:[SmsTemplate]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonSmsTemplateList")
	@ResponseBody
	public String jsonSmsTemplateList(@ModelAttribute ExpressDto expressDto, @ModelAttribute SmsTemplateDto smsTemplateDto){
		return JSON.toJSONString(iSmsTemplateService.obtainSmsTemplateList(expressDto,smsTemplateDto));
	}

	/**
	 * 根据id获取短信模板
	 * @param temId
	 * @return SmsTemplate的json形式
     */
	@RequestMapping("jsonSmsTemplateOne")
	@ResponseBody
	public String jsonSmsTemplateOne(Integer temId){
		return JSON.toJSONString(iSmsTemplateService.obtainSmsTemplateById(temId));
	}


	/**
	 * 添加短信模板
	 * @param SmsTemplate
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */ 
	@RequestMapping("addSmsTemplate")
	@ResponseBody
	public String addSmsTemplate(@ModelAttribute SmsTemplate SmsTemplate){
		try{
			if(iSmsTemplateService.addSmsTemplate(SmsTemplate)){
				return "addSuccess";
			}else {
				return "addFailed";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "addFailed";
	}

	/**
	 * 编辑短信模板
	 * @param SmsTemplate
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("updateSmsTemplate")
	@ResponseBody
	public String updateSmsTemplate(@ModelAttribute SmsTemplate SmsTemplate){
		if(iSmsTemplateService.updateSmsTemplate(SmsTemplate)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}


}
