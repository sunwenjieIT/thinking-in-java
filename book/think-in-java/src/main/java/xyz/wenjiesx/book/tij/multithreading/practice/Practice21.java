package xyz.wenjiesx.book.tij.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习21
 * 创建两个Runnable, 其中一个的run方法启动并调用wait
 * 而第二个类应该捕获第一个runnable对象的引用
 * 其run方法应该在一定的秒数之后, 为第一个任务调用notifyAll
 * 从而使第一个任务可以显示一条信息
 * 使用Executor来测试你的类
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice21 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Run1 run1 = new Run1();
        exec.execute(run1);
        exec.execute(new Run2(run1));

        exec.shutdown();
    }
}

class Run1 implements Runnable {
    public volatile static boolean needWait = true;

    public void run() {
        try {
            synchronized (this) {
                while (needWait) {
                    System.out.println("run1 run() wait()");
                    wait();
                }
            }
            System.out.println("run1 wait finish.");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        } finally {
            System.out.println("bye");
        }
    }
}

class Run2 implements Runnable {

    private Run1 run1;

    public Run2(Run1 run1) {
        this.run1 = run1;
    }

    public void run() {

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (run1) {
            System.out.println("run2 run change wait signal after sleep 3 seconds");
            Run1.needWait = false;
            System.out.println("run2 run notify all");
            run1.notifyAll();
        }

    }
}
