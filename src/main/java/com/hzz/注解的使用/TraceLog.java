package com.hzz.注解的使用;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TraceLog {

    /**
     * 业务
     */
    String business() default "未知";

    /**
     * 模块
     */
    String module() default "前置";
}