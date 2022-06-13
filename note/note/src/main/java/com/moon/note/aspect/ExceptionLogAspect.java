package com.moon.note.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author: Moon
 * @date: 2022/6/9
 */

@Aspect
@Component
public class ExceptionLogAspect {

    private static final String ex = "execution(public * com.moon.note.exception.*.*(..))";

    @Around(ex)
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("Exception:"+joinPoint.getSignature().getName());
        return joinPoint.proceed();
    }
}
