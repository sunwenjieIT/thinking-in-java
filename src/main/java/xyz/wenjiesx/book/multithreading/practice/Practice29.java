package xyz.wenjiesx.book.multithreading.practice;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 练习29
 * 修改ToastOMatic.java
 * 使用两个单独的组装线来创建涂有花生黄油和果冻的吐司三明治
 * (一个用于花生黄油, 第二个用于果冻, 然后把两条线合并)
 *
 * @author wenji
 * @date 2019/10/21
 */
public class Practice29 {
    public static void main(String[] args) {
        ToastQueue toastQueue = new ToastQueue();
        ToastQueue jellyQueue = new ToastQueue();
        ToastQueue peanutButterQueue = new ToastQueue();
        SandwichQueue sandwichQueue = new SandwichQueue();
        ExecutorService exec = Executors.newCachedThreadPool();

        exec.execute(new Toaster(toastQueue));
        exec.execute(new Jellyer(toastQueue, jellyQueue));
        exec.execute(new PeanutButterer(toastQueue, peanutButterQueue));
        exec.execute(new SandwichMaker(jellyQueue, peanutButterQueue, sandwichQueue));
        exec.execute(new SandwichConsumer(sandwichQueue));

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("main interrupted");
        }
        exec.shutdownNow();

    }
}

class Toast {
    public enum Status {DRY, JELLIED, PEANUTBUTTERED}

    private Status status = Status.DRY;
    private final int id;

    public Toast(int id) {
        this.id = id;
    }

    public void jelly() {
        status = Status.JELLIED;
    }

    public void peanutButter() {
        status = Status.PEANUTBUTTERED;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Toast{" +
                "status=" + status +
                ", id=" + id +
                '}';
    }
}

class Sandwich {

    private Toast top;
    private Toast bottom;
    private final int id;

    public Sandwich(Toast top, Toast bottom, int id) {
        this.top = top;
        this.bottom = bottom;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Toast getTop() {
        return top;
    }

    public Toast getBottom() {
        return bottom;
    }

    @Override
    public String toString() {
        return "Sandwich{" +
                "top=" + top +
                ", bottom=" + bottom +
                ", id=" + id +
                '}';
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class SandwichQueue extends LinkedBlockingQueue<Sandwich> {

}

class Toaster implements Runnable {
    private ToastQueue dryQueue;
    private int count = 0;
    private Random random = new Random(47);

    public Toaster(ToastQueue dryQueue) {
        this.dryQueue = dryQueue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
                Toast toast = new Toast(count++);
                System.out.println(toast);
                dryQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupted");
        }
        System.out.println("Toaster off");

    }
}

class Jellyer implements Runnable {
    private ToastQueue dryQueue, jellyQueue;
    private int count;

    public Jellyer(ToastQueue dryQueue, ToastQueue jellyQueue) {
        this.dryQueue = dryQueue;
        this.jellyQueue = jellyQueue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = dryQueue.take();
                toast.jelly();
                System.out.println(toast);
                jellyQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Jellyer interrupted");
        }
        System.out.println("Jellyer off");
    }
}

class PeanutButterer implements Runnable {

    private ToastQueue dryQueue, PeanutQueue;

    public PeanutButterer(ToastQueue dryQueue, ToastQueue PeanutQueue) {
        this.dryQueue = dryQueue;
        this.PeanutQueue = PeanutQueue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast toast = dryQueue.take();
                toast.peanutButter();
                System.out.println(toast);
                PeanutQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("PeanutButterer interrupted");
        }
        System.out.println("PeanutButterer off");
    }
}

class SandwichMaker implements Runnable {
    private ToastQueue jellyQueue, peanutQueue;
    private SandwichQueue sandwichQueue;
    private int count;

    public SandwichMaker(ToastQueue jellyQueue, ToastQueue peanutQueue, SandwichQueue sandwichQueue) {
        this.jellyQueue = jellyQueue;
        this.peanutQueue = peanutQueue;
        this.sandwichQueue = sandwichQueue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast jellyToast = jellyQueue.take();
                Toast peanutToast = peanutQueue.take();
                Sandwich sandwich = new Sandwich(jellyToast, peanutToast, count++);
                System.out.println(sandwich);
                sandwichQueue.put(sandwich);

            }
        } catch (InterruptedException e) {
            System.out.println("SandwichMaker interrupted");
        }
        System.out.println("SandwichMaker off");
    }
}

class SandwichConsumer implements Runnable {
    private SandwichQueue sandwichQueue;

    private int count = 0;

    public SandwichConsumer(SandwichQueue sandwichQueue) {
        this.sandwichQueue = sandwichQueue;
    }


    public void run() {
        try {
            while (!Thread.interrupted()) {
                Sandwich sandwich = sandwichQueue.take();
                if (sandwich.getId() != count++ ||
                        sandwich.getTop().getStatus() != Toast.Status.JELLIED ||
                        sandwich.getBottom().getStatus() != Toast.Status.PEANUTBUTTERED) {
                    System.out.println("Error: " + sandwich);
                    System.exit(1);
                } else {
                    System.out.println("consumer eat " + sandwich);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("SandwichConsumer interrupted");
        }
        System.out.println("SandwichConsumer off");

    }
}