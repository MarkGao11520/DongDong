package com.zrkj.ecp.service.membersign;

import com.zrkj.ecp.domain.membersign.MemberSginRecord;
import com.zrkj.ecp.dto.memberintegral.MemberIntergralDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberSignRecordService {
    /**
     * 获取列表
     * @param memberIntergralDto
     * @return
     */
    public Map<String,Object> obtainMemberSignRecordList(MemberIntergralDto memberIntergralDto);


    /**
     * 添加
     * @param memberSginRecord
     * @return
     */
    public boolean addMemberSignRecord(MemberSginRecord memberSginRecord, HttpServletRequest request);

    /**
     * 根据id获取
     * @param reId
     * @return
     */
    public MemberSginRecord obtainRecordById(Integer reId);

}
