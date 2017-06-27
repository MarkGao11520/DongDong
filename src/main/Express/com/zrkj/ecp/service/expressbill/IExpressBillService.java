package com.zrkj.ecp.service.expressbill;

import com.zrkj.ecp.aop.LogAction;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.dto.expressbill.ExpressBillDto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/19.
 */
public interface IExpressBillService {
    /**
     * 列表
     * @param expressBillDto
     * @return
     */
    public Map<String,Object> obtainExpredssBillList(ExpressBillDto expressBillDto);

    /**
     * 唯一查询
     * @param billId
     * @return
     */
    public ExpressBill obtainExpressBillById(Integer billId);

    /**
     * 增加
     * @param expressBill
     * @return
     */
    public boolean addExpressBill(ExpressBill expressBill);

    public Map<String,String> addExpressBill(ExpressBill expressBill,String code);


    /**
     * 修改
     * @param expressBill
     * @return
     */
    public boolean updateExpressBill(ExpressBill expressBill);

    /**
     * 删除
     * @param billIdStr
     * @return
     */
    public boolean deleteExpressBill(String billIdStr);

    /**
     * 获取版本号
     * @return
     */
    public String findVersion();

    /**
     * 根据单号查找当前状态
     * @param billcode
     * @return
     */
    public Integer findCurrentStatusByBillCode(String billcode);

    /**
     * 根据单id查找当前状态
     * @param billId
     * @return
     */
    public Integer findCurrentStatusByBillId(Integer billId);

    /**
     * 检查订单是否存在
     * @param billcode
     * @return
     */
    public Boolean checkBillIsExists(String billcode);

    /**
     * 根据billcode获取billid
     */
    public String obtainBillCodeByBillId(String billCode);
}
