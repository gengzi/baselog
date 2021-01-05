package fun.gengzi.baselog.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
@RequestMapping("/test")
public class LuckdrawControllerTest {

    @RequestMapping("/logtest")
    @ResponseBody
    public void dbTest(@RequestParam("data") String data) {
        log.info("测试打印日志：{}", data);
    }


}

