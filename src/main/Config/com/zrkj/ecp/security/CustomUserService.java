package com.zrkj.ecp.security;

import com.zrkj.ecp.dao.user.BasUserMapper;
import com.zrkj.ecp.domain.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by gaowenfeng on 2017/2/5.
 */
public class CustomUserService implements UserDetailsService{
    @Autowired
    BasUserMapper basUserMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = basUserMapper.findByUserName(s);
        if (user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        user.setRoles(basUserMapper.findUserRoleByUserName(s));
        return user;
    }
}
