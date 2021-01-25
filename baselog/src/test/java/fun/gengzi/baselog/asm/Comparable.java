package fun.gengzi.baselog.asm;

public interface Comparable extends Runnable {
    int LESS = -1;
    int EQUAL = 0;
    int GREATER = 1;

    int compareTo(Object o);
}
