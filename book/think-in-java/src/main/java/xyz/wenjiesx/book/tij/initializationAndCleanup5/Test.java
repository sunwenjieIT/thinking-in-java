package xyz.wenjiesx.book.tij.initializationAndCleanup5;

/**
 * @author wenji
 * @Date 2020/6/8
 */
public class Test {
    int i = f();
    int j = g(i);
    int f() {
        return 1;
    }

    int g(int n) {
        return n * 10;
    }
}
