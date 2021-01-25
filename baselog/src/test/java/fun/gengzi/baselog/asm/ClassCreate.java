package fun.gengzi.baselog.asm;

import java.io.IOException;

//package fun.gengzi.baselog.asm;
//
//public interface Comparable extends Runnable {
//    int LESS = -1;
//    int EQUAL = 0;
//    int GREATER = 1;
//
//    int compareTo(Object o);
//}


// 构建上述类

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 已编译类不包含 Package 和 Import 部分
 */
public class ClassCreate {

    public static void main(String[] args) throws IOException {

        ClassWriter classWriter = new ClassWriter(0);
        // 构建 public interface Comparable extends Runnable
        // 指明了类的版本—— Java 1.5
        classWriter.visit(Opcodes.V1_5, Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT + Opcodes.ACC_INTERFACE,
                "fun/gengzi/baselog/asm/Comparable", null, "java/lang/Object",
                new String[]{"java/lang/Runnable"});

        // 构建所有的字段
        // int LESS = -1;
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "LESS",
                "I", null, new Integer(-1)).visitEnd();
        //  int EQUAL = 0;
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "EQUAL",
                "I", null, new Integer(0)).visitEnd();
        // int GREATER = 1;
        classWriter.visitField(Opcodes.ACC_PUBLIC + Opcodes.ACC_FINAL + Opcodes.ACC_STATIC, "GREATER",
                "I", null, new Integer(1)).visitEnd();

        // 构建所有的方法

        classWriter.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();

        classWriter.visitEnd();

        byte[] bytes = classWriter.toByteArray();
        File file = new File("D:\\ideaworkspace\\Comparable.class");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
        fileOutputStream.close();


    }


}
