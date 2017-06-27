package com.zrkj.ecp.controller;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.datadictionary.DataDictionary;
import com.zrkj.ecp.datadictionary.DataObject;
import com.zrkj.ecp.domain.Msg;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by gaowenfeng on 2017/2/6.
 */
@RestController
public class controller {
    @RequestMapping(value = "test/testinput")
    public String testinput(String content){
        System.out.println("start");
        System.out.println("content:"+content);
        DataObject  dataObject = new DataObject();
        dataObject.setContent(content);
        dataObject.setCreateTime(new Date());
        DataDictionary.DataMap.put(UUID.randomUUID().toString(),dataObject);
        System.out.println(JSON.toJSONString(DataDictionary.DataMap));
        return JSON.toJSONString(DataDictionary.DataMap);
    }

    @RequestMapping(value = "test/testget")
    public String testget(String id){
        return JSON.toJSONString(DataDictionary.DataMap.get(id));
    }


}
