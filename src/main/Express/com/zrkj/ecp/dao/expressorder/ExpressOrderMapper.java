package com.zrkj.ecp.dao.expressorder;


import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.domain.expressorder.ExpressOrder;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpressOrderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(ExpressOrder record);

    int insertSelective(ExpressOrder record);

    ExpressOrder selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(ExpressOrder record);

    int updateByPrimaryKey(ExpressOrder record);

    /**查找最大id**/
    Integer selectMaxId();

    /**根据订单号查找订单详情**/
    ExpressOrder selectOrderByOrderCode(@Param("orderCode") String orderCode);


    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<ExpressOrder> selectExpressOrderList(@Param("expressDto") ExpressDto expressDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectExpressOrderCount(@Param("expressDto") ExpressDto expressDto);

    /**
     * 根据mid获取未支付订单
     */
    List<ExpressOrder> selectExpressOrderListByMid(@Param("mid")Integer mid);
}