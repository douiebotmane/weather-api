package com.weather.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("@annotation(com.weather.logging.WeatherLog)")
    public void annotatedWithWeatherLog() {}

    @Before("annotatedWithWeatherLog()")
    public void logMethodEntry(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String method = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
        Object[] args = joinPoint.getArgs();
        log.info("➡️ Entering method: {} with arguments: {}", method, Arrays.toString(args));
    }

    @AfterReturning(pointcut = "annotatedWithWeatherLog()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String method = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
        log.info("✅ Method: {} returned: {}", method, result);
    }

    @AfterThrowing(pointcut = "annotatedWithWeatherLog()", throwing = "ex")
    public void logMethodError(JoinPoint joinPoint, Throwable ex) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String method = methodSignature.getDeclaringTypeName() + "." + methodSignature.getName();
        log.error("❌ Method: {} threw exception: {}", method, ex.getMessage(), ex);
    }
}
