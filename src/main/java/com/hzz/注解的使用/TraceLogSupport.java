package com.hzz.注解的使用;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j
public class TraceLogSupport {

    @Pointcut("@annotation(TraceLog)")
    private void pointcut() {
    }

    @Before("pointcut()&&@annotation(traceLog)")
    public void before(JoinPoint joinPoint, TraceLog traceLog) {
        Object[] args = joinPoint.getArgs();
        log.error(generateLog(traceLog, JSON.toJSONString(args)));
    }

    private String generateLog(TraceLog traceLog, String args) {
        List<String> elements = ImmutableList.of(
                traceLog.business(),
                traceLog.module(),
                args
        );
        return String.join(";", elements);
    }

    public static void main(String[] args) {
        List<String> of = ImmutableList.of("1", "2");
        System.out.println(String.join(";",of));
    }
}