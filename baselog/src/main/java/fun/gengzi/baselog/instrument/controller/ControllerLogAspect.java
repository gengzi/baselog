package fun.gengzi.baselog.instrument.controller;


import cn.hutool.json.JSONUtil;
import fun.gengzi.baselog.BaseLogProperties;
import fun.gengzi.baselog.LogFiedsEnum;
import fun.gengzi.baselog.LoggerInfo;
import fun.gengzi.baselog.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletMapping;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.ArrayList;

/**
 * <H1>Controller 日志切面</H1>
 *
 * 如果需要定制打印内容，实现 requestLog  responseLog 即可。
 *
 *
 * @author gengzi
 * @date 2021年1月6日14:44:15
 */
@Slf4j
public class ControllerLogAspect implements MethodInterceptor {

    private static final ThreadLocal<Long> BUSINESS_TIME = new ThreadLocal<>();


    /**
     * 请求日志
     */
    public LoggerFormat requestLog(MethodInvocation methodInvocation) {
        // 设置进入业务时间
        BUSINESS_TIME.set(System.currentTimeMillis());
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        LoggerInfo loggerInfo = new LoggerInfo();
        HttpServletRequest request = requestAttributes.getRequest();
        Method method = methodInvocation.getMethod();
        String ipAddr = IPUtils.getIpAddr();
        loggerInfo.setDeviceIp(ipAddr);
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(method.getName());
        objects.add(JSONUtil.toJsonStr(loggerInfo));
        return new LoggerFormat("{}#request:{}", objects);
//        log.info("{}#request:{}", method.getName(), JSONUtil.toJsonStr(loggerInfo));
    }


    /**
     * 响应日志
     */
    public LoggerFormat responseLog(MethodInvocation methodInvocation) {
        Method method = methodInvocation.getMethod();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(method.getName());
        return new LoggerFormat("{}#response", objects);
    }


    /**
     * 调用方法
     *
     * @param methodInvocation
     * @return
     * @throws Throwable
     */
    @Override
    public final Object invoke(MethodInvocation methodInvocation) throws Throwable {
        // 记录请求日志
        LoggerFormat requestLog = requestLog(methodInvocation);
        log.info(requestLog.getFormat(), requestLog.getLogContent().toArray());
        // 执行controller方法
        Object proceed = methodInvocation.proceed();
        // 记录响应日志
        LoggerFormat responseLog = responseLog(methodInvocation);
        log.info(responseLog.getFormat(), responseLog.getLogContent().toArray());
        return proceed;
    }
}
