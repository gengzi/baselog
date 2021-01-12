package fun.gengzi.baselog.test;

import fun.gengzi.baselog.instrument.annotations.BaseLog;
import org.springframework.stereotype.Service;

@Service
public class ServiceTestImpl implements ServiceTest {

    @BaseLog(businessInfo = "测试")
    @Override
    public String test(String data) {
        test1();
        return "测试" + data;
    }

    @BaseLog(businessInfo = "测试2")
    @Override
    public String test2(String data) {
        test("dd");
        return "测试" + data;
    }

    @BaseLog(businessInfo = "测试1")
    public String test1() {
        return "测试1";
    }

}
