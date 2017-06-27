package com.zrkj.ecp.service.serviceimpl.expressfdex;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expressfdex.ExpressSendFeeDto;
import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expressfdex.ExpressSendFee;
import com.zrkj.ecp.dao.expressfdex.ExpressSendFeeMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressfdex.IExpressFeeService;

@Service
public class ExpressSendFeeServiceImpl implements IExpressFeeService{
	 @Resource
	    ExpressSendFeeMapper expressSendFeeMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssSendFeeList(ExpressDto expressDto, ExpressSendFeeDto expressSendFeeDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",expressSendFeeMapper.selectExpressSendFeeList(expressDto,expressSendFeeDto));
	        map.put("total",expressSendFeeMapper.selectExpressSendFeeCount(expressDto,expressSendFeeDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param SendFeeId
	     * @return
	     */
	    @Override
	    public ExpressSendFee obtainExpressSendFeeById(Integer SendFeeId) {
	        return expressSendFeeMapper.selectByPrimaryKey(SendFeeId);
	    }

	    /**
	     * 添加
	     * @param expressSendFee
	     * @return
	     */
	    @Override
	    public boolean addExpressSendFee(ExpressSendFee expressSendFee) {
	        try {
				expressSendFee.setAdddate(new Date());
	            Integer maxid = expressSendFeeMapper.selectMaxId();
	            if(maxid==null){
	                expressSendFee.setSendid(1);
	            }else {
	                expressSendFee.setSendid(maxid+1);
	            }
	            expressSendFeeMapper.insertSelective(expressSendFee);
	            return true;
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param expressSendFee
	     * @return
	     */
	    @Override
	    public boolean updateExpressSendFee(ExpressSendFee expressSendFee) {
	        try {
				if(expressSendFeeMapper.updateByPrimaryKeySelective(expressSendFee)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param SendFeeIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteExpressSendFee(String SendFeeIdStr) {
	        return false;
	    }

}
