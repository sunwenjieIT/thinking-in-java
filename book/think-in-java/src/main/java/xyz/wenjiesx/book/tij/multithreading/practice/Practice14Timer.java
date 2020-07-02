package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习14
 * 创建一个程序, 可以生成许多Timer对象
 * 这些对象在定时时间到达后将执行某个简单的任务
 * 用这个程序来证明java.util.Timer可以扩展到很大的数目
 *
 * @author wenji
 * @date 2019/10/19
 */
public class Practice14Timer implements Runnable {
    private static int timers = 0;
    private static int tasks = 0;

    public void run() {
        try {
            // create 4000 Timers
            while (timers < 4000) {
                ++timers;
                Timer t = new Timer();
                t.schedule(new TimerTask() {
                    public void run() {
                        ++tasks;
                        if (timers % 100 == 0)
                            System.out.println(timers + " timers did "
                                    + tasks + " tasks");
                    }
                }, 0);
                try {
                    // time to do task
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (InterruptedException e) {
                    System.out.println("Sleep interrupted");
                }
                t.cancel();
            }
        } finally {
            System.out.println("Done. " + timers + " timers completed "
                    + tasks + " tasks");
        }
    }

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Practice14Timer());
        exec.shutdown();

    }
}
