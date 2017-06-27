package com.zrkj.ecp.service.serviceimpl.expressreason;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zrkj.ecp.Utils.BillStatus;
import com.zrkj.ecp.dao.expressbill.ExpressBillMapper;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.user.SysUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expressreason.ExpressAbnormal;
import com.zrkj.ecp.dao.expressreason.ExpressAbnormalMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressreason.IExpressAbnormalService;

@Service
public class ExpressAbnormalServiceImpl implements IExpressAbnormalService{
	 @Resource
	    ExpressAbnormalMapper expressAbnormalMapper;
	@Resource
	ExpressBillMapper expressBillMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssAbnormalList(ExpressDto expressDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",expressAbnormalMapper.selectExpressAbnormalList(expressDto));
	        map.put("total",expressAbnormalMapper.selectExpressAbnormalCount(expressDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param AbnormalId
	     * @return
	     */
	    @Override
	    public ExpressAbnormal obtainExpressAbnormalById(Integer AbnormalId) {
	        return expressAbnormalMapper.selectByPrimaryKey(AbnormalId);
	    }

	    /**
	     * 添加
	     * @param expressAbnormal
	     * @return
	     */
	    @Override
	    public boolean addExpressAbnormal(ExpressAbnormal expressAbnormal,Integer stauts) {
	        try {
				ExpressBill expressBill = new ExpressBill();
				expressBill.setBillid(expressAbnormal.getBillid());
				expressBill.setStauts(stauts);
				expressBillMapper.updateByPrimaryKeySelective(expressBill);
	            expressAbnormalMapper.insertSelective(expressAbnormal);
	            return true;
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param expressAbnormal
	     * @return
	     */
	    @Override
	    public boolean updateExpressAbnormal(ExpressAbnormal expressAbnormal) {
	        try {
				if(expressAbnormalMapper.updateByPrimaryKeySelective(expressAbnormal)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param AbnormalIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteExpressAbnormal(String AbnormalIdStr) {
	        return false;
	    }

}
