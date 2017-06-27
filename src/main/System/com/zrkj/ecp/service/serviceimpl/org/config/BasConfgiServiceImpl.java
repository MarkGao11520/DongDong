package com.zrkj.ecp.service.serviceimpl.org.config;

import com.zrkj.ecp.dao.org.config.BasConfigMapper;
import com.zrkj.ecp.domain.org.config.BasConfig;
import com.zrkj.ecp.dto.org.config.ConfigDto;
import com.zrkj.ecp.service.org.config.BasConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("basConfigService")
public class BasConfgiServiceImpl implements BasConfigService{
@Resource
BasConfigMapper basConfigMapper;
	/**
	 * 获取列表
	 */
	@Override
	public Map<String, Object> selectConfigList(ConfigDto configDto) {
		// TODO Auto-generated method stub
		String name = configDto.getConfigname();
		if(configDto.getConfigname()!=null){
            configDto.setConfigname("%"+name+"%");
        }
        configDto.setPage((configDto.getPage()-1)*configDto.getRows());
        List<BasConfig> configList = basConfigMapper.selectConfigList(configDto);
        int size = basConfigMapper.selectCount(configDto);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("rows",configList);
        map.put("total",size);
        return map;
	}
	
	/**
	 * 添加
	 */
	@Override
	public void addConfig(BasConfig basConfig) {
		// TODO Auto-generated method stub
		Integer maxid = basConfigMapper.selectMaxAid();
		if(maxid==null){
			basConfig.setCofid(1);
		}else{
			basConfig.setCofid(maxid+1);
		}
		basConfigMapper.insertSelective(basConfig);
		
	}
	
	/**
	 * 编辑
	 */
	@Override
	public void updateConfig(BasConfig basConfig) {
		// TODO Auto-generated method stub
		basConfigMapper.updateByPrimaryKeySelective(basConfig);
	}
	/**
	 * 根据主键查找
	 */
	@Override
	public BasConfig selectConfigByCofId(Integer cofif) {
		// TODO Auto-generated method stub
		return basConfigMapper.selectByPrimaryKey(cofif);
	}
	/**
	 * 删除
	 */
	@Override
	public String deleteConfig(String str) {
		// TODO Auto-generated method stub
		String configid[] = str.split(",");
		for (String string : configid) {
			BasConfig basConfig = new BasConfig();
			basConfig.setCofid(Integer.parseInt(string));
			basConfig.setIsdel(1);
			basConfigMapper.updateByPrimaryKeySelective(basConfig);
		}
		return "删除成功";
	}

}
