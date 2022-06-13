package com.moon.note.aspect;

import com.moon.note.annotation.SystemServiceLog;
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
public class ServiceLogAspect {

    /**
     * 所有被标注@SystemServiceLog的方法都会被此方法切面
     */
    @Around("@annotation(anno))")
    public Object around(ProceedingJoinPoint joinPoint, SystemServiceLog anno) throws Throwable {
        System.out.println(anno.toString());
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
//        System.out.println("[Service] "+joinPoint.getSignature().getName()+"总耗时:"+(end-start)+" ms");
        return proceed;
    }
}

