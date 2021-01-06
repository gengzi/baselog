package fun.gengzi.baselog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggerInfo {
    // 请求路径
    private String uri;
    // 跟踪id
    private String traceid;
    // 请求ip
    private String deviceIp;
    // 请求方法名称
    private String requestName;

}
