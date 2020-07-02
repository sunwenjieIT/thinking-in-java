package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wenji
 * @date 2019/10/16
 */
public class Practice4ExecutorFibonacci {

    public static void main(String[] args) {

        ExecutorService exec1 = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            exec1.execute(new Practice2Fibonacci(5));

        }
        exec1.shutdown();

        ExecutorService exec2 = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            exec2.execute(new Practice2Fibonacci(6));
        }
        exec2.shutdown();

        ExecutorService exec3 = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 5; i++) {
            exec3.execute(new Practice2Fibonacci(7));
        }

        exec3.shutdown();
    }

}
