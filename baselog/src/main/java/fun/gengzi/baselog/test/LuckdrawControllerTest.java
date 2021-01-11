package fun.gengzi.baselog.test;

import fun.gengzi.baselog.LoggerInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@Controller
@RequestMapping("/test")
public class LuckdrawControllerTest {

    private static final AtomicInteger num = new AtomicInteger(1);

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
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

    @RequestMapping("/logtest")
    @ResponseBody
//    @ThreadPoolExecuteLog
    public LoggerInfo dbTest(@RequestParam("data") String data) {
        log.info("测试打印日志1：{}", data);
        String aa = data;
        log.info("测试打印日志2：{}", data);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("测试打印日志3：{}", data);


        threadPoolExecutor.execute(() -> {
            // 会丢失日志
            log.info("测试打印日志4：{}", data);
        });


        LoggerInfo loggerInfo = new LoggerInfo();
        loggerInfo.setDeviceIp("hahah");

        return loggerInfo;
    }

}

