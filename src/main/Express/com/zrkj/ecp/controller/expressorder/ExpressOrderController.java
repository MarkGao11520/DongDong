package com.zrkj.ecp.controller.expressorder;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.PayRuselt;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.alipay.constant.Constant;
import com.zrkj.ecp.alipay.constant.OrderInfoUtil2_0;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.expressorder.ExpressOrder;
import com.zrkj.ecp.domain.expresssms.SmsTemplate;
import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressbill.IExpressBillService;
import com.zrkj.ecp.service.expressorder.IExpressOrderService;
import com.zrkj.ecp.service.expresstracing.IExpressTracing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/3/12.
 */
@Controller
@RequestMapping("expressOrderController")
public class ExpressOrderController {
    @Autowired
    IExpressOrderService iExpressOrderService;
    @Autowired
    IExpressBillService iExpressBillService;
    @Autowired
    IExpressTracing iExpressTracing;

    /**
     * 服务器端订单界面
     */
    @RequestMapping("expressOrderList")
    public ModelAndView expressLogList(ModelAndView mv){
        mv.setViewName("Express/expressorder/expressOrderList");
        return mv;
    }

    /**
     * 获取操作订单列表
     */
    @RequestMapping("jsonExpressOrderList")
    @ResponseBody
    public String jsonExpressLogList(@ModelAttribute ExpressDto expressDto){
        return JSON.toJSONString(iExpressOrderService.obtainExpredssOrderList(expressDto));
    }

    /**
     * 根据id获取订单
     */
    @RequestMapping("jsonExpressOrderOne")
    @ResponseBody
    public String jsonExpressLogOne(Integer orderId){
        return JSON.toJSONString(iExpressOrderService.obtainExpressOrderById(orderId));
    }

    /**
     * 服务器端添加订单界面
     */
    @RequestMapping("addExpressOrderInput")
    public ModelAndView addExpressOrderInput(ModelAndView mv,ExpressOrder expressOrder){
        Map<String,String> map = new HashMap<String, String>();
        mv.addObject("code","addExpressOrder");
        mv.addObject("updateExpressOrder",expressOrder);
        mv.setViewName("Express/expressorder/addExpressOrder");
        return mv;
    }

    /**
     * 服务器端添加订单界面
     */
    @RequestMapping("updateExpressOrderInput")
    public ModelAndView updateExpressOrderInput(ModelAndView mv,Integer orderid){
        Map<String,String> map = new HashMap<String, String>();
        mv.addObject("code","updateExpressOrder");
        mv.addObject("updateExpressOrder",iExpressOrderService.obtainExpressOrderById(orderid));
        mv.setViewName("Express/expressorder/addExpressOrder");
        return mv;
    }

    /**
     * 服务器端添加订单
     */
    @RequestMapping("addExpressOrder")
    public ModelAndView addExpressOrder(ModelAndView mv,@ModelAttribute ExpressOrder expressOrder){
        if (iExpressOrderService.addExpressOrderService(expressOrder)) {
            mv.setViewName("Express/expressorder/expressOrderList");
        } else {
            Map<String,String> map = new HashMap<String, String>();
            map.put("code","addExpressOrder");
            map.put("error","1");
            mv.addObject("code",map);
            mv.addObject("updateExpressOrder",expressOrder);
            mv.setViewName("Express/expressorder/addExpressOrder");
        }
        return mv;
    }

    /**
     * 服务器端添加订单
     */
    @RequestMapping("updateExpressOrder")
    public ModelAndView updateExpressOrder(ModelAndView mv,@ModelAttribute ExpressOrder expressOrder){
        if (iExpressOrderService.updateExpressOrderService(expressOrder)) {
            mv.setViewName("Express/expressorder/expressOrderList");
        } else {
            Map<String,String> map = new HashMap<String, String>();
            map.put("code","updateExpressOrder");
            map.put("error","2");
            mv.addObject("code",map);
            mv.addObject("updateExpressOrder",iExpressOrderService.obtainExpressOrderById(expressOrder.getOrderid()));
            mv.setViewName("Express/expressorder/addExpressOrder");
        }
        return mv;
    }


    /**
     * 生成订单
     * @param expressOrder
     * @return
     */
    @RequestMapping("addOrderByType")
    @ResponseBody
    public String addOrderByType(ExpressOrder expressOrder){
        String orderCode = OrderInfoUtil2_0.getOutTradeNo();
        String[] str = expressOrder.getBillids().split(",");
        Integer revuserid= iExpressBillService.obtainExpressBillById(Integer.parseInt(str[0])).getRevuserid();
        Integer senduserid= iExpressBillService.obtainExpressBillById(Integer.parseInt(str[0])).getSenduserid();
        expressOrder.setUid(revuserid ==null?senduserid==null?null:senduserid:revuserid);
        expressOrder.setOrdercode(orderCode);
        iExpressOrderService.addExpressOrderService(expressOrder);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("orderCode",orderCode);
        return JSON.toJSONString(map);
    }


    /**
     * 签约
     * @param expressOrder
     * @return
     */
    @RequestMapping("signOrder")
    @ResponseBody
    public String signOrder(ExpressOrder expressOrder) {
        System.out.println("expressOrder:"+expressOrder);
        System.out.println("ordercode:"+expressOrder.getOrdercode());
        try {
            ExpressOrder expressOrder1 = iExpressOrderService.obtainOrderByOrderCode(expressOrder.getOrdercode().trim());
            expressOrder1.setMoney(expressOrder.getMoney());
            System.out.println("order1:"+expressOrder1);
            expressOrder1.setPaydateString(expressOrder.getPaydateString());
//            expressOrder.setOrdercode(OrderInfoUtil2_0.getOutTradeNo());
            Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(expressOrder1, true);
            String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
            String sign = OrderInfoUtil2_0.getSign(params, Constant.RSA2_PRIVATE, true);
            String orderInfo = orderParam + "&" + sign;
            Map<String, String> map = new HashMap<String, String>();
            map.put("check", "true");
            map.put("orderInfo", orderInfo);
            map.put("orderCode",expressOrder.getOrdercode());
            iExpressOrderService.updateExpressOrderService(expressOrder1);
            return JSON.toJSONString(map);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            System.out.println(e.getMessage());
            Map<String, String> map = new HashMap<String, String>();
            map.put("check", "false");
            map.put("orderInfo", "");
            map.put("orderCode","");
            return JSON.toJSONString(map);
        }

    }

    /**
     * 异步测试
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("notifay_url")
    @ResponseBody
    public String notifay_url(HttpServletRequest request) throws UnsupportedEncodingException {
        return iExpressOrderService.notifay(request);
    }

    /**
     * 根据mid获取未支付订单
     * @param mid
     * @return
     */
    @RequestMapping("obtainOrderListByMid")
    @ResponseBody
    public String obtainOrderListByMid(Integer mid){
        return JSON.toJSONString(iExpressOrderService.obtainExpressOrderListByMid(mid));
    }

    /**
     * 根据ordercode获取支付结果
     * @param orderCode
     * @return
     */
    @RequestMapping("obtainResultByOrderCode")
    @ResponseBody
    public String obtainResultByOrderCode(String orderCode){
        ExpressOrder expressOrder = iExpressOrderService.obtainOrderByOrderCode(orderCode);
        if(expressOrder.getType()==2&&expressOrder.getResult()==1){
            try {
                ExpressBill expressBill= new ExpressBill();
                Integer currentStatus = iExpressBillService.findCurrentStatusByBillCode(expressBill.getBillcode());
                expressBill.setBillid(Integer.parseInt(expressOrder.getBillids()));
                expressBill.setStauts(200);
                if(iExpressBillService.updateExpressBill(expressBill)){
                    ExpressTracing expressTracing = new ExpressTracing();
                    expressTracing.setBillcode(expressBill.getBillcode());
                    expressTracing.setStatusbefore(currentStatus);
                    expressTracing.setStatusafter(expressBill.getStauts());
                    expressTracing.setUid(expressBill.getRevuserid());
                    expressTracing.setNotes("收件处理");
                    iExpressTracing.addExpressTracing(expressTracing);
                }
            }catch (Exception e){
                e.printStackTrace();
                    return "-99";
            }

        }
        return expressOrder.getResult().toString();
    }



}
