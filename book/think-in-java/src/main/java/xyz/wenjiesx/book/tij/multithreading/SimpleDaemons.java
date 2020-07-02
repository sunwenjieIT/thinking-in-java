package xyz.wenjiesx.book.tij.multithreading;

import java.util.concurrent.TimeUnit;

/**
 * @author wenji
 * @date 2019/10/17
 */
public class SimpleDaemons implements Runnable {
    public void run() {

        try {

            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " + this);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("all daemons started");
        TimeUnit.MILLISECONDS.sleep(5750);

    }

}
