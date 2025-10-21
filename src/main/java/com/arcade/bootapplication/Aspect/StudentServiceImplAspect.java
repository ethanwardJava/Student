package com.arcade.bootapplication.Aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class StudentServiceImplAspect {

    // Pointcut for all methods inside StudentServiceImpl
    @Pointcut("execution(* com.arcade.bootapplication.service.StudentServiceImpl.*(..))")
    public void studentServiceMethods() {}

    // Log before method execution
    @Before("studentServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("➡️ Entering method: {}", joinPoint.getSignature().toShortString());
    }

    // Log after method completes successfully
    @AfterReturning(pointcut = "studentServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("✅ Method {} executed successfully, return: {}",
                joinPoint.getSignature().toShortString(), result);
    }

    // Log if an exception is thrown
    @AfterThrowing(pointcut = "studentServiceMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("❌ Exception in {}: {}",
                joinPoint.getSignature().toShortString(), ex.getMessage(), ex);
    }

    // Log execution time
    @Around("studentServiceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;
            log.debug("⏱️ {} executed in {} ms", joinPoint.getSignature().toShortString(), duration);
            return result;
        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - start;
            log.error("💥 {} failed after {} ms: {}",
                    joinPoint.getSignature().toShortString(), duration, ex.getMessage());
            throw ex;
        }
    }
}
