package com.zrkj.ecp.service.user;

import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.user.BasUser;
import com.zrkj.ecp.domain.user.BasUserProperty;
import com.zrkj.ecp.dto.log.LogDto;
import com.zrkj.ecp.dto.user.UserDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BasUserService {

	/**
	 * 查询用户列表
	 * @param userDto
	 * @return
	 */
    public Map<String,Object> UserList(UserDto userDto);
    
    /**
     * 登录验证
     * @return
     */
    public List<BasApp> loginCheck(HttpServletRequest request);

    /**
     * 手机端登录验证
     */
    public Map<String,Object> loginCheck(BasUser basUser,HttpServletRequest request);

    /**
     * 获取客户端ip
     * @param request
     * @return
     */
    public String getIp(HttpServletRequest request);
    
    /**
     * 添加新用户
     */
    public void addUser(BasUser basUser, BasUserProperty basUserProperty);
    
    /**
     * 根据uid查找user
     * @param uid
     * @return
     */
    public Map<String, Object> selectUserByUid(Integer uid);    
    /**
     * 修改用户
     * @param basUser
     * @param basUserProperty
     */
    public void updateUser(BasUser basUser, BasUserProperty basUserProperty);
    
    /**
     * 插入日志
     * @param basLog
     */
    public void insertlog(BasLog basLog);
    
    /**
     * 获取日志列表
     * @param logDto
     * @return
     */
    public Map<String, Object> selectLogList(LogDto logDto);

    /**
     * 修改密码
     * @param oldP
     * @param newP
     * @return
     */
    public boolean updatePassWord(String oldP,String newP);
     
}
