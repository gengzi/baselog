package fun.gengzi.baselog.test;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ThreadPoolClassVisitor extends ClassVisitor implements Opcodes {
    public ThreadPoolClassVisitor(ClassVisitor cv) {
        super(ASM5, cv);
    }
    @Override
    public void visit(int version, int access, String name, String signature,
                      String superName, String[] interfaces) {
        cv.visit(version, access, name, signature, superName, interfaces);
    }
    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature,
                exceptions);
        //Base类中有两个方法：无参构造以及process方法，这里不增强构造方法
        if (name.equals("execute") && mv != null) {
            mv = new MyMethodVisitor(mv);
        }
        return mv;
    }
    class MyMethodVisitor extends MethodVisitor implements Opcodes {
        public MyMethodVisitor(MethodVisitor mv) {
            super(Opcodes.ASM5, mv);
        }

        @Override
        public void visitCode() {
            super.visitCode();
//            mv.visitMethodInsn(INVOKESTATIC, "fun/gengzi/baselog/instrument/execute/MDCInheritableThreadLocal", "get", "()Ljava/lang/Object;", false);
//            mv.visitTypeInsn(CHECKCAST, "java/util/Map");
//            mv.visitMethodInsn(INVOKESTATIC, "org/slf4j/MDC", "setContextMap", "(Ljava/util/Map;)V", false);
            mv.visitInsn(ACONST_NULL);
            mv.visitMethodInsn(INVOKESTATIC, "org/slf4j/MDC", "setContextMap", "(Ljava/util/Map;)V", false);

        }
        @Override
        public void visitInsn(int opcode) {
            mv.visitInsn(opcode);
        }
    }
}