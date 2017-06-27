package com.zrkj.ecp.service.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalanceLog;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberBalanceLogService {
    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    Map<String,Object> obtainBalanceLogList(MemberIntergralDto memberIntergralDto);

    /**
     * 添加
     * @param balanceLog
     * @return
     */
    boolean addBalanceLog(MemberBalanceLog balanceLog, HttpServletRequest request);

    /**
     * 根据id获取
     * @param logId
     * @return
     */
    MemberBalanceLog obtianBalanceLogById(String logId);
}
