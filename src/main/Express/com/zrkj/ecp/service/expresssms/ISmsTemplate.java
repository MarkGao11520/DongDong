package com.zrkj.ecp.service.expresssms;

import java.util.Map;

import com.zrkj.ecp.domain.expresssms.SmsTemplate;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;

public interface ISmsTemplate {
	/**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainSmsTemplateList(ExpressDto expressDto, SmsTemplateDto smsTemplateDto);

    /**
     * 唯一查询
     * @param billId
     * @return
     */
    public SmsTemplate obtainSmsTemplateById(Integer billId);

    /**
     * 增加
     * @param SmsTemplate
     * @return
     */
    public boolean addSmsTemplate(SmsTemplate SmsTemplate);

    /**
     * 修改
     * @param SmsTemplate
     * @return
     */
    public boolean updateSmsTemplate(SmsTemplate SmsTemplate);

    /**
     * 删除
     * @param billIdStr
     * @return
     */
    public boolean deleteSmsTemplate(String billIdStr);

    /**
     * 根据cid获取
     */
    public SmsTemplate obtainByCid(Integer cid);
}
