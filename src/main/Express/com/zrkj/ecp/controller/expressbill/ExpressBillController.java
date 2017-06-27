package com.zrkj.ecp.controller.expressbill;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.BillStatus;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.expressfdex.ExpressFdex;
import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dto.expressbill.ExpressBillDto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.member.MemberBasDeatilDto;
import com.zrkj.ecp.service.expressbill.IExpressBillService;
import com.zrkj.ecp.service.expressfdex.IExpressFdexService;
import com.zrkj.ecp.service.expresssms.ISmsTemplate;
import com.zrkj.ecp.service.expresstracing.IExpressTracing;
import com.zrkj.ecp.service.member.MemberService;
import com.zrkj.ecp.service.org.parame.BasParameService;
import com.zrkj.ecp.service.serviceimpl.expresssms.SmsBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zrkj.ecp.domain.expressbill.ExpressBill;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * 例如快递单列表界面的方法url为 expressBillController/expressBillList
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressBillController")
public class ExpressBillController {
	@Resource
	IExpressBillService iExpressBillService;
	@Resource
	IExpressTracing iExpressTracing;
	@Resource
	SmsBase smsBase;
	@Resource
	BasParameService basParameService;
	@Autowired
	ISmsTemplate iSmsTemplate;

	/**
	 * 快递单列表界面(寄件)
	 * @param mv
	 * @return
	 */
	@RequestMapping("expressBillList")
	public ModelAndView expressBillList(ModelAndView mv){
		mv.setViewName("Express/expressbill/expressBillList");
		return mv;
	}

	/**
	 * 快递单列表界面(派件)
	 * @param mv
	 * @return
	 */
	@RequestMapping("expressToSendBillList")
	public ModelAndView expressToSendBillList(ModelAndView mv){
		mv.setViewName("Express/expressbill/expressBillToSendList");
		return mv;
	}

	/**
	 * 添加快递单
	 * @param mv
	 * @return
     */
	@RequestMapping("addExpressBillInput")
	public ModelAndView addExpressBillInput(ModelAndView mv){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","add");
		map.put("error","0");
		mv.addObject("code",map);
		mv.addObject("updateBill",new ExpressBill());
		mv.setViewName("Express/expressbill/addBill");
		return mv;
	}

	/**
	 * 编辑快递单
	 * @param mv
	 * @param billid
	 * @return
	 */
	@RequestMapping("updateExpressBillInput")
	public ModelAndView updateExpressBillInput(ModelAndView mv,Integer billid){
		Map<String,String> map = new HashMap<String, String>();
		map.put("url","update");
		map.put("error","0");
		mv.addObject("code",map);
		mv.addObject("updateBill",iExpressBillService.obtainExpressBillById(billid));
		mv.setViewName("Express/expressbill/addBill");
		return mv;
	}

	/**
	 * 服务器端添加方法
	 * @param mv
	 * @param expressBill
	 * @return
	 */
	@RequestMapping("addExpressBill")
	public ModelAndView addExpressBill(ModelAndView mv,@ModelAttribute ExpressBill expressBill){
		if (iExpressBillService.addExpressBill(expressBill)) {
			mv.setViewName("Express/expressbill/expressBillList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","add");
			map.put("error","1");
			mv.addObject("code",map);
			mv.setViewName("Express/expressbill/addBill");
		}
		return mv;
	}

	/**
	 * 服务器端编辑方法
	 * @param mv
	 * @param expressBill
	 * @return
	 */
	@RequestMapping("updateExpressBill")
	public ModelAndView updateExpressBill(ModelAndView mv,@ModelAttribute ExpressBill expressBill){
		if (iExpressBillService.updateExpressBill(expressBill)) {
			mv.setViewName("Express/expressbill/expressBillList");
		} else {
			Map<String,String> map = new HashMap<String, String>();
			map.put("url","update");
			map.put("error","2");
			mv.addObject("code",map);
			mv.addObject("updateFdex",iExpressBillService.obtainExpressBillById(expressBill.getBillid()));
			mv.setViewName("Express/expressbill/addBill");
		}
		return mv;
	}

	/**
	 * 获取快递单列表(寄件状态)
	 * @param expressBillDto
	 * @return  {rows:[ExpressBill]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonSendExpressBillList")
	@ResponseBody
	public String jsonSendExpressBillList(@ModelAttribute ExpressBillDto expressBillDto,Integer code){
		if(code==null){
			expressBillDto.setBillStauts(1);
		}else if(code == 0){
			expressBillDto.setBillStauts(1);
		}else if(code == 1){
			expressBillDto.setBillStauts(3);
		}
		return JSON.toJSONString(iExpressBillService.obtainExpredssBillList(expressBillDto));
	}

	/**
	 * 获取快递单列表(派件状态)
	 * @param expressBillDto
	 * @return  {rows:[ExpressBill]   数据列表,total:Integer  总数}
	 */
	@RequestMapping("jsonToSendExpressBillList")
	@ResponseBody
	public String jsonToSendExpressBillList(@ModelAttribute ExpressBillDto expressBillDto,Integer code){
		if(code==null){
			expressBillDto.setBillStauts(2);
		}else if(code == 0){
			expressBillDto.setBillStauts(2);
		}else if(code == 1){
			expressBillDto.setBillStauts(3);
		}
		return JSON.toJSONString(iExpressBillService.obtainExpredssBillList(expressBillDto));
	}


	/**
	 * 根据id获取快递单
	 * @param billId
	 * @return Expressbill 的json形式
     */
	@RequestMapping("jsonExpressBillOne")
	@ResponseBody
	public String jsonExpressBillOne(Integer billId){
		return JSON.toJSONString(iExpressBillService.obtainExpressBillById(billId));
	}

	/**
	 * 根据收件人id获取派件列表
	 * @param expressBillDto
	 * @return
     */
	@RequestMapping("mobile/obtainExpressBillListByRevNameId")
	@ResponseBody
	public String obtainExpressBillListByRevNameId(ExpressBillDto expressBillDto){
		return JSON.toJSONString(iExpressBillService.obtainExpredssBillList(expressBillDto));
	}

	/**
	 * 下单配送
	 * @param billidstr
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/peisongEexpress")
	@ResponseBody
	public String peisongEexpress(String billidstr){
		String billid[] = billidstr.split(",");
		for (String string :billid){
			ExpressBill expressBill = new ExpressBill();
			expressBill.setBillid(Integer.parseInt(string));
			expressBill.setStauts(BillStatus.INTHEDEREADY);
			if(iExpressBillService.updateExpressBill(expressBill)){
				ExpressTracing expressTracing = new ExpressTracing();
				expressTracing.setBillcode(expressBill.getBillcode());
				expressTracing.setStatusbefore(iExpressBillService.findCurrentStatusByBillCode(expressBill.getBillcode()));
				expressTracing.setStatusafter(BillStatus.INTHEDEREADY);
				iExpressTracing.addExpressTracing(expressTracing);
			}else {
				return RETURNCODE.UPDATESTATUSFAIELD;
			}
		}
		return RETURNCODE.SUCCESS;
	}



	/**
	 * 添加快递单
	 * @param expressBill
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("mobile/addExpressBill")
	@ResponseBody
	public String addExpressBill(@ModelAttribute ExpressBill expressBill){
		if(iExpressBillService.addExpressBill(expressBill)){
			ExpressTracing expressTracing = new ExpressTracing();
			expressTracing.setBillcode(expressBill.getBillcode());
			expressTracing.setStatusbefore(iExpressBillService.findCurrentStatusByBillCode(expressBill.getBillcode()));
			expressTracing.setStatusafter(BillStatus.GENERATIONOFSIGNAFTERRECEIVINGSTH);
			iExpressTracing.addExpressTracing(expressTracing);
			return RETURNCODE.SUCCESS;
		}else {
			return RETURNCODE.ADDFAILED;
		}
	}

	/**
	 * 编辑快递单
	 * @param expressBill
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/updateExpressBill")
	@ResponseBody
	public String updateExpressBill(@ModelAttribute ExpressBill expressBill){
		if(iExpressBillService.updateExpressBill(expressBill)){
			return RETURNCODE.SUCCESS;
		}else {
			return RETURNCODE.UPDATEFAILED;
		}
	}


	/**
	 * 获取版本号
	 * @return
     */
	@RequestMapping("mobile/findVersion")
	@ResponseBody
	public String findVersion(){
		return iExpressBillService.findVersion();
	}

	/**
	 * 寄件
	 * @param expressBill
	 * @return 添加成功：addSuccess   添加失败：addFailed
	 */
	@RequestMapping("mobile/sendExpress")
	@ResponseBody
	public String sendExpress(@ModelAttribute ExpressBill expressBill){
		Map<String,String> map = iExpressBillService.addExpressBill(expressBill,"send");
		if(map.get("isSuccess").equals("true")){
			ExpressTracing expressTracing = new ExpressTracing();
			expressTracing.setBillcode(expressBill.getBillcode());
			expressTracing.setStatusbefore(iExpressBillService.findCurrentStatusByBillCode(expressBill.getBillcode()));
			expressTracing.setStatusafter(BillStatus.GENERATIONOFSIGNAFTERRECEIVINGSTH);
			iExpressTracing.addExpressTracing(expressTracing);
		}
		return JSON.toJSONString(map);
	}

	/**
	 * 入库
	 * @param expressBill
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/putAway")
	@ResponseBody
	public String putAway(@ModelAttribute ExpressBill expressBill,Integer uid) {

		if (!iExpressBillService.checkBillIsExists(expressBill.getBillcode())) {
			if (iExpressBillService.addExpressBill(expressBill)) {
				ExpressTracing expressTracing = new ExpressTracing();
				expressTracing.setBillcode(expressBill.getBillcode());
				expressTracing.setStatusbefore(500);
				expressTracing.setNotes("入库");
				expressTracing.setUid(uid);
				iExpressTracing.addExpressTracing(expressTracing);
//				String content = "您好，你的" + expressBill.getBillcode() + "编号【" + expressBill.getBillcode() + "】已经到达" + basParameService.seleteParameByid(expressBill.getPositionno()).getParaname() + "，如有疑问请下载APP，优先知道快递信息，送货上门。";
				StringBuilder content = new StringBuilder();
				String contents[] = iSmsTemplate.obtainByCid(expressBill.getCid()).getTemcontent().split("%s");
				if(contents!=null&&contents.length>0){
					content.append(contents[0]).append(expressBill.getSerialno()).append(contents[1]) .append(basParameService.seleteParameByid(expressBill.getPositionno()).getParaname()).append(contents[2]);
				}else{
					return RETURNCODE.MSGCODEERROR;
				}
				try {
					if (Integer.parseInt(smsBase.SendSms(expressBill.getRevphone(), content.toString())) > 0) {
						return RETURNCODE.SUCCESS;
					}else{
						return RETURNCODE.MSGCODEERROR;
					}
				} catch (UnsupportedEncodingException e) {
					System.out.println(e.getMessage());
					return RETURNCODE.MSGCODEERROR;
				}
			} else {
				return RETURNCODE.ADDFAILED;
			}
		} else {
			return RETURNCODE.BILLEXISTS;
		}
	}

	/**
	 * 派送件处理
	 * @param expressBill
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/waitsendEexpress")
	@ResponseBody
	public String waitsendEexpress(@ModelAttribute ExpressBill expressBill){
		Integer currentStatus = iExpressBillService.findCurrentStatusByBillCode(expressBill.getBillcode());
		if(currentStatus == 600){
		if(iExpressBillService.updateExpressBill(expressBill)){
			ExpressTracing expressTracing = new ExpressTracing();
			expressTracing.setBillcode(expressBill.getBillcode());
			expressTracing.setStatusbefore(currentStatus);
			expressTracing.setStatusafter(expressBill.getStauts());
			expressTracing.setUid(expressBill.getRevuserid());
			expressTracing.setNotes("派送件处理");
			iExpressTracing.addExpressTracing(expressTracing);
			return RETURNCODE.SUCCESS;
		}else {
			return RETURNCODE.UPDATESTATUSFAIELD;
		}
		}else if(currentStatus == 900){
			return RETURNCODE.NOTAPPOINT;
		}else{
			return RETURNCODE.STATUSABNORMAL;
		}
	}

	/**
	 * 出库处理
	 * @param expressBill
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/singinExpress")
	@ResponseBody
	public String singinExpress(@ModelAttribute ExpressBill expressBill,Integer uid){
//		expressBill.setBillid(iExpressBillService.obtainBillCodeByBillId(expressBill));
		Integer currentStauts = iExpressBillService.findCurrentStatusByBillCode(expressBill.getBillcode());
		if(iExpressBillService.updateExpressBill(expressBill)){
			ExpressTracing expressTracing = new ExpressTracing();
			expressTracing.setBillcode(expressBill.getBillcode());
			expressTracing.setStatusbefore(currentStauts);
			expressTracing.setStatusafter(BillStatus.HAVETOSIGNFOR);
			expressTracing.setUid(uid);
			expressTracing.setNotes("出库处理");
			iExpressTracing.addExpressTracing(expressTracing);
			return RETURNCODE.SUCCESS;
		}else {
			return RETURNCODE.UPDATESTATUSFAIELD;
		}
	}


	/**
	 * 移库操作
	 * @param expressBill
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("mobile/moveExpress")
	@ResponseBody
	public String moveExpress(@ModelAttribute ExpressBill expressBill){
		if(iExpressBillService.updateExpressBill(expressBill)){
			return RETURNCODE.SUCCESS;
		}else {
			return RETURNCODE.UPDATEFAILED;
		}
	}

	/**
	 * 根据billcode获取billid
	 */
	@RequestMapping("mobile/obtainBillCodeByBillId")
	@ResponseBody
	public String obtainBillCodeByBillId(String billcode){
		return iExpressBillService.obtainBillCodeByBillId(billcode);
	}

}
