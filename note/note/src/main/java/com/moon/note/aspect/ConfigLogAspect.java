package com.moon.note.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author: Moon
 * @date: 2022/6/9
 */

@Aspect
@Component
@Slf4j
public class ConfigLogAspect {
    /**
     * 切面表达式 可以扫描哪些包下哪些类哪些方法，也可以按注解扫描
     */
    private static final String ex = "execution(public * com.moon.note.config.*.*(..))";

    @Around(ex)
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
//        System.out.println("config:"+ signature.getName());
        Object proceed = proceedingJoinPoint.proceed();
        return proceed;
    }

    @AfterThrowing(value = ex, throwing = "e")
    public Object throwing(JoinPoint joinPoint, Throwable e) {
//        System.out.println("config:"+joinPoint.getSignature().getName());
        e.printStackTrace();
        return null;
    }
}
