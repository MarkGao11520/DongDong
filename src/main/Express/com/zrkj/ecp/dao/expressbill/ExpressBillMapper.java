package com.zrkj.ecp.dao.expressbill;

import com.zrkj.ecp.domain.app.BasApp;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.dto.expressbill.ExpressBillDto;
import com.zrkj.ecp.dto.expressbill.ExpressDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface

ExpressBillMapper {
    int deleteByPrimaryKey(Integer billid);

    int insert(ExpressBill record);

    int insertSelective(ExpressBill record);

    ExpressBill selectByPrimaryKey(Integer billid);

    int updateByPrimaryKeySelective(ExpressBill record);

    int updateByPrimaryKey(ExpressBill record);

    /**
     * 列表
     * @param expressBillDto
     * @return
     */
    List<ExpressBill> selectExpressBillList(@Param("expressBillDto") ExpressBillDto expressBillDto);

    /**
     * 总数
     * @param expressBillDto
     * @return
     */
    Integer selectExpressBillCount(@Param("expressBillDto") ExpressBillDto expressBillDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();

    /**
     * 获取版本号
     * @return
     */
    BasApp findVersion();

    /**
     * 根据单号查找当前状态
     * @param billcode
     * @return
     */
    Integer selectStatusByBillCode(@Param("billcode") String billcode);

    /**
     * 根据单id查找当前状态
     */
    Integer selectStatusByBillId(Integer billid);

    /**
     * 根据快递单号查找id
     * @param billcode
     * @return
     */
    Integer selectBillIdByBillCode(String billcode);


}