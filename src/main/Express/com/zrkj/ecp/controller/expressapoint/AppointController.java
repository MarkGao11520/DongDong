package com.zrkj.ecp.controller.expressapoint;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.BillStatus;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dto.expressbill.ExpressBillDto;
import com.zrkj.ecp.service.expressappoint.IExpressAppoinService;
import com.zrkj.ecp.service.expressbill.IExpressBillService;
import com.zrkj.ecp.service.expresstracing.IExpressTracing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
@Controller
@RequestMapping("appointController")
public class AppointController {
    @Resource
    IExpressBillService iExpressBillService;
    @Resource
    IExpressAppoinService iExpressAppoinService;
    @Resource
    IExpressTracing iExpressTracing;

    @RequestMapping("appointMain")
    public ModelAndView appointMain(ModelAndView mv){
        mv.setViewName("Express/exprssapoint/apointMain");
        return mv;
    }

    @RequestMapping("appointLeft")
    public ModelAndView appointLeft(ModelAndView mv){
        mv.setViewName("Express/exprssapoint/userLeft");
        return mv;
    }

    @RequestMapping("jsonUserList")
    @ResponseBody
    public String jsonUserList(){
        return JSON.toJSONString(iExpressAppoinService.obtainUserListByPposid());
    }

    @RequestMapping("appointList")
    public ModelAndView appointList(ModelAndView mv,Integer uid){
        mv.addObject("uid",uid);
        mv.setViewName("Express/exprssapoint/apointList");
        return mv;
    }

    @RequestMapping("appointList1")
    public ModelAndView appointList1(ModelAndView mv){
        mv.setViewName("Express/exprssapoint/apointList1");
        return mv;
    }

    @RequestMapping("jsonappointList")
    @ResponseBody
    public String appointList(ExpressBillDto expressBillDto){
        expressBillDto.setBillStauts(3);
        return JSON.toJSONString(iExpressBillService.obtainExpredssBillList(expressBillDto));
    }

    @RequestMapping("appointExpress")
    @ResponseBody
    public String appointExpress(String billIdStr,Integer uid){
        try{
        String billId[] = billIdStr.split(",");
        for (String string:billId){
            Integer status = iExpressBillService.findCurrentStatusByBillId(Integer.parseInt(string));
            System.out.println("status:"+status);
            System.out.println("billid:"+string);
            System.out.println("uid:"+uid);
            if(status == 900){
                System.out.println("yes");
                ExpressBill expressBill = new ExpressBill();
                expressBill.setBillid(Integer.parseInt(string));
                expressBill.setStauts(BillStatus.INTHEDELIVERY);
                expressBill.setRevuserid(uid);
                if(iExpressBillService.updateExpressBill(expressBill)) {
                    ExpressTracing expressTracing = new ExpressTracing();
                    expressTracing.setBillcode(expressBill.getBillcode());
                    expressTracing.setStatusbefore(BillStatus.INTHEDEREADY);
                    expressTracing.setStatusafter(BillStatus.INTHEDELIVERY);
                    iExpressTracing.addExpressTracing(expressTracing);
                }
            }else if(status == 300){
                ExpressBill expressBill = new ExpressBill();
                expressBill.setBillid(Integer.parseInt(string));
                expressBill.setStauts(BillStatus.TOTHERECEIVER);
                expressBill.setRevuserid(uid);
                if(iExpressBillService.updateExpressBill(expressBill)){
                    ExpressTracing expressTracing = new ExpressTracing();
                    expressTracing.setBillcode(expressBill.getBillcode());
                    expressTracing.setStatusbefore(BillStatus.COURIERRECEIPT);
                    expressTracing.setStatusafter(BillStatus.TOTHERECEIVER);
                    iExpressTracing.addExpressTracing(expressTracing);
                }
            }
        }
        return RETURNCODE.SUCCESS;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return RETURNCODE.UPDATEFAILED;
    }
}
