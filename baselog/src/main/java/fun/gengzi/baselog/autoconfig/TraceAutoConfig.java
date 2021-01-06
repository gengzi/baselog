package fun.gengzi.baselog.autoconfig;

import fun.gengzi.baselog.BaseLogProperties;
import fun.gengzi.baselog.MDCContentCreate;
import fun.gengzi.baselog.filter.TraceFilter;
import fun.gengzi.baselog.trace.CreateTraceId;
import fun.gengzi.baselog.trace.DefaultCreateTraceId;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>跟踪自动配置类</h1>
 * <p>
 * 1, 配置全局过滤器
 *
 * @author gengzi
 * @date 2021年1月5日14:03:17
 */
@Configuration
@ConditionalOnProperty(
        value = {"baselog.enabled"},
        matchIfMissing = true
)
@EnableConfigurationProperties({BaseLogProperties.class})
public class TraceAutoConfig {

    /**
     * 构造全局日志追踪过滤器
     *
     * @param baseLogProperties
     * @return
     */
    @Bean
    public TraceFilter traceFilter(BaseLogProperties baseLogProperties) {
        return new TraceFilter(baseLogProperties);
    }


    @Bean
    @ConditionalOnMissingBean // 如果用户创建了该Bean，此处将不再创建
    public CreateTraceId createTraceId() {
        return new DefaultCreateTraceId();
    }


    @Bean
    public MDCContentCreate mdcContentCreate() {
        return new MDCContentCreate();
    }


}
