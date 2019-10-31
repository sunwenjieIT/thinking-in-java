package xyz.wenjiesx.book.multithreading;

/**
 * 基于runnable编写的任务
 * @author wenji
 * @date 2019/10/15
 */
public class LiftOff implements Runnable {

    protected int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
//            Thread.yield();
        }

    }

    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
    }
}

