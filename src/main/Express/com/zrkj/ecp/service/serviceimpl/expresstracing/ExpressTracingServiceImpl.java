package com.zrkj.ecp.service.serviceimpl.expresstracing;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.zrkj.ecp.domain.user.SysUser;
import com.zrkj.ecp.dto.expresstracing.ExpressTracingDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expresstracing.ExpressTracing;
import com.zrkj.ecp.dao.expresstracing.ExpressTracingMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expresstracing.IExpressTracing;

@Service
public class ExpressTracingServiceImpl implements IExpressTracing{
	 @Resource
	    ExpressTracingMapper expressTracingMapper;
	    /**
	     * 列表
	     * @param expressDto
	     * @return
	     */
	    @Override
	    public Map<String, Object> obtainExpredssTracingList(ExpressDto expressDto, ExpressTracingDto expressTracingDto) {
	        expressDto.setPage((expressDto.getPage()-1)*expressDto.getRows());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put("rows",expressTracingMapper.selectExpressTracingList(expressDto,expressTracingDto));
	        map.put("total",expressTracingMapper.selectExpressTracingCount(expressDto,expressTracingDto));
	        return map;
	    }

	    /**
	     * 唯一查询
	     * @param TracingId
	     * @return
	     */
	    @Override
	    public ExpressTracing obtainExpressTracingById(Integer TracingId) {
	        return expressTracingMapper.selectByPrimaryKey(TracingId);
	    }

	    /**
	     * 添加
	     * @param expressTracing
	     * @return
	     */
	    @Override
	    public boolean addExpressTracing(ExpressTracing expressTracing) {
	        try {
	            Integer maxid = expressTracingMapper.selectMaxId();
	            if(maxid==null){
	                expressTracing.setTracid(1);
	            }else {
	                expressTracing.setTracid(maxid+1);
	            }
//				Object principal = SecurityContextHolder.getContext()
//						.getAuthentication().getPrincipal();
//				Integer uid;
//				SysUser sysUser = new SysUser();
//				if (principal instanceof UserDetails) {
//					sysUser = (SysUser) principal;
//				}
//				uid = Integer.parseInt(sysUser.getId().toString());
//				expressTracing.setUid(uid);
				expressTracing.setChangedate(new Date());
	            expressTracingMapper.insertSelective(expressTracing);
	            return true;
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 修改
	     * @param expressTracing
	     * @return
	     */
	    @Override
	    public boolean updateExpressTracing(ExpressTracing expressTracing) {
	        try {
				if(expressTracingMapper.updateByPrimaryKeySelective(expressTracing)>0){
					return true;
				}
	        }catch (Exception e){
	            System.out.println(e.getMessage());
	        }
	        return false;
	    }

	    /**
	     * 删除
	     * @param TracingIdStr
	     * @return
	     */
	    @Override
	    public boolean deleteExpressTracing(String TracingIdStr) {
	        return false;
	    }

}
