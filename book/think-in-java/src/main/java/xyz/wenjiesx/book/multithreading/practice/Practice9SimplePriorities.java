package xyz.wenjiesx.book.multithreading.practice;

import xyz.wenjiesx.book.multithreading.SimplePriorities;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * 练习9
 * 修改SimplePriorities, 使得定制的ThreadFactory可以设置线程优先级
 *
 * @author wenji
 * @date 2019/10/19
 */
public class Practice9SimplePriorities {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.shutdown();

        ExecutorService execMaxPriority = Executors.newCachedThreadPool(new CustomThreadFactory(Thread.MAX_PRIORITY));

        execMaxPriority.execute(new SimplePriorities());
        execMaxPriority.shutdown();
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
