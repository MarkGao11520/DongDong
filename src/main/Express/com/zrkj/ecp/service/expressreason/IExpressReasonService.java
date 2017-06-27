package com.zrkj.ecp.service.expressreason;

import java.util.Map;

import com.zrkj.ecp.domain.expressreason.ExpressReason;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

public interface IExpressReasonService {
	/**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssReasonList(ExpressDto expressDto);

    /**
     * 唯一查询
     * @param ReasonId
     * @return
     */
    public ExpressReason obtainExpressReasonById(Integer ReasonId);

    /**
     * 增加
     * @param expressReason
     * @return
     */
    public boolean addExpressReason(ExpressReason expressReason);

    /**
     * 修改
     * @param expressReason
     * @return
     */
    public boolean updateExpressReason(ExpressReason expressReason);

    /**
     * 删除
     * @param ReasonIdStr
     * @return
     */
    public boolean deleteExpressReason(String ReasonIdStr);
}
