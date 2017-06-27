package com.zrkj.ecp.service.memberbc;

import com.zrkj.ecp.domain.memberbc.MemberBc;
import com.zrkj.ecp.dto.memberbc.MemberBcDto;

import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberBcService {
    /**
     * 获取列表
     * @param memberBcDto
     * @return
     */
    Map<String,Object> obtainMemberBcList(MemberBcDto memberBcDto);

    /**
     * 根据id获取信息
     * @param bcId
     * @return
     */
    MemberBc obtainMemberBcByBcId(Integer bcId);

    /**
     * 添加
     * @param memberBc
     * @return
     */
    boolean addMemberBc(MemberBc memberBc);

    /**
     * 修改
     * @param memberBc
     * @return
     */
    boolean updateMemberBc(MemberBc memberBc);

    /**
     * 删除
     * @param bcStr
     * @return
     */
    boolean deleteMemberBc(String bcStr);
}
