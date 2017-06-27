package com.zrkj.ecp.service.expresssms;

import java.util.Map;

import com.zrkj.ecp.domain.expresssms.SmsRecord;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;

public interface ISmsRecordService {
	/**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssBillList(ExpressDto expressDto, SmsTemplateDto smsTemplateDto);

    /**
     * 唯一查询
     * @param billId
     * @return
     */
    public SmsRecord obtainSmsRecordById(Integer billId);

    /**
     * 增加
     * @param SmsRecord
     * @return
     */
    public boolean addSmsRecord(SmsRecord SmsRecord);

    /**
     * 修改
     * @param SmsRecord
     * @return
     */
    public boolean updateSmsRecord(SmsRecord SmsRecord);

    /**
     * 删除
     * @param billIdStr
     * @return
     */
    public boolean deleteSmsRecord(String billIdStr);
}
