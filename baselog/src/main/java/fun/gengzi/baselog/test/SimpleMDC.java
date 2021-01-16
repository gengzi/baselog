package fun.gengzi.baselog.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.core.ConsoleAppender;

public class SimpleMDC {
    static public void main(String[] args) throws Exception {
        // 你可以选择在任何时候将值放入 MDC 中
        MDC.put("clientId", "客户端01");
        Logger logger = LoggerFactory.getLogger(SimpleMDC.class);
        MDC.put("addressIp", "192.168.0.1");
        logger.info("执行任务");
        logger.info("完成");
        MDC.put("clientId", "客户端02");
        MDC.put("addressIp", "192.168.0.2");
        logger.info("执行任务");
        logger.info("完成");

        new Thread(() -> {
            logger.info("子线程-执行任务");
        },"子线程").start();


    }
}