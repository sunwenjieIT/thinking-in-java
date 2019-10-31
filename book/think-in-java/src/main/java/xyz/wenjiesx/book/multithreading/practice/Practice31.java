package xyz.wenjiesx.book.multithreading.practice;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 练习31
 * 修改DeadlockingDiningPhiloshophers.java
 * 使得当哲学家用完筷子后, 把筷子放在一个筷笼里
 * 当哲学家要就餐的时候, 他们就从筷笼里取出下两根可用的筷子.
 * 这消除了死锁的可能吗?
 * 你能仅仅通过减少可用的筷子数目就重新引入死锁吗?
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice31 {

    public static void main(String[] args) throws InterruptedException, IOException {
        int ponder = 0;
        int size = 5;
        ExecutorService exec = Executors.newCachedThreadPool();
        LinkedBlockingQueue<Chopstick> bin = new LinkedBlockingQueue<Chopstick>();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick();
            bin.put(chopsticks[i]);
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], bin, i, ponder));
        }

        System.out.println("Press 'Enter' to quit");
        System.in.read();
        exec.shutdownNow();
    }

}

class Philosopher implements Runnable {
    private Chopstick left;
    private Chopstick right;
    private LinkedBlockingQueue<Chopstick> bin;
    private final int id;
    private final int ponderFactor;
    private Random random = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponderFactor * 250));

    }

    public Philosopher(Chopstick left, Chopstick right, LinkedBlockingQueue<Chopstick> bin, int id, int ponderFactor) {
        this.left = left;
        this.right = right;
        this.bin = bin;
        this.id = id;
        this.ponderFactor = ponderFactor;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println(this + " thinking");
                pause();

                System.out.println(this + " taking first, right chopstick");
                right = bin.take();
                System.out.println(this + " taking second, left chopstick");
                left = bin.take();
                System.out.println(this + " eating");
                pause();
                System.out.println(this + " returning chopsticks");
                bin.put(right);
                bin.put(left);

            }
        } catch (InterruptedException e) {
            System.out.println(this + " exit via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }

}

class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}