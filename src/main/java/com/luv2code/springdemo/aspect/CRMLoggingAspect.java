package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDAOPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDAOPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        logger.info("===>> in @Before: calling method: " + joinPoint.getSignature().toShortString());

        Arrays.asList(joinPoint.getArgs()).forEach(x -> logger.info("====>> argument: " + x));
    }

    @AfterReturning(pointcut = "forAppFlow()", returning = "theResult")
    public void afterReturning(JoinPoint joinPoint, Object theResult) {

        logger.info("===>> in @AfterReturning: from method: " + joinPoint.getSignature().toShortString());

        logger.info("===>> result: " + theResult);
    }


}
