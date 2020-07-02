package xyz.wenjiesx.book.tij.multithreading;

import java.util.concurrent.TimeUnit;

class DaemonSpawn implements Runnable {

    public void run() {
        while (true) {
            Thread.yield();
        }
    }
}

public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println("d.isDaemon() = " + d.isDaemon());
        TimeUnit.SECONDS.sleep(1);

    }
}
