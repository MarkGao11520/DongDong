package com.zrkj.ecp.service.serviceimpl.expressfdex;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.zrkj.ecp.dto.expressfdex.ExpressFdexDto;
import org.springframework.stereotype.Service;

import com.zrkj.ecp.domain.expressfdex.ExpressFdex;
import com.zrkj.ecp.dao.expressfdex.ExpressFdexMapper;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressfdex.IExpressFdexService;

@Service
public class ExpressFdexServiceImpl implements IExpressFdexService {

    @Resource
    ExpressFdexMapper expressFdexMapper;

    /**
     * 列表
     *
     * @param expressDto
     * @return
     */
    @Override
    public Map<String, Object> obtainExpredssFdexList(ExpressDto expressDto) {
        expressDto.setPage((expressDto.getPage() - 1) * expressDto.getRows());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", expressFdexMapper.selectExpressFdexList(expressDto));
        map.put("total", expressFdexMapper.selectExpressFdexCount(expressDto));
        return map;
    }

    /**
     * 唯一查询
     *
     * @param FdexId
     * @return
     */
    @Override
    public ExpressFdex obtainExpressFdexById(Integer FdexId) {
        return expressFdexMapper.selectByPrimaryKey(FdexId);
    }

    /**
     * 添加
     *
     * @param expressFdex
     * @return
     */
    @Override
    public boolean addExpressFdex(ExpressFdex expressFdex) {
        try {
            expressFdex.setAdddate(new Date());
            Integer maxid = expressFdexMapper.selectMaxId();
            if (maxid == null) {
                expressFdex.setFdid(1);
            } else {
                expressFdex.setFdid(maxid + 1);
            }
            expressFdexMapper.insertSelective(expressFdex);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 修改
     *
     * @param expressFdex
     * @return
     */
    @Override
    public boolean updateExpressFdex(ExpressFdex expressFdex) {
        try {
            if (expressFdexMapper.updateByPrimaryKeySelective(expressFdex) > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     *
     * @param FdexIdStr
     * @return
     */
    @Override
    public boolean deleteExpressFdex(String FdexIdStr) {
        return false;
    }

    /**
     * 获取快递公司列表(名称,id)
     *
     * @return
     */
    @Override
    public List<ExpressFdexDto> obtainExpressFdexDtoList() {
        return expressFdexMapper.selectExpressFdexDtoList();
    }

}
