package xyz.wenjiesx.book.tij.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenji
 * @date 2019/10/19
 */
public class AtomicityTest implements Runnable {

    private int i = 0;
    public int getValue(){
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
        i++;
        i++;
        i++;
    }

    public void run() {

        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        AtomicityTest atomicityTest = new AtomicityTest();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(atomicityTest);

        while (true) {
            int val = atomicityTest.getValue();
            if (val % 5 != 0) {
                System.out.println(val);
                System.exit(0);
            } else {
                System.out.println(val);
            }
        }
    }
}
