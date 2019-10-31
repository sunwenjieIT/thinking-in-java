package xyz.wenjiesx.book.multithreading.practice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 练习18
 * 创建一个非任务的类, 它有一个用较长的时间间隔调用sleep()的方法
 * 创建一个任务, 它将调用这个非任务类上的那个方法
 * 在main()中,启动该任务, 然后调用interrupt()来终止它
 * 确保这个任务被安全地关闭
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice18 {
    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> f = exec.submit(new Task());
        ExecutorService exec2 = Executors.newCachedThreadPool();
        exec2.execute(new Task());
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
        f.cancel(true);
        exec.shutdown();
        exec2.shutdownNow();
    }
}

class Test {

    public void action() {

        try {
            System.out.println("test sleep 5 seconds");
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        } finally {
            System.out.println("action exit");
        }

    }

}

class Task implements Runnable {

    private static Test t = new Test();

    public void run() {
        t.action();
    }
}