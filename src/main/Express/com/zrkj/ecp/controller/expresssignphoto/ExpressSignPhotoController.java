package com.zrkj.ecp.controller.expresssignphoto;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expresssignphoto.ExpressSignPhoto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresssignphoto.IExpressSignPhoto;
import org.springframework.web.servlet.ModelAndView;

/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressSignPhotoController")
public class ExpressSignPhotoController {
	@Resource
	IExpressSignPhoto iExpressSignPhotoService;

	/**
	 * 服务器端界面
	 * @return
	 */
	@RequestMapping("expressSignPhotoList")
	public ModelAndView expressSignPhotoList(ModelAndView mv){
		mv.setViewName("Express/expresssignphoto/expressSignPhotoList");
		return mv;
	}

	/**
	 * 获取签收照片列表
	 * @param expressDto
	 * @return {rows:[ExpressSignPhoto]   数据列表,total:Integer  总数}
     */
	@RequestMapping("jsonExpressSignPhotoList")
	@ResponseBody
	public String jsonExpressSignPhotoList(@ModelAttribute ExpressDto expressDto){
		return JSON.toJSONString(iExpressSignPhotoService.obtainExpredssSignPhotoList(expressDto));
	}

	/**
	 * 根据id获取签收照片
	 * @param SignPhotoId
	 * @return ExpressSignPhoto的json形式
     */
	@RequestMapping("jsonExpressSignPhotoOne")
	@ResponseBody
	public String jsonExpressSignPhotoOne(Integer SignPhotoId){
		return JSON.toJSONString(iExpressSignPhotoService.obtainExpressSignPhotoById(SignPhotoId));
	}


	/**
	 * 添加签收照片
	 * @param expressSignPhoto
	 * @return 添加成功：addSuccess   添加失败：addFailed
     */
	@RequestMapping("addExpressSignPhoto")
	@ResponseBody
	public String addExpressSignPhoto(@ModelAttribute ExpressSignPhoto expressSignPhoto){
		if(iExpressSignPhotoService.addExpressSignPhoto(expressSignPhoto)){
			return "addSuccess";
		}else {
			return "addFailed";
		}
	}

	/**
	 * 编辑签收照片
	 * @param expressSignPhoto
	 * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
	 */
	@RequestMapping("updateExpressSignPhoto")
	@ResponseBody
	public String updateExpressSignPhoto(@ModelAttribute ExpressSignPhoto expressSignPhoto){
		if(iExpressSignPhotoService.updateExpressSignPhoto(expressSignPhoto)){
			return "updateSuccess";
		}else {
			return "updateFailed";
		}
	}

}
