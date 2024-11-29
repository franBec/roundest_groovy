package dev.pollito.roundest_groovy.aspect

import groovy.util.logging.Slf4j
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component

@Aspect
@Component
@Slf4j
class LogAspect {

    @Pointcut("execution(public * dev.pollito.roundest_groovy.controller..*.*(..))")
    void controllerPublicMethodsPointcut() {
        // Pointcut for public methods in controller package
    }

    @Before("controllerPublicMethodsPointcut()")
    void logBefore(JoinPoint joinPoint) {
        log.info(
                "[${joinPoint.signature.toShortString()}] Args: ${joinPoint.args}"
        )
    }

    @AfterReturning(pointcut = "controllerPublicMethodsPointcut()", returning = "result")
    void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info(
                "[${joinPoint.signature.toShortString()}] Response: ${result}"
        )
    }
}
