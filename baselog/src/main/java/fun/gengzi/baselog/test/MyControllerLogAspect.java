package fun.gengzi.baselog.test;


import fun.gengzi.baselog.instrument.controller.ControllerLogAspect;
import fun.gengzi.baselog.instrument.controller.LoggerFormat;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MyControllerLogAspect extends ControllerLogAspect {

//    @Override
//    public LoggerFormat requestLog(MethodInvocation methodInvocation) {
//        log.info("ddd");
//        super.requestLog(methodInvocation);
//    }

//    @Override
//    public LoggerFormat responseLog(MethodInvocation methodInvocation) {
//        super.responseLog(methodInvocation);
//
//    }
}
