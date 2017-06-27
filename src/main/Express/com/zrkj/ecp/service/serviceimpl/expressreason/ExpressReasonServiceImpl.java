package com.zrkj.ecp.service.serviceimpl.expressreason;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expressreason.ExpressReason;
import com.zrkj.ecp.dao.expressreason.ExpressReasonMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressreason.IExpressReasonService;

@Service
public class ExpressReasonServiceImpl implements IExpressReasonService{
	 @Resource
	    ExpressReasonMapper expressReasonMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssReasonList(ExpressDto expressDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",expressReasonMapper.selectExpressReasonList(expressDto));
	        map.put("total",expressReasonMapper.selectExpressReasonCount(expressDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param ReasonId
	     * @return
	     */
	    @Override
	    public ExpressReason obtainExpressReasonById(Integer ReasonId) {
	        return expressReasonMapper.selectByPrimaryKey(ReasonId);
	    }

	    /**
	     * 添加
	     * @param expressReason
	     * @return
	     */
	    @Override
	    public boolean addExpressReason(ExpressReason expressReason) {
	        try {
				expressReason.setAdddate(new Date());
	            Integer maxid = expressReasonMapper.selectMaxId();
	            if(maxid==null){
	                expressReason.setReasonid(1);
	            }else {
	                expressReason.setReasonid(maxid+1);
	            }
	            expressReasonMapper.insertSelective(expressReason);
	            return true;
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param expressReason
	     * @return
	     */
	    @Override
	    public boolean updateExpressReason(ExpressReason expressReason) {
	        try {
				if(expressReasonMapper.updateByPrimaryKeySelective(expressReason)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param ReasonIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteExpressReason(String ReasonIdStr) {
	        return false;
	    }

}
