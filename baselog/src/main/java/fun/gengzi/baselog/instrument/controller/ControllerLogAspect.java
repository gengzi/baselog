package fun.gengzi.baselog.instrument.controller;


import fun.gengzi.baselog.BaseLogProperties;
import fun.gengzi.baselog.LogFiedsEnum;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.sql.PreparedStatement;

/**
 * <H1>Controller </H1>
 *
 * @author gengzi
 * @date 2021年1月6日14:44:15
 */
@Aspect
@Configuration
public class ControllerLogAspect implements MethodInterceptor {

//
//    /**
//     * 将线程中的traceid 传递进来
//     *
//     * @param joinPoint
//     */
//    @Around("requestLog()")
//    public Object doBefore(ProceedingJoinPoint joinPoint) throws Throwable {
//        String traceId = MDC.get(LogFiedsEnum.TRACEID.getLogFide());
//
//
//        Object proceed = null;
//        try {
//            proceed = joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return proceed;
//
//    }


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {

        Object proceed = methodInvocation.proceed();
        return proceed;
    }
}
