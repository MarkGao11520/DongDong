package com.zrkj.ecp.aop;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.domain.expresssms.SmsRecord;
import com.zrkj.ecp.domain.member.Member;
import com.zrkj.ecp.domain.member.MemberProperty;
import com.zrkj.ecp.service.expresslog.IExpressLogService;
import com.zrkj.ecp.service.expresssms.ISmsRecordService;
import com.zrkj.ecp.service.member.MemberService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaowenfeng on 2017/3/28.
 */
@Aspect
@Component
public class SmsRecordAspect {
    @Autowired
    ISmsRecordService iSmsRecordService;
    @Autowired
    MemberService memberService;


    @Pointcut("@annotation(com.zrkj.ecp.aop.SmsRecordAction)")
    public void annotationSmsPointCut(){}

    @After("annotationSmsPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("开始aop注解功能");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("method:"+method.getName());
        LogAction logAction = method.getAnnotation(LogAction.class);
        //                System.out.println(logAction.name());
//         method.getParameters()
        Object[] objects= joinPoint.getArgs();
        System.out.println(JSON.toJSON(objects));
        try {
            String phone =  (String) objects[0];
            String content = (String) objects[1];
            Map<String,Object> map = memberService.obtainMemberByPhone(phone);
            Member member =(Member) map.get("member");
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setRevphone(phone);
            smsRecord.setContent(content);
            if(member!=null){
                MemberProperty memberProperty =(MemberProperty) map.get("pro");
                smsRecord.setRevname(memberProperty.getNikename()==null?null:memberProperty.getNikename());
                smsRecord.setRevid(member.getMid()==null?null:member.getMid());
                smsRecord.setCid(member.getCid()==null?null:member.getCid());
            }
            iSmsRecordService.addSmsRecord(smsRecord);
        }catch (Error e){
            System.out.println("error:"+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error:"+e.getMessage());
        }
    }

}
