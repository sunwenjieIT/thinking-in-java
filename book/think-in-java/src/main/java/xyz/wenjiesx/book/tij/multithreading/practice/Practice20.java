package xyz.wenjiesx.book.tij.multithreading.practice;

import xyz.wenjiesx.book.tij.multithreading.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 练习20
 * 修改CachedThreadPool.java
 * 使所有任务在结束前都将受到一个interrupt()
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice20 {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Using LiftOff:");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            Future<?> f = exec.submit(new LiftOff());
            f.cancel(true);
        }
        exec.shutdownNow();
        if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some tasks were not terminated");
        // Using modified LiftOff:
        System.out.println("\nUsing LiftOff20:");
        ExecutorService exec20 = Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            Future<?> f = exec20.submit(new LiftOff());
            f.cancel(true);
        }
        exec20.shutdown();
//        exec20.shutdownNow();
        if(!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            System.out.println("Some tasks were not terminated");
    }

}
