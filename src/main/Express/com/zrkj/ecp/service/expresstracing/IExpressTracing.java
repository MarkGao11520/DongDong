package com.zrkj.ecp.service.expresstracing;

import java.util.Map;

import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expresstracing.ExpressTracingDto;

public interface IExpressTracing {
	/**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssTracingList(ExpressDto expressDto, ExpressTracingDto expressTracingDto);

    /**
     * 唯一查询
     * @param TracingId
     * @return
     */
    public ExpressTracing obtainExpressTracingById(Integer TracingId);

    /**
     * 增加
     * @param expressTracing
     * @return
     */
    public boolean addExpressTracing(ExpressTracing expressTracing);

    /**
     * 修改
     * @param expressTracing
     * @return
     */
    public boolean updateExpressTracing(ExpressTracing expressTracing);

    /**
     * 删除
     * @param TracingIdStr
     * @return
     */
    public boolean deleteExpressTracing(String TracingIdStr);
}
