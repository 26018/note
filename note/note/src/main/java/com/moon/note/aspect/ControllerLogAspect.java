package com.moon.note.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author MoonLight
 */

@Aspect
@Component
public class ControllerLogAspect {
    /**
     * 所有controller目录下的类，方法都会被此方法切面
     */
    private static final String ex = "execution(public * com.moon.note.controller.*.*(..))";

    @AfterReturning(value = ex, returning = "ret")
    public void doAfterReturning(JoinPoint joinPoint, Object ret) {
//        System.out.println(joinPoint.getSignature().getName()+"() 返回结果:" + ret);
    }

    @AfterThrowing(value = ex,throwing = "e")
    public void deAfterThrowing(JoinPoint joinPoint,Throwable e){
//        System.out.println("deAfterThrowing");
//        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = requestAttributes.getRequest();
//        request.get
    }

    @Around(ex)
    public Object deAround(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();

        Object proceed = joinPoint.proceed();

        long end = System.currentTimeMillis();
//        System.out.println(signature.getName()+"()方法执行总耗时：" + (end - start)+" ms");
        return proceed;
    }

}
