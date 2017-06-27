package com.zrkj.ecp.controller.expresssms;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expresssms.SmsRecord;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresssms.ISmsRecordService;
import org.springframework.web.servlet.ModelAndView;

/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("smsRecordController")
public class SmsRecordController {
	@Resource
	ISmsRecordService iSmsRecordService;

	/**
	 * 服务器端短信界面
	 * @return
	 */
	@RequestMapping("SmsRecordLeft")
	public ModelAndView SmsRecordLeft(){
		return new ModelAndView("Express/expresssms/Record/RecordLeft");
	}

	/**
	 * 服务器端短信界面
	 * @return
	 */
	@RequestMapping("SmsRecordMain")
	public ModelAndView SmsRecordMain(){
		return new ModelAndView("Express/expresssms/Record/RecordMain");
	}

	/**
	 * 服务器端短信界面
	 * @return
	 */
	@RequestMapping("SmsRecordList")
	public ModelAndView SmsRecordList(ModelAndView mv,Integer cid){
		mv.addObject("cid",cid);
		mv.setViewName("Express/expresssms/Record/RecordList");
		return mv;
	}

	/**
	 * 获取短信记录列表
	 * @param expressDto
	 * @param smsTemplateDto
	 * @return  {rows:[SmsRecord]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonSmsRecordList")
	@ResponseBody
	public String jsonSmsRecordList(@ModelAttribute ExpressDto expressDto, @ModelAttribute SmsTemplateDto smsTemplateDto){
		return JSON.toJSONString(iSmsRecordService.obtainExpredssBillList(expressDto,smsTemplateDto));
	}

	/**
	 * 根据id获取短信记录
	 * @param billId
	 * @return SmsRecord的json形式
     */
	@RequestMapping("jsonSmsRecordOne")
	@ResponseBody
	public String jsonSmsRecordOne(Integer recordId){
		return JSON.toJSONString(iSmsRecordService.obtainSmsRecordById(recordId));
	}


	/**
	 * 添加短信记录
	 * @param SmsRecord
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("addSmsRecord")
	@ResponseBody
	public String addSmsRecord(@ModelAttribute SmsRecord SmsRecord){
		if(iSmsRecordService.addSmsRecord(SmsRecord)){
			return "addSuccess";
		}else {
			return "addFailed";
		}
	}

	/**
	 * 编辑短信记录
	 * @param SmsRecord
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("updateSmsRecord")
	@ResponseBody
	public String updateSmsRecord(@ModelAttribute SmsRecord SmsRecord){
		if(iSmsRecordService.updateSmsRecord(SmsRecord)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}


}
