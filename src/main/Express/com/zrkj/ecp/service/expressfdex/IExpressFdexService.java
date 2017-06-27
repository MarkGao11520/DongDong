package com.zrkj.ecp.service.expressfdex;

import com.zrkj.ecp.domain.expressfdex.ExpressFdex;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.dto.expressfdex.ExpressFdexDto;

import java.util.List;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/19.
 */
public interface IExpressFdexService {
    /**
     * 列表
     * @param expressDto
     * @return
     */
    public Map<String,Object> obtainExpredssFdexList(ExpressDto expressDto);

    /**
     * 唯一查询
     * @param FdexId
     * @return
     */
    public ExpressFdex obtainExpressFdexById(Integer FdexId);

    /**
     * 增加
     * @param expressFdex
     * @return
     */
    public boolean addExpressFdex(ExpressFdex expressFdex);

    /**
     * 修改
     * @param expressFdex
     * @return
     */
    public boolean updateExpressFdex(ExpressFdex expressFdex);

    /**
     * 删除
     * @param FdexIdStr
     * @return
     */
    public boolean deleteExpressFdex(String FdexIdStr);

    /**
     * 获取快递公司列表(名称,id)
     * @return
     */
    public List<ExpressFdexDto> obtainExpressFdexDtoList();
}
