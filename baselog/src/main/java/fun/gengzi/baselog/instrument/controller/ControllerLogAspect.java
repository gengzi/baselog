package fun.gengzi.baselog.instrument.controller;


import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import fun.gengzi.baselog.LoggerInfo;
import fun.gengzi.baselog.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * <H1>Controller 日志切面</H1>
 * <p>
 * 如果需要定制打印内容，实现 requestLog  responseLog 即可。
 *
 * @author gengzi
 * @date 2021年1月6日14:44:15
 */
@Slf4j
public class ControllerLogAspect implements MethodInterceptor {

    @Autowired
    private BaseLogUserService baseLogUserService;

    private static final ThreadLocal<Long> BUSINESS_TIME = new ThreadLocal<>();


    /**
     * 请求日志
     */
    public LoggerFormat requestLog(MethodInvocation methodInvocation) {
        // 设置进入业务时间
        BUSINESS_TIME.set(System.currentTimeMillis());
        // 获取请求信息
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return null;
        }
        LoggerInfo loggerInfo = new LoggerInfo();
        HttpServletRequest request = requestAttributes.getRequest();
        // 请求方法名称
        Method method = methodInvocation.getMethod();
        final String ipAddr = IPUtils.getIpAddr();
        // 请求方式
        final String requestMethod = request.getMethod();
        // 请求路径
        final String requestURI = request.getRequestURI();
        // 接口调用实际路径
        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
            // 所有的参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            loggerInfo.setRequestMap(parameterMap);
        } else if (request.getMethod().equalsIgnoreCase(RequestMethod.POST.name())) {
            Object[] arguments = methodInvocation.getArguments();
            loggerInfo.setRequestData(arguments);
        }
        // 设置到threadlocal中
        LoggerHolder.set(loggerInfo);
        // 设置请求信息
        loggerInfo.setDeviceIp(ipAddr);
        loggerInfo.setReqUrl(requestURI);
        loggerInfo.setHttpMethod(requestMethod);
        loggerInfo.setRequestName(method.getName());
        loggerInfo.setUserId(baseLogUserService.getUserId() == null ? baseLogUserService.getUserId() : "");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(method.getName());
        objects.add(JSONUtil.toJsonStr(loggerInfo));
        // crl层接口类接口方法请求数据
        return new LoggerFormat("{}#request:{}", objects);
    }


    /**
     * 响应日志
     */
    public LoggerFormat responseLog(MethodInvocation methodInvocation, Object proceed) {
        try {
            // 移除本线程中存储的内容，手动清除防止内容泄露
            LoggerInfo loggerInfo = LoggerHolder.get();

            if (JSONUtil.isJsonObj(JSONUtil.toJsonStr(proceed))) {
                JSONObject jsonObject = JSONUtil.parseObj(JSONUtil.toJsonStr(proceed));
                loggerInfo.setResult(jsonObject);
            } else if (JSONUtil.isJsonArray(JSONUtil.toJsonStr(proceed))) {
                JSONArray objects = JSONUtil.parseArray(JSONUtil.toJsonStr(proceed));
                loggerInfo.setResult(objects);
            } else {
                loggerInfo.setResult(proceed);
            }
            loggerInfo.setTimeCostMils(System.currentTimeMillis() - BUSINESS_TIME.get());

            Method method = methodInvocation.getMethod();
            ArrayList<Object> objects = new ArrayList<>();
            objects.add(method.getName());
            objects.add(JSONUtil.toJsonStr(loggerInfo));
            return new LoggerFormat("{}#response:{}", objects);
        } catch (Exception e) {
            return new LoggerFormat("{}#response:{}", new ArrayList<>());
        } finally {
            // 移除本线程中存储的内容，手动清除防止内容泄露
            LoggerHolder.remove();
            BUSINESS_TIME.remove();
        }


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
        LoggerFormat responseLog = responseLog(methodInvocation, proceed);
        log.info(responseLog.getFormat(), responseLog.getLogContent().toArray());
        return proceed;
    }
}