package xyz.wenjiesx.book.multithreading;

import java.util.concurrent.ThreadFactory;

/**
 * @author wenji
 * @date 2019/10/17
 */
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }

}
