package com.zrkj.ecp.aop;

import java.lang.annotation.*;

/**
 * Created by gaowenfeng on 2017/3/14.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAction {
    String name();
}
