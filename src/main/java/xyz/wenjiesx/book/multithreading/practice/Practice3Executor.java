package xyz.wenjiesx.book.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 练习3
 * 使用各种类型的执行器重复练习1
 * @author wenji
 * @date 2019/10/16
 */
public class Practice3Executor {

    public static void main(String[] args) {

        //练习3

        ExecutorService exec1 = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec1.execute(new Practice1Runnable(7));
        }
        exec1.shutdown();

        ExecutorService exec2 = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            exec2.execute(new Practice1Runnable(9));
        }
        exec2.shutdown();
        ExecutorService exec3 = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            exec3.execute(new Practice1Runnable(10));
        }
        exec3.shutdown();

    }


}
