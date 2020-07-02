package xyz.wenjiesx.book.tij.multithreading.practice;



import xyz.wenjiesx.book.tij.multithreading.Daemon;

import java.util.concurrent.TimeUnit;

/**
 * 练习7
 * 在Daemons.java中使用不同的休眠时间, 观察结果
 *
 * @author wenji
 * @date 2019/10/18
 */
public class Practice7Daemons {

    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon());
        TimeUnit.MILLISECONDS.sleep(1);
    }

}
