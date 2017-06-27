package com.zrkj.ecp.service.expresslog;

import java.util.Map;

import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

public interface IExpressLogService {
	 /**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssLogList(ExpressDto expressDto);

    /**
     * 唯一查询
     * @param LogId
     * @return
     */
    public ExpressLog obtainExpressLogById(Integer LogId);

    /**
     * 增加
     * @param expressLog
     * @return
     */
    public boolean addExpressLog(ExpressLog expressLog);

    /**
     * 修改
     * @param expressLog
     * @return
     */
    public boolean updateExpressLog(ExpressLog expressLog);

    /**
     * 删除
     * @param LogIdStr
     * @return
     */
    public boolean deleteExpressLog(String LogIdStr);

}
