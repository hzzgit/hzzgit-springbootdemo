package com.hzz.注解的使用;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 这种切面是可以定义多个，这样就能快速的打印日志了
 */
@Aspect
@Component
@Slf4j
public class TraceLogtest2Support {

    @Pointcut("@annotation(TraceLog)")
    private void pointcut() {
    }

    @Before("pointcut()&&@annotation(traceLog)")
    public void before(JoinPoint joinPoint, TraceLog traceLog) {
        Object[] args = joinPoint.getArgs();
        log.error(generateLog(traceLog, JSON.toJSONString(args))+"这个是第二个切面");
    }

    @Around("@annotation(trackTime)")
    public Object around(ProceedingJoinPoint joinPoint, TraceLog trackTime) throws Throwable {
        Object result = null;
        long startTime = System.currentTimeMillis();
        result = joinPoint.proceed();
        long timeTaken = System.currentTimeMillis() - startTime;
        System.out.println(" -------------> Time Taken by " + joinPoint + " with param[" + trackTime.business() + "] is " + timeTaken);
        return result;
    }

    private String generateLog(TraceLog traceLog, String args) {
        List<String> elements = ImmutableList.of(
                traceLog.business(),
                traceLog.module(),
                args
        );
        return String.join(";", elements);
    }
}