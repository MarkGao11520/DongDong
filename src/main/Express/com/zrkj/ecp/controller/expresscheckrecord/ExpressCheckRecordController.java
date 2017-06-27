package com.zrkj.ecp.controller.expresscheckrecord;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.expresscheckrecord.ExpressCheckRecord;
import com.zrkj.ecp.dto.expresscheckrecord.ExpressCheckRecordDto;
import com.zrkj.ecp.service.expresscheckrecord.IExpressCheckRecord;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
@Controller
@RequestMapping("expressCheckRecordController")
public class ExpressCheckRecordController {
    @Resource
    IExpressCheckRecord iExpressCheckRecord;

    @RequestMapping("checkExpress")
    @ResponseBody
    public String checkExpress(ExpressCheckRecord expressCheckRecord){
//        expressCheckRecord.setBillcode(1111111111);
        if(iExpressCheckRecord.addCheckRecord(expressCheckRecord)){
            return RETURNCODE.SUCCESS;
        }else{
            return RETURNCODE.ADDFAILED;
        }
    }

    @RequestMapping("checkRecordList")
    public ModelAndView checkRecordList(ModelAndView mv){
        mv.setViewName("Express/expresscheckrecord/checkRecordList");
        return mv;
    }

    @RequestMapping("jsonCheckRecordList")
    @ResponseBody
    public String jsonCheckRecordList(ExpressCheckRecordDto expressCheckRecordDto){
        return JSON.toJSONString(iExpressCheckRecord.obtainCheckRecordList(expressCheckRecordDto));
    }

}
