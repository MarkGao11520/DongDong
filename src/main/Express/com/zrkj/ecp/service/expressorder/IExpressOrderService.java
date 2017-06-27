package com.zrkj.ecp.service.expressorder;

import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.domain.expressorder.ExpressOrder;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/3/13.
 */
public interface IExpressOrderService {
    Boolean addExpressOrderService(ExpressOrder expressOrder);

    ExpressOrder obtainOrderByOrderCode(String orderCode);

    Boolean updateExpressOrderService(ExpressOrder expressOrder);

    String notifay(HttpServletRequest request) throws UnsupportedEncodingException;

    /**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssOrderList(ExpressDto expressDto);

    /**
     * 唯一查询
     * @param LogId
     * @return
     */
    public ExpressOrder obtainExpressOrderById(Integer LogId);

    /**
     * 根据mid获取订单
     */
    public List<ExpressOrder> obtainExpressOrderListByMid (Integer mid);
}
