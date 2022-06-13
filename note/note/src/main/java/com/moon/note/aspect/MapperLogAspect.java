package com.moon.note.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: Moon
 * @date: 2022/6/10
 */

@Aspect
@Component
class MapperLogAspect {

    @Around("@annotation(com.moon.note.annotation.SystemMapperLog)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
//        System.out.println("[Mapper] " + joinPoint.getSignature().getName() + "() 方法总耗时:" + (end - start) + " ms");
        return proceed;
    }

    @AfterReturning(pointcut = "@annotation(com.moon.note.annotation.SystemMapperLog)",returning = "ret")
    public void afterReturn(JoinPoint joinPoint, Object ret) {
//        System.out.println("[Mapper] "+joinPoint.getSignature().getName()+"()返回结果：" + ret);
    }
}
