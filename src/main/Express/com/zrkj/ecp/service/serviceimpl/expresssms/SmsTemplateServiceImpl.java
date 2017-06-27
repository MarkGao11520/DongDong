package com.zrkj.ecp.service.serviceimpl.expresssms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;
import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expresssms.SmsTemplate;
import com.zrkj.ecp.dao.expresssms.SmsTemplateMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresssms.ISmsTemplate;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SmsTemplateServiceImpl implements ISmsTemplate{
	 @Resource
	    SmsTemplateMapper SmsTemplageMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainSmsTemplateList(ExpressDto expressDto, SmsTemplateDto smsTemplateDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",SmsTemplageMapper.selectSmsTemPlateList(expressDto,smsTemplateDto));
	        map.put("total",SmsTemplageMapper.selectSmsTemPlateCount(expressDto,smsTemplateDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param billId
	     * @return
	     */
	    @Override
	    public SmsTemplate obtainSmsTemplateById(Integer billId) {
	        return SmsTemplageMapper.selectByPrimaryKey(billId);
	    }

	    /**
	     * 添加
	     * @param SmsTemplage
	     * @return
	     */
	    @Override
		@Transactional()
	    public boolean addSmsTemplate(SmsTemplate SmsTemplage) {
				SmsTemplage.setAdddate(new Date());
	            Integer maxid = SmsTemplageMapper.selectMaxId();
	            if(maxid==null){
	                SmsTemplage.setTemid(1);
	            }else {
	                SmsTemplage.setTemid(maxid+1);
	            }
	            SmsTemplageMapper.insertSelective(SmsTemplage);
//				SmsTemplageMapper.insertSelective(SmsTemplage);
	            return true;
	    }

	    /**
	     * 修改
	     * @param SmsTemplage
	     * @return
	     */
	    @Override
	    public boolean updateSmsTemplate(SmsTemplate SmsTemplage) {
	        try {
				if(SmsTemplageMapper.updateByPrimaryKeySelective(SmsTemplage)>0){
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
	    public boolean deleteSmsTemplate(String billIdStr) {
	        return false;
	    }

	@Override
	public SmsTemplate obtainByCid(Integer cid) {
		return SmsTemplageMapper.selectByCid(cid);
	}

}
