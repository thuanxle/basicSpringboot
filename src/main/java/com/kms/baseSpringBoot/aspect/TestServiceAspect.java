package com.kms.baseSpringBoot.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class TestServiceAspect {
    private Logger logger = LoggerFactory.getLogger(TestServiceAspect.class);

    @Before("execution(* com.kms.baseSpringBoot.controllers.*.*(..))")
    public void before(JoinPoint joinPoint) {
        logger.info(" before called " + joinPoint.toString());
    }
//
//    @After("execution(* com.ldt.demospringaop.dao.*.*(..))")
//    public void after(JoinPoint joinPoint) {
//        logger.info(" after called " + joinPoint.toString());
//    }
//
//    @AfterReturning("execution(* com.ldt.demospringaop.dao.*.*(..))")
//    public void afterReturning(JoinPoint joinPoint) {
//        logger.info(" afterReturning called " + joinPoint.toString());
//    }
//
//    @AfterThrowing("execution(* com.ldt.demospringaop.dao.*.*(..))")
//    public void afterThrowing(JoinPoint joinPoint) {
//        logger.info(" afterThrowing called " + joinPoint.toString());
//    }
//
//    @Around("execution(* com.ldt.demospringaop.dao.*.*(..))")
//    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        Long startTime = System.currentTimeMillis();
//        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
//        joinPoint.proceed();
//
//        Long timeTaken = System.currentTimeMillis() - startTime;
//        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
//    }
//
    @Around("@annotation(com.kms.baseSpringBoot.aspect.TrackTime)")
    public void aroundTrackTime(ProceedingJoinPoint joinPoint) throws Throwable {

        Long startTime = System.currentTimeMillis();
        logger.info("Start Time Taken by {} is {}", joinPoint, startTime);
        joinPoint.proceed();

        Long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
    }
}