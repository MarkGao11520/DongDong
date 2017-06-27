package com.zrkj.ecp.service.serviceimpl.expresssms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;
import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expresssms.SmsRecord;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresssms.ISmsRecordService;

@Service
public class SmsRecordServiceImpl implements ISmsRecordService{
	    @Resource
	    com.zrkj.ecp.dao.expresssms.SmsRecordMapper SmsRecordMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssBillList(ExpressDto expressDto, SmsTemplateDto smsTemplateDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",SmsRecordMapper.selectSmsRecordList(expressDto,smsTemplateDto));
	        map.put("total",SmsRecordMapper.selectSmsRecordCount(expressDto,smsTemplateDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param billId
	     * @return
	     */
	    @Override
	    public SmsRecord obtainSmsRecordById(Integer billId) {
	        return SmsRecordMapper.selectByPrimaryKey(billId);
	    }

	    /**
	     * 添加
	     * @param SmsRecord
	     * @return
	     */
	    @Override
	    public boolean addSmsRecord(SmsRecord SmsRecord) {
	        try {
				SmsRecord.setAdddate(new Date());
	            Integer maxid = SmsRecordMapper.selectMaxId();
	            if(maxid==null){
	                SmsRecord.setRecordid(1);
	            }else {
	                SmsRecord.setRecordid(maxid+1);
	            }
	            SmsRecord.setAdddate(new Date());
	            SmsRecordMapper.insertSelective(SmsRecord);
	            return true;
	        }catch (Exception e){
	        	e.printStackTrace();
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param SmsRecord
	     * @return
	     */
	    @Override
	    public boolean updateSmsRecord(SmsRecord SmsRecord) {
	        try {
				if(SmsRecordMapper.updateByPrimaryKeySelective(SmsRecord)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param billIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteSmsRecord(String billIdStr) {
	        return false;
	    }

}
