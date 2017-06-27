package com.zrkj.ecp.service.expresscheckrecord;

import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.expresscheckrecord.ExpressCheckRecord;
import com.zrkj.ecp.dto.expresscheckrecord.ExpressCheckRecordDto;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
public interface IExpressCheckRecord {
    public boolean addCheckRecord(ExpressCheckRecord expressCheckRecord);

    public Map<String,Object> obtainCheckRecordList(ExpressCheckRecordDto expressCheckRecordDto);
}
