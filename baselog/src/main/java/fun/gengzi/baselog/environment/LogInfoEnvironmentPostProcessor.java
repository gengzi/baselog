package fun.gengzi.baselog.environment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;


/**
 * <h1>日志信息环境后处理器</h1>
 *
 *
 * 主要用于设置默认的配置
 *
 * autoconfig
 * 配置对象注入，过滤器，拦截器，aop
 *
 *
 * @author gengzi
 * @date 2021年1月4日22:35:42
 */
@Component
public class LogInfoEnvironmentPostProcessor implements EnvironmentPostProcessor {


    /**
     *
     *
     *
     *
     *
     *
     * @param environment
     * @param application
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

    }
}
