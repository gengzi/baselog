package fun.gengzi.baselog.instrument.annotations;

import java.lang.annotation.*;

/**
 * <h1>基础日志注解</h1>
 *
 * @author gengzi
 * @date 2021年1月11日21:43:31
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseLog {
    // 业务信息
    String businessInfo() default "";

}
