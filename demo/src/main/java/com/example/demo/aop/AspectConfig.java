package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class AspectConfig {

    Logger logger = LoggerFactory.getLogger(getClass());

//    @Before(value = "execution(* com.example.demo.controllers.*.*(..))")
//    public void beforeAdvice(JoinPoint jointPoint) {
//        logger.info("Inside Before Advice");
//    }

//    Before advice to intercept methods with 1 method parameter
    @Before(value = "execution(* com.example.demo.controllers.*.*(..)) && args(object1, object2)")
    public void beforeAdvice(JoinPoint jointPoint, Object object1, Object object2) {
        logger.info("Request = " + object1 + object2);
    }

    @After(value = "execution(* com.example.demo.controllers.*.*(..)) && args(object)")
    public void afterAdvice(JoinPoint jointPoint, Object object) {
        logger.info("Request = " + object);
    }

    @AfterReturning(value = "execution(* com.example.demo.controllers.*.*(..)) && args(object)",
            returning = "returningObject")
    public void afterReturningAdvice(JoinPoint jointPoint, Object object, Object returningObject) {
        logger.info("Response = " + returningObject);
    }

    @Around(value = "execution(* com.example.demo.controllers.*.*(..)) && args(object)")
    public void aroundAdvice(ProceedingJoinPoint proceedingJoinPoint, Object object) {
        logger.info("Response = " + object);

        Object returningObject = null;
        try {
            returningObject = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        logger.info("Response = " + returningObject);
    }
}
