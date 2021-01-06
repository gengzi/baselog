package fun.gengzi.baselog.instrument.execute;


import fun.gengzi.baselog.LogFiedsEnum;

/**
 * <h1>线程池增加Traceid 的注解</h1>
 */
public @interface ThreadPoolExecuteLog {

    String traceId() default "bl-traceid";


}
