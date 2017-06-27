package com.zrkj.ecp.service.serviceimpl.expressorder;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.PayRuselt;
import com.zrkj.ecp.alipay.constant.Base64;
import com.zrkj.ecp.alipay.constant.Constant;
import com.zrkj.ecp.dao.expressorder.ExpressOrderMapper;
import com.zrkj.ecp.dao.user.BasUserMapper;
import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.domain.expressorder.ExpressOrder;
import com.zrkj.ecp.domain.user.BasUser;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressorder.IExpressOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gaowenfeng on 2017/3/13.
 */
@Service
public class ExpressOrderServiceImpl implements IExpressOrderService{
    @Autowired
    ExpressOrderMapper expressOrderMapper;
    @Autowired
    BasUserMapper basUserMapper;
    @Override
    public Boolean addExpressOrderService(ExpressOrder expressOrder) {
        Integer maxid = expressOrderMapper.selectMaxId();
        if(maxid == null){
            expressOrder.setOrderid(1);
        }else{
            expressOrder.setOrderid(maxid+1);
        }
        try {
            if(expressOrder.getUid()!=null) {
                expressOrder.setUsername(basUserMapper.selectByPrimaryKey(expressOrder.getUid()).getUname());
            }
            if(expressOrder.getPaydate()==null&&expressOrder.getPaydateString()!=null){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
                Date date=sdf.parse(expressOrder.getPaydateString());
                expressOrder.setPaydate(date);
            }
            expressOrder.setCreatedate(new Date());
            expressOrderMapper.insertSelective(expressOrder);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public ExpressOrder obtainOrderByOrderCode(String orderCode) {
        return expressOrderMapper.selectOrderByOrderCode(orderCode);
    }

    @Override
    public Boolean updateExpressOrderService(ExpressOrder expressOrder) {
        try {
            if(expressOrder.getPaydate()==null&&expressOrder.getPaydateString()!=null){
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
                Date date=sdf.parse(expressOrder.getPaydateString());
                expressOrder.setPaydate(date);
            }
            expressOrderMapper.updateByPrimaryKeySelective(expressOrder);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 列表
     * @param expressDto
     * @return
     */
    @Override
    public Map<String, Object> obtainExpredssOrderList(ExpressDto expressDto) {
        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows",expressOrderMapper.selectExpressOrderList(expressDto));
        map.put("total",expressOrderMapper.selectExpressOrderCount(expressDto));
        return map;
    }

    /**
     * 唯一查询
     * @param LogId
     * @return
     */
    @Override
    public ExpressOrder obtainExpressOrderById(Integer LogId) {
        return expressOrderMapper.selectByPrimaryKey(LogId);
    }

    /**
     * 根据mid获取未支付订单
     * @param mid
     * @return
     */
    @Override
    public List<ExpressOrder> obtainExpressOrderListByMid(Integer mid) {
        return expressOrderMapper.selectExpressOrderListByMid(mid);
    }

    @Override
    public String notifay(HttpServletRequest request) throws UnsupportedEncodingException {
        System.out.println("异步请求回调测试");
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            if (!name.equals("sign")) {
                params.put(name, valueStr);
            }else{
                String sign = Base64.decode(valueStr).toString();
                params.put(name,sign);
            }
        }
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
        String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
        String app_id = new String(request.getParameter("app_id").getBytes("ISO-8859-1"), "UTF-8");
        boolean signVerified = true;
//      boolean signVerified = AlipaySignature.rsaCheckV1(params, Constant.ALIPAY_PUBLIC_KEY, "UTF-8");
        ExpressOrder expressOrder =obtainOrderByOrderCode(out_trade_no);
        System.out.println("trade_status:"+trade_status);
        System.out.println("out_trade_no:"+out_trade_no);
        System.out.println("total_amount:"+total_amount);
        System.out.println("appid:"+app_id);
        System.out.println("Order:"+expressOrder);
        if (signVerified) {
            if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                if (expressOrder!=null) {
                    if (total_amount.trim().equals(expressOrder.getMoney().toString().trim())) {
                            if (app_id.equals(Constant.APPID)) {
                                expressOrder.setResult(PayRuselt.PAY_SUCCESS);
                            } else {
                                expressOrder.setResult(PayRuselt.APPID_ERROR);
                                System.out.println("数据库记录该笔交易appid错误");
                                return "faild";
                                //数据库记录该笔交易appid错误
                            }
                    } else {
                        System.out.println("数据库记录该笔交易订单金额错误");
                        expressOrder.setResult(PayRuselt.AMOUNT_ERROR);
                        return "faild";
                        //数据库记录该笔交易订单金额错误
                    }
                } else {
                    System.out.println("数据库记录该笔交易订单订单号错误");
                    expressOrder.setResult(PayRuselt.ORDERCODE_ERROR);
                    return "faild";
                    //数据库记录该笔交易订单订单号错误
                }
            }
            updateExpressOrderService(expressOrder);
            System.out.println("success");
            return "success";
        } else {
            System.out.println("trade_status:"+trade_status);
            return "faild";
        }
    }
}
