package com.zrkj.ecp.service.serviceimpl.expressappoint;

import com.zrkj.ecp.dao.user.BasUserMapper;
import com.zrkj.ecp.domain.user.SysUser;
import com.zrkj.ecp.dto.user.ExpressUserDto;
import com.zrkj.ecp.service.expressappoint.IExpressAppoinService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
@Service
public class ExpressAppointServiceImpl implements IExpressAppoinService{
    @Resource
    BasUserMapper basUserMapper;

    @Override
    public List<Map<String,String>> obtainUserListByPposid() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        Integer posid;
        SysUser sysUser = new SysUser();
        if (principal instanceof UserDetails) {
            sysUser = (SysUser) principal;
        }
        posid = Integer.parseInt(sysUser.getRoles().get(0).getName());
        List<Map<String,String>> jsonlist = new ArrayList<Map<String,String>>();
        for (ExpressUserDto expressUserDto : basUserMapper.selectExpressUserDtoByPposid(posid)) {
            Map<String,String> map = new HashMap<String, String>();
            map.put("id", expressUserDto.getUid().toString());
            map.put("text",expressUserDto.getRealname());
            map.put("state", "");
            jsonlist.add(map);
        }
        return jsonlist;
    }
}
