package com.zrkj.ecp.service.serviceimpl.app;

import com.zrkj.ecp.dao.app.BasAppMapper;
import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.domain.member.MemberProperty;
import com.zrkj.ecp.dto.app.AppDto;
import com.zrkj.ecp.dto.app.PMappDto;
import com.zrkj.ecp.service.app.BasAppService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

@Service("basAppService")
public class BasAppServiceImpl implements BasAppService {

	@Resource
	BasAppMapper basAppMapper;
	
	/**
	 * 添加应用
	 */
	@Override
	public void addApp(BasApp basApp) {
		// TODO Auto-generated method stub
		Integer maxid = basAppMapper.selectMaxAid();
		if(maxid==null){
			basApp.setAppid(1);
		}else{
		basApp.setAppid(maxid+1);
		}
		basApp.setLastupdated(new Date());
		basAppMapper.insertSelective(basApp);
	}

	/**
	 * 应用列表
	 */
	@Override
	public Map<String, Object> appList(AppDto appDto) {
		// TODO Auto-generated method stub
			String name = appDto.getAppFullName();
		 	if(appDto.getAppFullName()!=null){
	            appDto.setAppFullName("%"+name+"%");
	        }
	        appDto.setPage((appDto.getPage()-1)*appDto.getRows());
	        List<BasApp> AppList =basAppMapper.selectAppList(appDto);
	        int size = basAppMapper.selectAppCount(appDto);
	        Map<String,Object> map = new HashMap<String, Object>();
	        map.put("rows",AppList);
	        map.put("total",size);
	        return map;
	}
	
    /**
     * 修改
     */
	@Override
	public void updateApp(BasApp basApp) {
		// TODO Auto-generated method stub
		basApp.setLastupdated(new Date());
		basAppMapper.updateByPrimaryKeySelective(basApp);
	}

	/**
	 * 根据主键查找
	 */
	@Override
	public BasApp selectAppByAppId(Integer appid) {
		// TODO Auto-generated method stub
		return basAppMapper.selectByPrimaryKey(appid);
	}

	/**
	 * 获取pmdto记录
	 */
	@Override
	public List<PMappDto> selectPMappDtoList() {
		// TODO Auto-generated method stub
		return basAppMapper.selectPMappDtoList();
	}

	/**
	 * 删除
	 */
	@Override
	public String deleteApp(String str) {
		// TODO Auto-generated method stub
		String appid[] = str.split(",");
		String result;
		for (String string : appid) {
			if(basAppMapper.selectCountByMFK(Integer.parseInt(string))>0){
				result = "第"+string+"条记录删除失败，因为其正在被其他表关联";
				return result;
			}
		}
		for (String string : appid) {
			BasApp basApp = new BasApp();
			basApp.setAppid(Integer.parseInt(string));
			basApp.setIsdel(1);
			basAppMapper.updateByPrimaryKeySelective(basApp);
		}
		result = "删除成功";
		return result;
	}

	/**
	 * 根据uid获取applist
	 * @param uid
	 * @return
     */
	@Override
	public List<BasApp> obtainAppListByUid(Integer uid) {
		return null;
	}


	/**
	 * 上传apk
	 * @param request
	 * @param multipartFile
	 * @param mid
	 * @return
	 */
	@Override
	public Map<String,String> uploadAPK(HttpServletRequest request, MultipartFile multipartFile, Integer appid) {
		Map<String,String> map = new HashMap<String,String>();
		try {
			UUID uuid = UUID.randomUUID();
			String path = request.getServletContext().getRealPath(
					"/");
			String userPath = "/" + "apk"+"/"+appid.toString()+"/";
			String realPath = path + userPath ;
			File dir = new File(realPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			String apkname = appid.toString()+".apk";
			String apkurl = realPath+apkname;
			multipartFile.transferTo(new File(apkurl));
			BasApp basApp = new BasApp();
			map.put("isSuccess","true");
			map.put("url",userPath+apkname);
			return map;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		map.put("isSuccess","false");
		map.put("url","");
		return map;
	}

}
