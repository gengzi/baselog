package fun.gengzi.baselog.trace;

import cn.hutool.core.util.IdUtil;

/**
 * <H1>默认实现</H1>
 */
public class DefaultCreateTraceId extends CreateTraceId {

    // Lazily initialized and cached.
    volatile String traceIdString;

    /**
     * 创建方法
     *
     * @return
     */
    @Override
    public String create() {
        String r = traceIdString;
        if (r == null) {
            r = IdUtil.simpleUUID();
            traceIdString = r;
        }
        return r;
    }
}
