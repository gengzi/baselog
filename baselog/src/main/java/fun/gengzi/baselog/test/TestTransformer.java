package fun.gengzi.baselog.test;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class TestTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
        // 排除一些不需要处理的方法
        if (!className.equals("java/util/concurrent/ThreadPoolExecutor")) {
            return classfileBuffer;
        }

        System.out.println("Transforming " + className);
        try {
            ClassReader classReader = new ClassReader("java.util.concurrent.ThreadPoolExecutor");
            ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
            //处理
            ClassVisitor classVisitor = new ThreadPoolClassVisitor(classWriter);
            classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
            byte[] data = classWriter.toByteArray();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}