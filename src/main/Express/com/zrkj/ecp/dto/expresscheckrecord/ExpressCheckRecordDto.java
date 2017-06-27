package com.zrkj.ecp.dto.expresscheckrecord;

import com.zrkj.ecp.dto.expressbill.ExpressDto;

/**
 * Created by gaowenfeng on 2017/2/18.
 */
public class ExpressCheckRecordDto extends ExpressDto{
    private String date;

    private Integer isin;

    public Integer getIsin() {
        return isin;
    }

    public void setIsin(Integer isin) {
        this.isin = isin;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
