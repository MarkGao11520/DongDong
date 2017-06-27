package com.zrkj.ecp.service.serviceimpl.expresslog;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.dao.expresslog.ExpressLogMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresslog.IExpressLogService;

@Service
public class ExpressLogServiceImpl implements IExpressLogService{
	
	 @Resource
	    ExpressLogMapper expressLogMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssLogList(ExpressDto expressDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",expressLogMapper.selectExpressLogList(expressDto));
	        map.put("total",expressLogMapper.selectExpressLogCount(expressDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param LogId
	     * @return
	     */
	    @Override
	    public ExpressLog obtainExpressLogById(Integer LogId) {
	        return expressLogMapper.selectByPrimaryKey(LogId);
	    }

	    /**
	     * 添加
	     * @param expressLog
	     * @return
	     */
	    @Override
	    public boolean addExpressLog(ExpressLog expressLog) {
	        try {
	            Integer maxid = expressLogMapper.selectMaxId();
	            if(maxid==null){
	                expressLog.setLogid(1);
	            }else {
	                expressLog.setLogid(maxid+1);
	            }
	            expressLog.setAdddate(new Date());
	            expressLogMapper.insertSelective(expressLog);
	            return true;
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param expressLog
	     * @return
	     */
	    @Override
	    public boolean updateExpressLog(ExpressLog expressLog) {
	        try {
				if(expressLogMapper.updateByPrimaryKeySelective(expressLog)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param LogIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteExpressLog(String LogIdStr) {
	        return false;
	    }

}
