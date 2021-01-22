package fun.gengzi.baselog.test;

import java.lang.instrument.Instrumentation;
import java.util.concurrent.ThreadPoolExecutor;

public class TestAgent {
    public static void premain(String args, Instrumentation inst) {
        //指定我们自己定义的Transformer，在其中利用Javassist做字节码替换
        inst.addTransformer(new TestTransformer(), true);
        try {
            //重定义类并载入新的字节码
            inst.retransformClasses(ThreadPoolExecutor.class);
            System.out.println("Agent Load Done.");
        } catch (Exception e) {
            System.out.println("agent load failed!");
        }
    }
}