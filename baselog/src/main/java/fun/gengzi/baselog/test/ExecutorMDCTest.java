package fun.gengzi.baselog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorMDCTest {
    private static final AtomicInteger num = new AtomicInteger(1);

    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
            5,
            5 + 1,
            60L,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(10_00), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "test-thread" + num.getAndIncrement());
        }
    });

    static public void main(String[] args) throws Exception {
        Logger log = LoggerFactory.getLogger(ExecutorMDCTest.class);
        MDC.put("bl-traceid", "988f54f4bdd34920a53d908827a9fa59");
        Map<String, String> copyOfContextMap = MDC.getCopyOfContextMap();
        // 线程池
        threadPoolExecutor.execute(() -> {
            // 会丢失日志
            log.info("测试打印日志4：{}", "data");
            MDC.setContextMap(copyOfContextMap);
            log.info("设置后-测试打印日志5：{}", "data");
        });

    }
}