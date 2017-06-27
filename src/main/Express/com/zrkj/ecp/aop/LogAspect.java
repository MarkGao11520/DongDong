package com.zrkj.ecp.aop;

import com.alibaba.fastjson.JSON;
import com.zrkj.ecp.domain.expressbill.ExpressBill;
import com.zrkj.ecp.domain.expresslog.ExpressLog;
import com.zrkj.ecp.service.expresslog.IExpressLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by gaowenfeng on 2017/3/14.
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    IExpressLogService iExpressLogService;

    @Pointcut("@annotation(com.zrkj.ecp.aop.LogAction)")
    public void annotationPointCut(){}

    @After("annotationPointCut()")
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
            ExpressBill expressBill = (ExpressBill) objects[0];
            ExpressLog expressLog = new ExpressLog();

            expressLog.setLogcontent(expressBill.toString());
            expressLog.setBillid(expressBill.getBillid());
            expressLog.setUid(expressBill.getRevuserid()==null?expressBill.getSenduserid()==null?null:expressBill.getSenduserid():expressBill.getRevuserid());
            expressLog.setNotes("备注:"+expressBill.getNotes());
            iExpressLogService.addExpressLog(expressLog);
        }catch (Error e){
            System.out.println("logError:"+e.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("logException:"+e.getMessage());
        }
    }

}
