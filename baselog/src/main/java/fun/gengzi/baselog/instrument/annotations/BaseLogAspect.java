package fun.gengzi.baselog.instrument.annotations;

import cn.hutool.json.JSONUtil;
import io.micrometer.core.instrument.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Configuration
public class BaseLogAspect {

    @Pointcut("@annotation(fun.gengzi.baselog.instrument.annotations.BaseLog)")
    public void ServiceAspect() {

    }

    @Around("ServiceAspect()")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        BaseLog baseLogAnnotation = method.getAnnotation(BaseLog.class);
        String businessInfo = baseLogAnnotation.businessInfo();
        log.info("{}#join{}",businessInfo,JSONUtil.toJsonStr(joinPoint.getArgs()));
        Object obj = null;
        try {
            obj = joinPoint.proceed();
            log.info("{}#participate{}",businessInfo, JSONUtil.toJsonStr(obj));
        } catch (Throwable e) {

        }
        return obj;
    }
}