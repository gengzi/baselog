package fun.gengzi.baselog;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.ParameterizedTypeReference;

import java.io.Serializable;
import java.util.List;

/**
 * <h1>基础日志属性绑定类</h1>
 *
 * @author gengzi
 * @date 2021年1月5日10:38:15
 */
@Data
@ConfigurationProperties("baselog")
public class BaseLogProperties implements Serializable {
    // 默认日志包围路径
    private List<String> packageurl;
    // 是否开启基础日志
    private String enable;
    // 忽略的日志字段
    private List<String> ignoreLogFields;


}
