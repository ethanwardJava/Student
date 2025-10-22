package com.arcade.bootapplication.Exceptions;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class StudentServicesImplExceptions {

    // Applies to all methods in the service layer
    @AfterThrowing(pointcut = "execution(* com.arcade.bootapplication.service.*.*(..))", throwing = "ex")
    public void logServiceExceptions(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in {}.{}() with message: {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                ex.getMessage(), ex);
    }
}
