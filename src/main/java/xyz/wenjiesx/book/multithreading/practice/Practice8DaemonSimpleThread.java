package xyz.wenjiesx.book.multithreading.practice;

import java.util.concurrent.TimeUnit;

/**
 * 练习8
 * 把simpleThread.java中的所有线程修改成后台进程
 * 并验证, 一旦main退出, 程序立刻终止
 *
 * @author wenji
 * @date 2019/10/19
 */
public class Practice8DaemonSimpleThread {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new SimpleThread("" + i);
        }

        TimeUnit.MILLISECONDS.sleep(500);

        System.out.println("main thread exit");
    }


}


class SimpleThread extends Thread {


    public SimpleThread(String name) {
        super(name);
        this.setDaemon(true);
        this.start();
    }

    @Override
    public void run() {

        try {
            for (; ; ) {
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.println(currentThread().getName() + " is running");
                Thread.yield();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}