package xyz.wenjiesx.book.multithreading.practice;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 练习30
 * 修改PipedIO.java, 使其使用BlockingQueue而不是管道
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice30 {

    public static void main(String[] args) throws InterruptedException, IOException {
        /*Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);*/
        LinkedBlockingQueue<Character> queue = new LinkedBlockingQueue<Character>();
        Sender sender = new Sender(queue);
        Receiver receiver = new Receiver(queue);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}

class Sender implements Runnable {

    private Random random = new Random(47);
    private PipedWriter out = new PipedWriter();

    private LinkedBlockingQueue<Character> queue;

    public Sender(LinkedBlockingQueue<Character> queue) {
        this.queue = queue;
    }

    public PipedWriter getPipedWriter() {
        return out;
    }


    public void run() {

        try {
            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    queue.put(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
                }
            }
        } catch (InterruptedException e) {
            System.out.println(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private LinkedBlockingQueue<Character> queue;

    public Receiver(LinkedBlockingQueue<Character> queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("Read: " + queue.take() + ",");
            }
        } catch (InterruptedException e) {
            System.out.println(e + " Receiver interrupted exception");
        }
    }
}
