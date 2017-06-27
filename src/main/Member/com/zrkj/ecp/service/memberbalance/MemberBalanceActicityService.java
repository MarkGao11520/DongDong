package com.zrkj.ecp.service.memberbalance;

import com.zrkj.ecp.domain.memberbalance.MemberBalanceActicityWithBLOBs;
import com.zrkj.ecp.dto.memberbalance.MemberBalancesActicityDto;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/17.
 */
public interface MemberBalanceActicityService {
    /**
     * 获取列表
     * @param memberBalancesActicityDto
     * @return
     */
    Map<String,Object> obtainBalcnceActicityList(MemberBalancesActicityDto memberBalancesActicityDto);

    /**
     * 根据id获取
     * @param acId
     * @return
     */
    MemberBalanceActicityWithBLOBs obtainBalcnceActicityById(Integer acId);

    /**
     * 添加
     * @param memberBalanceActicityWithBLOBs
     * @return
     */
    boolean addBalanceActicity(MemberBalanceActicityWithBLOBs memberBalanceActicityWithBLOBs);

    /**
     * 编辑
     * @param memberBalanceActicityWithBLOBs
     * @return
     */
    boolean updateBalanceActicity(MemberBalanceActicityWithBLOBs memberBalanceActicityWithBLOBs);

    /**
     * 删除
     * @param acIdStr
     * @return
     */
    boolean deleteBalanceActicity(String acIdStr, HttpServletRequest request);

}
