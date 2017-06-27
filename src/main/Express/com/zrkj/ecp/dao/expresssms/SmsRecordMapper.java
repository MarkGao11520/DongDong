package com.zrkj.ecp.dao.expresssms;

import java.util.List;

import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expresssms.SmsRecord;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface SmsRecordMapper {
    int deleteByPrimaryKey(Integer recordid);

    int insert(SmsRecord record);

    int insertSelective(SmsRecord record);

    SmsRecord selectByPrimaryKey(Integer recordid);

    int updateByPrimaryKeySelective(SmsRecord record);

    int updateByPrimaryKey(SmsRecord record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<SmsRecord> selectSmsRecordList(@Param("expressDto") ExpressDto expressDto, @Param("smsTemplateDto")SmsTemplateDto smsTemplateDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectSmsRecordCount(@Param("expressDto") ExpressDto expressDto,@Param("smsTemplateDto")SmsTemplateDto smsTemplateDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();
}