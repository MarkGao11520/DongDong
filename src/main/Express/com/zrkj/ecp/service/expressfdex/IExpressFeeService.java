package com.zrkj.ecp.service.expressfdex;

import java.util.Map;

import com.zrkj.ecp.domain.expressfdex.ExpressSendFee;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expressfdex.ExpressSendFeeDto;

public interface IExpressFeeService {
	 /**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssSendFeeList(ExpressDto expressDto, ExpressSendFeeDto expressSendFeeDto);

    /**
     * 唯一查询
     * @param SendFeeId
     * @return
     */
    public ExpressSendFee obtainExpressSendFeeById(Integer SendFeeId);

    /**
     * 增加
     * @param expressSendFee
     * @return
     */
    public boolean addExpressSendFee(ExpressSendFee expressSendFee);

    /**
     * 修改
     * @param expressSendFee
     * @return
     */
    public boolean updateExpressSendFee(ExpressSendFee expressSendFee);

    /**
     * 删除
     * @param SendFeeIdStr
     * @return
     */
    public boolean deleteExpressSendFee(String SendFeeIdStr);
}
