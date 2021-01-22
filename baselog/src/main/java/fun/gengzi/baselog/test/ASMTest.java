package fun.gengzi.baselog.test;

import fun.gengzi.baselog.instrument.execute.MDCInheritableThreadLocal;
import org.slf4j.MDC;

import java.util.Map;

public class ASMTest {


    public void exec(){
        MDC.setContextMap(null);
    }

}
