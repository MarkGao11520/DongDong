package com.zrkj.ecp.datadictionary;

import com.alibaba.fastjson.JSON;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/2/19.
 */
@Service
public class ScheduledTaskService {
    @Scheduled(fixedRate = 60000)
    public void ResetDataDictionary(){
        for(Map.Entry<String,DataObject> entry:DataDictionary.DataMap.entrySet()){
            String key = entry.getKey().toString();
            DataObject value = entry.getValue();
            Date date = new Date();
            if((date.getTime()-value.getCreateTime().getTime())>=60000){
                DataDictionary.DataMap.remove(key);
            }
        }
    }
}
