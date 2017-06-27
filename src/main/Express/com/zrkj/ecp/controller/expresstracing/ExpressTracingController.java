package com.zrkj.ecp.controller.expresstracing;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expresstracing.ExpressTracingDto;
import com.zrkj.ecp.service.expresstracing.IExpressTracing;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by gaowenfeng on 2017/1/20.
 */
/**
 * 每个方法的url为：类前的@RequestMapping的参数/方法前的@RequestMapping的参数
 * @author gaowenfeng
 *
 */
@Controller
@RequestMapping("expressTracingController")
public class ExpressTracingController {
    @Resource
    IExpressTracing iExpressTracingService;

    /**
	 * 服务器界端界面
	 */
    @RequestMapping("expressTracingList")
    public ModelAndView expressTracingList(ModelAndView mv,Integer billcode){
        mv.addObject("billcode",billcode);
        mv.setViewName("Express/expresstracing/expressTracingList");
        return mv;
    }

    /**
     * 获取快递跟踪列表
     * @param expressDto
     * @param expressTracingDto
     * @return  {rows:[ExpressTracing]   数据列表,total:Integer  总数}
     */
    @RequestMapping("jsonExpressTracingList")
    @ResponseBody
    public String jsonExpressTracingList(@ModelAttribute ExpressDto expressDto, @ModelAttribute ExpressTracingDto expressTracingDto){
        return JSON.toJSONString(iExpressTracingService.obtainExpredssTracingList(expressDto,expressTracingDto));
    }

    /**
     * 根据id获取快递跟踪
     * @param TracingId
     * @return  ExpressTracing的json形式
     */
    @RequestMapping("jsonExpressTracingOne")
    @ResponseBody
    public String jsonExpressTracingOne(Integer TracingId){
        return JSON.toJSONString(iExpressTracingService.obtainExpressTracingById(TracingId));
    }


    /**
     * 添加快递跟踪记录
     * @param expressTracing
     * @return 添加成功：addSuccess   添加失败：addFailed
     */
    @RequestMapping("addExpressTracing")
    @ResponseBody
    public String addExpressTracing(@ModelAttribute ExpressTracing expressTracing){
        if(iExpressTracingService.addExpressTracing(expressTracing)){
            return "addSuccess";
        }else {
            return "addFailed";
        }
    }

    /**
     * 编辑快递单
     * @param expressTracing
     * @return 修改成功 ： updateSuccess，修改失败 ：updateFailed
     */
    @RequestMapping("updateExpressTracing")
    @ResponseBody
    public String updateExpressTracing(@ModelAttribute ExpressTracing expressTracing){
        if(iExpressTracingService.updateExpressTracing(expressTracing)){
            return "updateSuccess";
        }else {
            return "updateFailed";
        }
    }

}
