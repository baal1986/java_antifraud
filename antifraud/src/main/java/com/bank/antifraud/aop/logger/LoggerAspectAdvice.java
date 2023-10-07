package com.bank.antifraud.aop.logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class LoggerAspectAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerAspectAdvice.class);

    @Before("execution(* com.bank.antifraud.controller..*(..)) || execution(* com.bank.antifraud.service..*(..))")
    public void writeLogMethodExec(JoinPoint joinPoint) {
        LOGGER.info("Method: " + joinPoint.getSignature().getName() +
                ", class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                ", args: " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterThrowing(pointcut = "execution(* com.bank.antifraud.controller..*(..)) || execution(* com.bank.antifraud.service..*(..))",
            throwing = "th", argNames = "joinPoint, th")
    public void writeLogThrowing(JoinPoint joinPoint, Throwable th) {
        LOGGER.error("Exception in " + joinPoint.getSignature().getName() +
                ", class: " + joinPoint.getSignature().getDeclaringType().getSimpleName() +
                ", args: " + Arrays.toString(joinPoint.getArgs()) + ": " +
                th.getMessage());
    }
}

