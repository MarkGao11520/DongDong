package com.zrkj.ecp.service.expressreason;

import java.util.Map;

import com.zrkj.ecp.domain.expressreason.ExpressAbnormal;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

public interface IExpressAbnormalService {
	/**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssAbnormalList(ExpressDto expressDto);

    /**
     * 唯一查询
     * @param AbnormalId
     * @return
     */
    public ExpressAbnormal obtainExpressAbnormalById(Integer AbnormalId);

    /**
     * 增加
     * @param expressAbnormal
     * @return
     */
    public boolean addExpressAbnormal(ExpressAbnormal expressAbnormal,Integer stauts);

    /**
     * 修改
     * @param expressAbnormal
     * @return
     */
    public boolean updateExpressAbnormal(ExpressAbnormal expressAbnormal);

    /**
     * 删除
     * @param AbnormalIdStr
     * @return
     */
    public boolean deleteExpressAbnormal(String AbnormalIdStr);

}
