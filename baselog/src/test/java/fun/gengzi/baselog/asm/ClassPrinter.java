package fun.gengzi.baselog.asm;

import jdk.internal.org.objectweb.asm.Opcodes;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;

import java.io.IOException;

/**
 * <h1>类信息输出类</h1>
 *
 * 继承 classvisitor（用于生成和变转已编译类的抽象类）
 *
 *         <dependency>
 *             <groupId>org.ow2.asm</groupId>
 *             <artifactId>asm</artifactId>
 *             <version>7.2-beta</version>
 *             <scope>compile</scope>
 *         </dependency>
 *
 */
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter(int api) {
        super(api);
    }

    public ClassPrinter(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        System.out.println(version + "|" + access + "|" + name + "|" + signature + "|" + superName + "|" + interfaces);
    }

    public static void main(String[] args) throws IOException {
        // Opcodes.ASM4  ASM API 的 version
        ClassPrinter cp = new ClassPrinter(Opcodes.ASM4);
        // 52|1537|java/lang/Runnable|null|java/lang/Object|[Ljava.lang.String;@1e80bfe8
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(cp, 0);
    }
}
