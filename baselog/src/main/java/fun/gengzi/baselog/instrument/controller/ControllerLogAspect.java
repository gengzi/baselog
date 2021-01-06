package fun.gengzi.baselog.instrument.controller;


import fun.gengzi.baselog.BaseLogProperties;
import fun.gengzi.baselog.LogFiedsEnum;
import fun.gengzi.baselog.LoggerInfo;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;

/**
 * <H1>Controller </H1>
 *
 * @author gengzi
 * @date 2021年1月6日14:44:15
 */
@Configuration
public class ControllerLogAspect implements MethodInterceptor {

    private LoggerInfo loggerInfo;

    public ControllerLogAspect(LoggerInfo loggerInfo) {
        this.loggerInfo = loggerInfo;
    }

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
    private static final ThreadLocal<Long> BUSINESS_TIME = new ThreadLocal<>();


    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 设置进入业务时间
        BUSINESS_TIME.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null){
            return methodInvocation.proceed();
        }
        HttpServletRequest request = requestAttributes.getRequest();


        Method method = methodInvocation.getMethod();



        Object proceed = methodInvocation.proceed();
        return proceed;
    }
}
