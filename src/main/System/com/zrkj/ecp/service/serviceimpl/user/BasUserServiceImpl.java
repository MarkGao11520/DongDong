package com.zrkj.ecp.service.serviceimpl.user;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.dao.app.BasAppMapper;
import com.zrkj.ecp.dao.dept.BasDeptMapper;
import com.zrkj.ecp.dao.log.BasLogMapper;
import com.zrkj.ecp.dao.user.BasUserMapper;
import com.zrkj.ecp.dao.user.BasUserPropertyMapper;
import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.domain.log.BasLog;
import com.zrkj.ecp.domain.user.BasUser;
import com.zrkj.ecp.domain.user.BasUserProperty;
import com.zrkj.ecp.domain.user.SysUser;
import com.zrkj.ecp.dto.log.LogDto;
import com.zrkj.ecp.dto.user.UserDto;
import com.zrkj.ecp.service.org.BasCompanyService;
import com.zrkj.ecp.service.user.BasUserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service("basUserService")
public class BasUserServiceImpl implements BasUserService {
    @Resource
    BasUserMapper basUserMapper;
    @Resource
    BasUserPropertyMapper basUserPropertyMapper;
    @Resource
    BasDeptMapper basDeptMapper;
    @Resource
    BasLogMapper basLogMapper;
    @Resource
    BasAppMapper basAppMapper;
    @Resource
    BasCompanyService basCompanyService;

    /**
     * 查询用户列表
     */
    @Override
    public Map<String, Object> UserList(UserDto userDto) {
        // System.out.println(JSON.toJSONString(userDto));
        String name = userDto.getRealname();
        userDto.setPage((userDto.getPage() - 1) * userDto.getRows());
        if (userDto.getRealname() != null) {
            userDto.setRealname("%" + name + "%");
        }
        List<BasUserProperty> userlist = basUserPropertyMapper
                .selectUserList(userDto);
        int size = basUserPropertyMapper.selectUserCount(userDto);
        Map<String, Object> map = new HashMap<String, Object>();
        userDto.setRealname(name);
        map.put("rows", userlist);
        map.put("total", size);

        return map;
    }

    /**
     * 登录验证
     */
    @Override
    public List<BasApp> loginCheck(HttpServletRequest request) {
        // TODO Auto-generated method stub
        try {
            BasUser basUser = new BasUser();
            Object principal = SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            SysUser sysUser = new SysUser();
            if (principal instanceof UserDetails) {
                sysUser = (SysUser) principal;
                Iterator it = sysUser.getAuthorities().iterator();
                String authority = "";
                while (it.hasNext()) {
                    authority = ((GrantedAuthority) it.next()).getAuthority();
                }
            }


            String ip = getIp(request);
            basUser.setLoginip(ip);
            basUser.setUid(Integer.parseInt(sysUser.getId().toString()));
            basUser.setUname(sysUser.getUsername());
            Date date = new Date();
            basUser.setLastlogintime(date);
            basUser.setPassword(null);


            HttpSession session = request.getSession();
//            session.setAttribute("loginUname", sysUser.getUsername());
//            session.setAttribute("loginUid", sysUser.getId().toString());


            BasLog basLog = new BasLog();
            basLog.setUid(Integer.parseInt(sysUser.getId().toString()));
            basLog.setIp(ip);
            session.setAttribute("log", basLog);

            basUserMapper.updateByPrimaryKeySelective(basUser);
            return basAppMapper.selectAppListByUid(basUser.getUid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String,Object> loginCheck(BasUser basUser,HttpServletRequest request) {
        String uid = basUserMapper.loginCheck(basUser);
        Map<String,Object> map = new HashMap<String, Object>();
        try {
            BasUser basUser1 = basUserMapper.selectByPrimaryKey(Integer.parseInt(uid));
            if(uid!=null){
                basUser.setLastlogintime(new Date());
                basUser.setLoginip(getIp(request));
                basUser.setUid(Integer.parseInt(uid));
                basUserMapper.updateByPrimaryKeySelective(basUser);
                map.put("loginCheck",1);
                map.put("user",basUserMapper.selectByPrimaryKey(Integer.parseInt(uid)));
                map.put("userPro",basUserPropertyMapper.selectByPrimaryKey(Integer.parseInt(uid)));
                map.put("orgName",basCompanyService.selectCompanyByCid(basUser1.getCid()).getName());
                return map;
            }else {
                map.put("loginCheck", -1);
                return map;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            map.put("loginCheck", -1);
            return map;
        }

    }

    /**
     * 获取客户端ip
     */
    @Override
    public String getIp(HttpServletRequest request) {
        // TODO Auto-generated method stub
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 添加新用户
     */
    @Override
    public void addUser(BasUser basUser, BasUserProperty basUserProperty) {
        // TODO Auto-generated method stub
        basUser.setRegdate(new Date());
        Integer maxid = basUserMapper.selectMaxid();
        if (maxid == null) {
            basUser.setUid(1);
            basUserProperty.setUid(1);
        } else {
            basUser.setUid(maxid + 1);
            basUserProperty.setUid(maxid + 1);
        }
        basUserProperty.setDeptname(basDeptMapper.selectDeptNameByDid(basUser
                .getDid()));
        System.out.println(JSON.toJSONString(basUser));
        basUserMapper.insertSelective(basUser);
        basUserPropertyMapper.insertSelective(basUserProperty);
    }

    /**
     * 根据uid查找user
     */
    @Override
    public Map<String, Object> selectUserByUid(Integer uid) {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user", basUserMapper.selectByPrimaryKey(uid));
        map.put("userp", basUserPropertyMapper.selectByPrimaryKey(uid));
        return map;
    }

    @Override
    public void updateUser(BasUser basUser, BasUserProperty basUserProperty) {
        // TODO Auto-generated method stub
        basUserMapper.updateByPrimaryKeySelective(basUser);
        basUserPropertyMapper.updateByPrimaryKeySelective(basUserProperty);
    }

    /**
     * 插入日志
     */
    @Override
    public void insertlog(BasLog basLog) {
        // TODO Auto-generated method stub
        Integer maxid = basLogMapper.selectMaxAid();
        if (maxid == null) {
            basLog.setLid(1);
            ;
        } else {
            basLog.setLid(maxid + 1);
            ;
        }
        basLogMapper.insertSelective(basLog);
    }

    /**
     * 获取日志列表
     */
    @Override
    public Map<String, Object> selectLogList(LogDto logDto) {
        // TODO Auto-generated method stub


        logDto.setPage(logDto.getPage() * logDto.getRows());
//		if(logDto.getDateString()!=null){
//			StringToDateConverter stringToDateConverter = new StringToDateConverter("yyyy-dd-MM");
//			logDto.setDate(stringToDateConverter.convert(logDto.getDateString()));
//		}
        //	System.out.println("logdto:"+JSON.toJSONString(logDto)+"date:"+logDto.getDate().toString());
        List<BasLog> list = basLogMapper.selectLogList(logDto);
        int size = basLogMapper.selectLogCount(logDto);
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("total", size);
        map.put("rows", list);

        return map;
    }

    /**
     * 修改密码
     * @param oldP
     * @param newP
     * @return
     */
    @Override
    public boolean updatePassWord(String oldP, String newP) {
        try {
            Object principal = SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();

            if (principal instanceof UserDetails) {
                String uname = ((SysUser) ((UserDetails) principal)).getUsername();
                if(basUserMapper.updatePassword(uname,oldP,newP)>0){
                    return true;
                }
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
