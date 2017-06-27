package com.zrkj.ecp.service.expressappoint;

import com.zrkj.ecp.dto.user.ExpressUserDto;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
public interface IExpressAppoinService {
    List<Map<String,String>> obtainUserListByPposid();
}
