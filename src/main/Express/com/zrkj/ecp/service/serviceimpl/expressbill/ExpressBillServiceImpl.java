package com.zrkj.ecp.service.serviceimpl.expressbill;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.Utils.RETURNCODE;
import com.zrkj.ecp.aop.LogAction;
import com.zrkj.ecp.datadictionary.DataDictionary;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.dao.expressbill.ExpressBillMapper;
import com.zrkj.ecp.domain.user.SysUser;
import com.zrkj.ecp.dto.expressbill.ExpressBillDto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import com.zrkj.ecp.service.expressbill.IExpressBillService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/1/19.
 */
@Service
public class ExpressBillServiceImpl implements IExpressBillService{
    @Resource
    ExpressBillMapper expressBillMapper;
    /**
     * 列表
     * @param expressBillDto
     * @return
     */
    @Override
    public Map<String, Object> obtainExpredssBillList(ExpressBillDto expressBillDto) {
        expressBillDto.setPage((expressBillDto.getPage()-1)*expressBillDto.getRows());
        Map<String, Object> map = new HashMap<String, Object>();
        if(expressBillDto.getBillCode()!=null) {
            expressBillDto.setBillCode("%" + expressBillDto.getBillCode() + "%");
        }
        if(expressBillDto.getRevname()!=null) {
            expressBillDto.setRevname("%" + expressBillDto.getRevname() + "%");
        }
        if(expressBillDto.getRevphone()!=null) {
            expressBillDto.setRevphone("%" + expressBillDto.getRevphone() + "%");
        }
        System.out.println(JSON.toJSONString(expressBillDto));
        map.put("rows",expressBillMapper.selectExpressBillList(expressBillDto));
        map.put("total",expressBillMapper.selectExpressBillCount(expressBillDto));
//        System.out.println("map:"+ JSON.toJSONString(map));
        return map;
    }

    /**
     * 唯一查询
     * @param billId
     * @return
     */
    @Override
    public ExpressBill obtainExpressBillById(Integer billId) {
        return expressBillMapper.selectByPrimaryKey(billId);
    }

    /**
     * 添加  lfzhang@isoftstone.com
     * @param expressBill
     * @return
     */
    @Override
    @LogAction(name = "添加一条快递订单")
    public boolean addExpressBill(ExpressBill expressBill) {
        try {
            Integer maxid = expressBillMapper.selectMaxId();
            if(maxid==null){
                expressBill.setBillid(1);
            }else {
                expressBill.setBillid(maxid+1);
            }
            if(expressBill.getCid()==null) {
            Object principal = SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            Integer cid;
            SysUser sysUser = new SysUser();
            if (principal instanceof UserDetails) {
                sysUser = (SysUser) principal;
            }
            cid = sysUser.getCid();
                expressBill.setCid(cid);
            }
            expressBill.setBilldate(new Date());
            expressBillMapper.insertSelective(expressBill);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Map<String, String> addExpressBill(ExpressBill expressBill, String code) {
        Map<String,String> map = new HashMap<String,String>();
        try {
            Integer maxid = expressBillMapper.selectMaxId();
            if(maxid==null){
                expressBill.setBillid(1);
            }else {
                expressBill.setBillid(maxid+1);
            }

            expressBill.setBilldate(new Date());
            expressBillMapper.insertSelective(expressBill);
            map.put("isSuccess","true");
            map.put("billid",expressBill.getBillid().toString());
            return map;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        map.put("isSuccess","false");
        map.put("billid","");
        return map;
    }

    /**
     * 修改
     * @param expressBill
     * @return
     */
    @Override
    @LogAction(name = "修改一条快递订单")
    public boolean updateExpressBill(ExpressBill expressBill) {
        try {
            if(expressBillMapper.updateByPrimaryKeySelective(expressBill)>0){
            return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * 删除
     * @param billIdStr
     * @return
     */
    @Override
    public boolean deleteExpressBill(String billIdStr) {
        return false;
    }

    /**
     * 获取版本号
     * @return
     */
    @Override
    public String findVersion() {
        return JSON.toJSONString(expressBillMapper.findVersion());
    }

    @Override
    public Integer findCurrentStatusByBillCode(String billcode) {
        return expressBillMapper.selectStatusByBillCode(billcode);
    }

    @Override
    public Integer findCurrentStatusByBillId(Integer billId) {
        return expressBillMapper.selectStatusByBillId(billId);
    }

    /**
     * 检查订单是否存在
     * @param billcode
     * @return
     */
    @Override
    public Boolean checkBillIsExists(String billcode) {
        Integer id = expressBillMapper.selectBillIdByBillCode(billcode);
        if(id!=null){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据billcode获取billid
     */
    @Override
    public String obtainBillCodeByBillId(String billCode) {
        Integer id = expressBillMapper.selectBillIdByBillCode(billCode);
        if(id!=null){
            return id.toString();
        }else{
            return RETURNCODE.BILLNOTEXISTS;
        }
    }
}
