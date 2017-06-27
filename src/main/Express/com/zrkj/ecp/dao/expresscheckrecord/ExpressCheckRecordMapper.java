package com.zrkj.ecp.dao.expresscheckrecord;

import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.expresscheckrecord.ExpressCheckRecord;
import com.zrkj.ecp.dto.expresscheckrecord.ExpressCheckRecordDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExpressCheckRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(ExpressCheckRecord record);

    int insertSelective(ExpressCheckRecord record);

    ExpressCheckRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(ExpressCheckRecord record);

    int updateByPrimaryKey(ExpressCheckRecord record);

    List<ExpressCheckRecord> selectExpressCheckRecordList(@Param("expressCheckRecordDto")ExpressCheckRecordDto expressCheckRecordDto);

    Integer selectExpressCheckRecordCount(@Param("expressCheckRecordDto")ExpressCheckRecordDto expressCheckRecordDto);

    Integer selectMaxId();

    Integer isCheck(Integer billid);

    /**
     * 查看盘点记录
     * @return
     */
    List<ExpressBill> selectCheckedBill(@Param("expressCheckRecordDto") ExpressCheckRecordDto expressCheckRecordDto);

    /**
     * 查看盘点记录总数
     */
    int selectCheckedBillCount(@Param("expressCheckRecordDto") ExpressCheckRecordDto expressCheckRecordDto);
}