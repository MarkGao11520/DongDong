package com.zrkj.ecp.controller.org.config;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.org.config.BasConfig;
import com.zrkj.ecp.dto.org.config.ConfigDto;
import com.zrkj.ecp.service.org.config.BasConfigService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("configController")
public class ConfigController {
    @Resource
    BasConfigService basConfigService;
    @Resource
    BasUserService basUserService;

    /**
     * 获取设置列表
     *
     * @param mv
     * @param configDto
     * @return
     */
    @RequestMapping("ConfigList")
    public ModelAndView ConfigList(ModelAndView mv, @ModelAttribute ConfigDto configDto) {
        mv.addObject("cid", configDto.getCid());
        mv.setViewName("System/org/config/configList");
        return mv;
    }

    @RequestMapping("jsonConfigList")
    @ResponseBody
    public String jsonConfigList(@ModelAttribute ConfigDto configDto) {

                return JSON.toJSONString(basConfigService.selectConfigList(configDto));


    }

    /**
     * 新增显示
     *
     * @param mv
     * @param basConfig
     * @return
     */
    @RequestMapping("addConfigInput")
    public ModelAndView addConfigInput(ModelAndView mv, @ModelAttribute BasConfig basConfig) {
        mv.addObject("updateConfig", basConfig);
        Map<String, String> map = new HashMap<String, String>();
        map.put("url", "add");
        map.put("name", "新增");
//        mv.addObject("updateConfig",new BasConfig());
        mv.addObject("code", map);
        mv.setViewName("System/org/config/addConfig");
        return mv;
    }

    /**
     * 新增
     *
     * @param mv
     * @param basConfig
     * @param configDto
     * @param session
     * @return
     */
    @RequestMapping(value = "addConfig", method = RequestMethod.POST)
    public ModelAndView addConfig(ModelAndView mv, @ModelAttribute BasConfig basConfig, @ModelAttribute ConfigDto configDto, HttpSession session) {
        basConfigService.addConfig(basConfig);
        BasLog basLog = (BasLog) session.getAttribute("log");
        basLog.setModname("系统设置");
        basLog.setActionname("添加");
        basLog.setContent("添加一个新的设置：" + JSON.toJSONString(basConfig));
        basLog.setCreatedate(new Date());
        basUserService.insertlog(basLog);
        mv.addObject("cid", configDto.getCid());
        mv.setViewName("System/org/config/configList");
        return mv;
    }

    /**
     * 编辑显示
     *
     * @param mv
     * @param configid
     * @return
     */
    @RequestMapping(value = "updateConfigInput", method = RequestMethod.GET)
    public ModelAndView updateConfigInput(ModelAndView mv, Integer configid) {
        mv.addObject("updateConfig", basConfigService.selectConfigByCofId(configid));
        Map<String, String> map = new HashMap<String, String>();
        map.put("url", "update");
        map.put("name", "编辑");
        mv.addObject("code", map);
        mv.setViewName("System/org/config/addConfig");
        return mv;
    }

    /**
     * 修改
     *
     * @param mv
     * @param basConfig
     * @param configDto
     * @param session
     * @return
     */
    @RequestMapping(value = "updateConfig", method = RequestMethod.POST)
    public ModelAndView updateConfig(ModelAndView mv, @ModelAttribute BasConfig basConfig, @ModelAttribute ConfigDto configDto, HttpSession session) {
        basConfigService.updateConfig(basConfig);
        BasLog basLog = (BasLog) session.getAttribute("log");
        basLog.setModname("系统设置");
        basLog.setActionname("修改");
        basLog.setContent("修改一个系统设置：" + JSON.toJSONString(basConfig));
        basLog.setCreatedate(new Date());
        basUserService.insertlog(basLog);
        mv.addObject("cid", configDto.getCid());
        mv.setViewName("System/org/config/configList");
        return mv;
    }

    @RequestMapping(value = "delteConfig", method = RequestMethod.POST)
    public void deleteConfig(HttpSession session, String allConfig, HttpServletResponse response) {
        //System.out.println("123");
        try {
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            BasLog basLog = (BasLog) session.getAttribute("log");
            basLog.setModname("机构管理");
            basLog.setActionname("删除");
            String result = basConfigService.deleteConfig(allConfig);
            basLog.setContent("删除id:" + allConfig + "删除结果：" + result);
            basLog.setCreatedate(new Date());
            basUserService.insertlog(basLog);
            out.print(result);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
