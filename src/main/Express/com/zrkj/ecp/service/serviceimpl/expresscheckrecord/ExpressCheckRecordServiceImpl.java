package com.zrkj.ecp.service.serviceimpl.expresscheckrecord;

import com.zrkj.ecp.dao.expresscheckrecord.ExpressCheckRecordMapper;
import com.zrkj.ecp.domain.expresscheckrecord.ExpressCheckRecord;
import com.zrkj.ecp.dto.expresscheckrecord.ExpressCheckRecordDto;
import com.zrkj.ecp.service.expresscheckrecord.IExpressCheckRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
@Service
public class ExpressCheckRecordServiceImpl implements IExpressCheckRecord{
    @Resource
    ExpressCheckRecordMapper expressCheckRecordMapper;
    @Override
    public boolean addCheckRecord(ExpressCheckRecord expressCheckRecord) {
        if(expressCheckRecordMapper.isCheck(expressCheckRecord.getBillid())==null) {
            try {
                Integer maxid = expressCheckRecordMapper.selectMaxId();
                if (maxid != null) {
                    expressCheckRecord.setRecordid(maxid + 1);
                } else {
                    expressCheckRecord.setRecordid(1);
                }
                expressCheckRecord.setRecorddate(new Date());
                expressCheckRecordMapper.insertSelective(expressCheckRecord);
                return true;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    @Override
    public Map<String, Object> obtainCheckRecordList(ExpressCheckRecordDto expressCheckRecordDto) {
        if(expressCheckRecordDto.getIsin()==null){
            expressCheckRecordDto.setIsin(-1);
        }
        System.out.println("isin:"+expressCheckRecordDto.getIsin());
        expressCheckRecordDto.setPage((expressCheckRecordDto.getPage()-1)*expressCheckRecordDto.getRows());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows",expressCheckRecordMapper.selectCheckedBill(expressCheckRecordDto));
        map.put("total",expressCheckRecordMapper.selectCheckedBillCount(expressCheckRecordDto));
        return map;
    }
}
