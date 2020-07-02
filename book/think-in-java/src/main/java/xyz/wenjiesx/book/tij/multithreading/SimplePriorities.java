package xyz.wenjiesx.book.tij.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author wenji
 * @date 2019/10/17
 */
public class SimplePriorities implements Runnable{

    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority) {
        this.priority = priority;
    }

    public SimplePriorities() {
    }

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    public void run() {
        if (priority != 0)
            Thread.currentThread().setPriority(priority);
        while (true) {
            for (int i = 0; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0) {
                    Thread.yield();
                }
            }
            System.out.println(this);
            if (--countDown == 0) return;

        }
    }

    public static void main(String[] args) {

        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {

            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY));
        exec.shutdown();

        ExecutorService execMin = Executors.newCachedThreadPool(new CustomThreadFactory(Thread.MIN_PRIORITY));

        for (int i = 0; i < 5; i++) {

            execMin.execute(new SimplePriorities());
        }
        execMin.shutdown();

        ExecutorService execMax = Executors.newCachedThreadPool(new CustomThreadFactory(Thread.MAX_PRIORITY));
        execMax.execute(new SimplePriorities());
        execMax.shutdown();

    }
}

class CustomThreadFactory implements ThreadFactory {

    private int priority;

    public CustomThreadFactory(int priority) {
        this.priority = priority;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        t.setDaemon(true);
        return t;
    }
}
