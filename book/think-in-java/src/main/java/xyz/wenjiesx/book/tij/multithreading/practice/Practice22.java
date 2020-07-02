package xyz.wenjiesx.book.tij.multithreading.practice;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 练习22
 * 创建一个忙等待的示例
 * 第一个任务休眠一段时间然后将一个标志设置为true
 * 第二个任务在一个while循环中观察这个标志, 并且当该标志位true时, 将其设置回false
 * 然后向控制台报告这个变化
 * 请注意程序在忙等待中浪费了多少时间, 然后创建该程序的第二个版本,
 * 其中将使用wait()而不是忙等待
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice22 {
    public static volatile boolean signal = false;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new SleepTask());
        exec.execute(new WhileTask());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("exec shutdown now");
        exec.shutdownNow();

        ExecutorService waitExec = Executors.newCachedThreadPool();
        SleepTask sleepTask = new SleepTask();
        waitExec.execute(new WaitTask(sleepTask));
        waitExec.execute(new SleepTask());

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitExec.shutdownNow();
    }
}

class WhileTask implements Runnable {

    public void run() {
        while (!Thread.interrupted()) {
            if (Practice22.signal) {
                System.out.println("signal change to false");
                Practice22.signal = false;
            }
        }
    }
}

class SleepTask implements Runnable {

    public synchronized void run() {
        System.out.println("sleep task sleep 5 seconds");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println("sleep task interrupt");
        }
        System.out.println("sleep task update signal to true");
        Practice22.signal = true;
        System.out.println("sleep task notify all");
        notifyAll();
    }
}

class WaitTask implements Runnable {

    private SleepTask sleepTask;

    public WaitTask(SleepTask sleepTask) {
        this.sleepTask = sleepTask;
    }

    public synchronized void run() {

        try {
            while (!Practice22.signal) {
//                synchronized (sleepTask) {
                    System.out.println("wait task run wait()");
                    wait();
                    Practice22.signal = false;
//                }
            }
            System.out.println("wait task finish while loop");
        } catch (InterruptedException e) {
            System.out.println("wait task interrupt");
        }

    }
}