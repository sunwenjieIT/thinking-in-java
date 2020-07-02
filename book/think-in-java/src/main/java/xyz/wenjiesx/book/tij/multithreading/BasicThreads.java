package xyz.wenjiesx.book.tij.multithreading;

/**
 * 基础thread
 * @author wenji
 * @date 2019/10/15
 */
public class BasicThreads {
    public static void main(String[] args) {
        Thread thread = new Thread(new LiftOff());
        thread.start();
        System.out.println("waiting for liftoff");

    }
}

