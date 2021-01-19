package fun.gengzi.baselog.test;


import javassist.*;
import javassist.bytecode.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

/**
 * https://blog.csdn.net/ezreal_king/article/details/60879025
 *
 *
 */
public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, IllegalAccessException, InstantiationException, IOException, BadBytecode {
        ClassPool cp = ClassPool.getDefault();

        CtClass cc = cp.get("fun.gengzi.baselog.test.LuckdrawControllerTest");
        CtField[] fields = cc.getFields();
        fields[0].setType(cp.get("fun.gengzi.baselog.test.BaseLogThreadPoolExecuter"));


        CtMethod[] methods = cc.getMethods();
        MethodInfo methodInfo2 = methods[2].getMethodInfo2();


        CodeAttribute codeAttribute = methodInfo2.getCodeAttribute();

        CodeIterator iterator = codeAttribute.iterator();

        while (iterator.hasNext()) {
            int index = iterator.next();
            int op = iterator.byteAt(index);

            System.out.println(Mnemonic.OPCODE[op]);
        }
//        CtMethod m = cc.getDeclaredMethod("execute");
//        m.insertBefore("{ System.out.println(\"start\"); }");
//        m.insertAfter("{ System.out.println(\"end\"); }");
        Class c = cc.toClass();
        cc.writeFile("D:\\ideaworkspace");
//        ThreadPoolExecutor h = (ThreadPoolExecutor)c.newInstance();


    }
}