package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习12
 * 使用synchronized修复Atomicity.java
 * 并证明是安全的
 *
 * @author wenji
 * @date 2019/10/19
 */
public class Practice12AtomicityRepair implements Runnable {

    private int i = 0;

    public synchronized int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
        i++;
    }

    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Practice12AtomicityRepair at = new Practice12AtomicityRepair();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val < 0) {
                System.out.println("max int exceed");
                System.exit(0);

            }
            if (val % 10 != 0) {
                System.out.println(val);
                System.exit(0);
            } else {
                System.out.println(val);
            }
        }
    }

}

