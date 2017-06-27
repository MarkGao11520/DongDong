package com.zrkj.ecp.dao.expresssms;

import java.util.List;

import com.zrkj.ecp.dto.expresssms.SmsTemplateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zrkj.ecp.domain.expresssms.SmsTemplate;
import com.zrkj.ecp.dto.expressbill.ExpressDto;

@Mapper
public interface SmsTemplateMapper {
    int deleteByPrimaryKey(Integer temid);

    int insert(SmsTemplate record);

    int insertSelective(SmsTemplate record);

    SmsTemplate selectByPrimaryKey(Integer temid);

    int updateByPrimaryKeySelective(SmsTemplate record);

    int updateByPrimaryKey(SmsTemplate record);
    
    /**
     * 列表
     * @param expressDto
     * @return
     */
    List<SmsTemplate> selectSmsTemPlateList(@Param("expressDto") ExpressDto expressDto, @Param("smsTemplateDto") SmsTemplateDto smsTemplateDto);

    /**
     * 总数
     * @param expressDto
     * @return
     */
    Integer selectSmsTemPlateCount(@Param("expressDto") ExpressDto expressDto,@Param("smsTemplateDto") SmsTemplateDto smsTemplateDto);

    /**
     * 最大id
     * @return
     */
    Integer selectMaxId();

    /**
     * 根据cid来获取
     */
    SmsTemplate selectByCid(Integer cid);
}