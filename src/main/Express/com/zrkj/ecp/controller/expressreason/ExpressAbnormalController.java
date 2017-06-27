package com.zrkj.ecp.controller.expressreason;

import javax.annotation.Resource;

import com.zrkj.ecp.Utils.BillStatus;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.service.expressbill.IExpressBillService;
import com.zrkj.ecp.service.expresstracing.IExpressTracing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expressreason.ExpressAbnormal;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressreason.IExpressAbnormalService;
import org.springframework.web.servlet.ModelAndView;

/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressAbnormalController")
public class ExpressAbnormalController {
	@Resource
	IExpressAbnormalService iExpressAbnormalService;
	@Resource
	IExpressBillService iExpressBillService;
	@Resource
	IExpressTracing iExpressTracing;

	/**
	 * 服务器端列表界面
	 * @param mv
	 * @return
	 */
	@RequestMapping("expressAbnormalList")
	public ModelAndView expressAbnormalList(ModelAndView mv){
		mv.setViewName("Express/expressreason/expressAbnormal");
		return mv;
	}

	/**
	 * 获取异常件列表
	 * @param expressDto
	 * @return {rows:[ExpressAbnormal]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonExpressAbnormalList")
	@ResponseBody
	public String jsonExpressAbnormalList(@ModelAttribute ExpressDto expressDto){
		return JSON.toJSONString(iExpressAbnormalService.obtainExpredssAbnormalList(expressDto));
	}

	/**
	 * 根据id获取异常件
	 * @param AbnormalId
	 * @return ExpressAbnormal 的json形式
     */
	@RequestMapping("jsonExpressAbnormalOne")
	@ResponseBody
	public String jsonExpressAbnormalOne(Integer AbnormalId){
		return JSON.toJSONString(iExpressAbnormalService.obtainExpressAbnormalById(AbnormalId));
	}


	/**
	 * 添加异常件
	 * @param expressAbnormal
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("mobile/addExpressAbnormal")
	@ResponseBody
	public String addExpressAbnormal(@ModelAttribute ExpressAbnormal expressAbnormal,Integer stauts){
		Integer currentstauts = iExpressBillService.findCurrentStatusByBillCode(expressAbnormal.getBillcode());
		if(iExpressAbnormalService.addExpressAbnormal(expressAbnormal,stauts)){
			ExpressTracing expressTracing = new ExpressTracing();
			expressTracing.setBillcode(expressAbnormal.getBillcode());
			expressTracing.setStatusbefore(currentstauts);
			expressTracing.setStatusafter(stauts);
			expressTracing.setUid(expressAbnormal.getUid());
			iExpressTracing.addExpressTracing(expressTracing);
			return RETURNCODE.SUCCESS;
		}else {
			return RETURNCODE.ADDFAILED;
		}
	}

	/**
	 * 编辑异常件
	 * @param expressAbnormal
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/updateExpressAbnormal")
	@ResponseBody
	public String updateExpressAbnormal(@ModelAttribute ExpressAbnormal expressAbnormal){
		if(iExpressAbnormalService.updateExpressAbnormal(expressAbnormal)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}


}
